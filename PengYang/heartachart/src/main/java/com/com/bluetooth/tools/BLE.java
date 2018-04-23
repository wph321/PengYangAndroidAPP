package com.com.bluetooth.tools;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.pengyang.admin.heartachart.R;

import java.util.UUID;

/**
 * Created by admin on 2018/3/10.
 */

public class BLE {

    private BluetoothAdapter mBluetoothAdapter;

    private BluetoothDevice mBluetoothDevice;

    private BluetoothGatt mBluetoothGatt;

    private boolean isScanning = false;

    private Context mContext;

    private final String UUID_KEY_BATTERY_LEVEL_SERVICE = "0000fee9-0000-1000-8000-00805f9b34fb";

    private final String UUID_KEY_BATTERY_LEVEL_CHARACTERISTICS = "d44bc439-abfd-45a2-b575-925416129600";

    private String TAG = "MainActivity";




    //搜索蓝牙设备
    public void startLeScan(BluetoothAdapter mBluetoothAdapter) {
        Log.e(TAG, "搜索蓝牙");
        if (this.mBluetoothAdapter == null) {
            Log.e(TAG, "aDAPTER空");

            return;
        }


        Boolean A = this.mBluetoothAdapter.startLeScan(new UUID[]{UUID.fromString("0000fee9-0000-1000-8000-00805f9b34fb")},mLeScanCallback);   //此mLeScanCallback为回调函数
        Log.e(TAG, String.valueOf(A));

        mHandler.sendEmptyMessageDelayed(0, 10000);  //这个搜索10秒，如果搜索不到则停止搜索
    }

   BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(BluetoothDevice mBluetoothDevice, int arg1, byte[] arg2) {



            Log.e(TAG, "onLeScan() DeviceName------>"+mBluetoothDevice);  //在这里可通过device这个对象来获取到搜索到的ble设备名称和一些相关信息
            System.out.println(mBluetoothDevice.getAddress()+"-------"+mBluetoothDevice.getName()+"------"+mBluetoothDevice.getUuids());
//            if(mBluetoothDevice.getName() == null){
//                return;
//            }
//            if (mBluetoothDevice.getName().contains("ZLG9021P0-1-TC(8FBFC2)")) {    //判断是否搜索到你需要的ble设备
//                Log.i(TAG, "onLeScan() DeviceAddress------>"+mBluetoothDevice.getAddress());
//                mBluetoothAdapter.stopLeScan(mLeScanCallback);   //1、当找到对应的设备后，立即停止扫描；2、不要循环搜索设备，为每次搜索设置适合的时间限制。避免设备不在可用范围的时候持续不停扫描，消耗电量。
//
//                connect();  //连接
//            }
        }
    };

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    Log.e("连接错误", mContext.getResources().getString(R.string.msg_connect_failed));
//                    mBluetoothAdapter.stopLeScan(mLeScanCallback);
                    isScanning = false;
                    Log.e(TAG, "扫描停止（扫描   时间到）");
                    break;
            }
        };
    };
//连接蓝牙
    public boolean connect() {
        if (mBluetoothDevice == null) {
            Log.e(TAG, "没有找到蓝牙设备");
            Log.e(TAG, String.valueOf(mBluetoothDevice));

            return false;
        }
        mBluetoothGatt = mBluetoothDevice.connectGatt(mContext, false, mGattCallback);  //mGattCallback为回调接口

        if (mBluetoothGatt != null) {

            if (mBluetoothGatt.connect()) {
                Log.e(TAG, "蓝牙连接成功");
                System.out.println("蓝牙链接成功");
                return true;
            } else {
                Log.e(TAG, "蓝牙连接失败");
                return false;
            }
        } else {
            Log.e(TAG, "BluetoothGatt null.");
            return false;
        }
    }
    private final BluetoothGattCallback mGattCallback = new BluetoothGattCallback() {
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            if (newState == BluetoothProfile.STATE_CONNECTED) {
                System.out.println("链接成功");
                Toast.makeText(mContext, "连接成功", Toast.LENGTH_LONG).show();
                gatt.discoverServices(); //执行到这里其实蓝牙已经连接成功了

                Log.i(TAG, "Connected to GATT server.");
            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                if(mBluetoothDevice != null){
                    Log.i(TAG, "重新连接");
                    connect();
                }else{
                    Log.i(TAG, "Disconnected from GATT server.");
                }
            }
        }
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
                Log.i(TAG, "onServicesDiscovered");
                getBatteryLevel();  //获取电量
            } else {
                Log.i(TAG, "onServicesDiscovered status------>" + status);
            }
        }

        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            Log.d(TAG, "onCharacteristicRead------>" + "Utils.bytesToHexString(characteristic.getValue())");

//判断UUID是否相等
            if (UUID_KEY_BATTERY_LEVEL_CHARACTERISTICS.equals(characteristic.getUuid().toString())) {
            }
        }

        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            Log.d(TAG, "onCharacteristicChanged------>" + characteristic.getValue());
            System.out.println("onCharacteristicChanged------>" + characteristic.getValue());

//判断UUID是否相等
            if (UUID_KEY_BATTERY_LEVEL_CHARACTERISTICS.equals(characteristic.getUuid().toString())) {
            }
        }

        //接受Characteristic被写的通知,收到蓝牙模块的数据后会触发onCharacteristicWrite
        @Override
        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            Log.d(TAG,"status = " + status);
            Log.d(TAG, "onCharacteristicWrite------>" + "Utils.bytesToHexString(characteristic.getValue())");
        }
    };



    public void getBatteryLevel() {
        BluetoothGattCharacteristic batteryLevelGattC = getCharcteristic(
                UUID_KEY_BATTERY_LEVEL_SERVICE, UUID_KEY_BATTERY_LEVEL_CHARACTERISTICS);
        if (batteryLevelGattC != null) {
            readCharacteristic(batteryLevelGattC);
            setCharacteristicNotification(batteryLevelGattC, true); //设置当指定characteristic值变化时，发出通知。
        }
    }



//获取服务与特征

//a.获取服务

    public BluetoothGattService getService(UUID uuid) {
        if (mBluetoothAdapter == null || mBluetoothGatt == null) {
            Log.e(TAG, "BluetoothAdapter not initialized555555");

            return null;
        }
        return mBluetoothGatt.getService(uuid);
    }

//b.获取特征

    private BluetoothGattCharacteristic getCharcteristic(String serviceUUID, String characteristicUUID) {

//得到服务对象
        BluetoothGattService service = getService(UUID.fromString("0000fee9-0000-1000-8000-00805f9b34fb"));  //调用上面获取服务的方法

        if (service == null) {
            Log.e(TAG, "Can not find 'BluetoothGattService22222'");
            return null;
        }

//得到此服务结点下Characteristic对象
        final BluetoothGattCharacteristic gattCharacteristic = service.getCharacteristic(UUID.fromString("d44bc439-abfd-45a2-b575-925416129600"));
        if (gattCharacteristic != null) {
            return gattCharacteristic;
        } else {
            Log.e(TAG, "Can not find 'BluetoothGattCharacteristic'");
            return null;
        }
    }



//获取数据

    public void readCharacteristic(BluetoothGattCharacteristic characteristic) {
        if (mBluetoothAdapter == null || mBluetoothGatt == null) {
            Log.e(TAG, "BluetoothAdapter not initialized1111111");
            return;
        }
        mBluetoothGatt.readCharacteristic(characteristic);
    }



    public boolean setCharacteristicNotification(BluetoothGattCharacteristic characteristic, boolean enabled) {
        if (mBluetoothAdapter == null || mBluetoothGatt == null) {
            Log.e(TAG, "BluetoothAdapter not initialized");
            return false;
        }
        return mBluetoothGatt.setCharacteristicNotification(characteristic, enabled);
    }

}
