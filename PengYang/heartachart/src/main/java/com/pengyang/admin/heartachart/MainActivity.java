package com.pengyang.admin.heartachart;


import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.com.bluetooth.tools.BLE;
import com.draw.heart.*;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {


    private static String hexString = "0123456789abcdef";
    public static final String EXTRAS_DEVICE_NAME = "deviceName";
    public static final String EXTRAS_DEVICE_ADDRESS = "deviceAddress";

    private static final String UUIDDes="00002902-0000-1000-8000-00805f9b34fb";

    protected static String uuidQppService = "0000fee9-0000-1000-8000-00805f9b34fb";
    protected static String uuidWrite = "d44bc439-abfd-45a2-b575-925416129600";
    protected static String uuidRead = "D44BC439-ABFD-45A2-B575-925416129601";


    private TextView textdata;

    private BluetoothGattCharacteristic bluetoothGattCharacteristic;

    private ImageView bluetoothImage;

    private BluetoothAdapter mBluetoothAdapter;

    private BluetoothDevice mBluetoothDevice;

    private BluetoothGatt mBluetoothGatt;

    private boolean isScanning = false;

    private Context mContext;

    private final String UUID_KEY_BATTERY_LEVEL_SERVICE = "0000fee9-0000-1000-8000-00805f9b34fb";

    private final String UUID_KEY_BATTERY_LEVEL_CHARACTERISTICS = "d44bc439-abfd-45a2-b575-925416129601";

    private String TAG = "MainActivity";


    private Context context;

    private int REQUEST_ENABLE_BT = 1;

    private Bundle options;

    private BLE BLE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (android.os.Build.VERSION.SDK_INT < 18) {
            // 说明sdk不够高版本
            Toast.makeText(MainActivity.this,"此版本不支持BLE，请升级到 Android 4.3 版本或以上版本",Toast.LENGTH_SHORT);
            finish();
        }

Toast.makeText(MainActivity.this,"支持蓝牙",Toast.LENGTH_SHORT);
        final TextView connectBluetooth = findViewById(R.id.ConnectBLE);

        connectBluetooth.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
//            回调方法
            public void onClick(View view) {

//                LinearLayout topLinearLayout = findViewById(R.id.topPanel);
//                LinearLayout middleLinearLayout = findViewById(R.id.middlePanel);
//                LinearLayout downLinearLayout = findViewById(R.id.downPanel);
                LinearLayout bigLinearLayout = findViewById(R.id.bigPanel);


                final PainView painView = new PainView(MainActivity.this);
                painView.setM("chuanzhi");
                final CardiographView gd = new CardiographView(MainActivity.this);

                gd.setMinimumWidth(bigLinearLayout.getWidth());
                gd.setMinimumHeight(bigLinearLayout.getHeight());
                Log.e("宽", String.valueOf(bigLinearLayout.getWidth()));
                Log.e("高", String.valueOf(bigLinearLayout.getHeight()));


//                painView.setBackgroundColor(Color.WHITE);

//                painView.setMinimumWidth(bigLinearLayout.getWidth());
//                painView.setMinimumHeight(bigLinearLayout.getHeight());
//                painView.setBackgroundColor(Color.BLACK);
//                重绘view组件
                view.invalidate();
                bigLinearLayout.addView(painView);
//                middleLinearLayout.addView(painView);
//                downLinearLayout.addView(painView);
                textdata = findViewById(R.id.connectData);
                bluetoothImage = findViewById(R.id.imageView);
                bluetoothImage.setBackgroundColor(R.color.Green);

//                BluetoothManager mBluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
//                if (mBluetoothManager != null) {
//                    mBluetoothAdapter = mBluetoothManager.getAdapter();
//                    if (mBluetoothAdapter != null) {
//                        if (!mBluetoothAdapter.isEnabled()) {
//                            mBluetoothAdapter.enable();  //打开蓝牙
//                        }
//                    }
//
//                startLeScan();







//                    Button bu1 = findViewById(R.id.button2);
//                    Button bu = findViewById(R.id.button);
//                    bu.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            dataSend();
//                            setCharacteristicNotification(bluetoothGattCharacteristic,true);
//                            readCharacteristic(bluetoothGattCharacteristic);
//                        }
//                    });
//                    bu1.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            dataSend2();
//                            setCharacteristicNotification(bluetoothGattCharacteristic,true);
//                            readCharacteristic(bluetoothGattCharacteristic);
//                        }
//                    });

                }



//            }




        });
    }

//初始化藍牙
    private void initBluetooth() {
        BluetoothManager mBluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        if (mBluetoothManager != null) {
            mBluetoothAdapter = mBluetoothManager.getAdapter();
            if (mBluetoothAdapter != null) {
                if (!mBluetoothAdapter.isEnabled()) {
                    mBluetoothAdapter.enable();  //打开蓝牙
                }
            }
        }
    }

//    @SuppressLint("RestrictedApi")
//    public void openBlutooth(BluetoothAdapter mBluetoothAdapter){
//
//        if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
//            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT,options);
//        }
//    }

    //搜索蓝牙设备
    public void startLeScan() {
        Log.e(TAG, "搜索蓝牙");
        textdata = findViewById(R.id.connectData);
        bluetoothImage = findViewById(R.id.imageView);
        textdata.setText("搜索中......");
        if (this.mBluetoothAdapter == null) {
            Log.e(TAG, "aDAPTER空");
            textdata.setText("请重新连接......");
            return;
        }


        this.mBluetoothAdapter.startLeScan(new UUID[]{UUID.fromString("0000fee9-0000-1000-8000-00805f9b34fb")},mLeScanCallback);   //此mLeScanCallback为回调函数


        mHandler.sendEmptyMessageDelayed(0, 10000);  //这个搜索10秒，如果搜索不到则停止搜索
    }

    BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(BluetoothDevice device, int arg1, byte[] arg2) {



            Log.e(TAG, "onLeScan() DeviceName------>"+device);  //在这里可通过device这个对象来获取到搜索到的ble设备名称和一些相关信息
            System.out.println(device.getAddress()+"-------"+device.getName()+"------");
            if(device.getName() == null){
                Toast.makeText(MainActivity.this,"没有搜索到蓝牙设备",Toast.LENGTH_SHORT);
                MainActivity.this.textdata = findViewById(R.id.connectData);
                textdata.setText("[未找到设备]");
                return;
            }
            if (device.getName().contains("ZLG9021P0-1-TC(8FBFC2)")) {    //判断是否搜索到你需要的ble设备
                Log.i(TAG, "onLeScan() DeviceAddress------>"+device.getAddress());
                mBluetoothDevice = device;
                mBluetoothAdapter.stopLeScan(mLeScanCallback);   //1、当找到对应的设备后，立即停止扫描；2、不要循环搜索设备，为每次搜索设置适合的时间限制。避免设备不在可用范围的时候持续不停扫描，消耗电量。

                connect();  //连接
            }
        }
    };

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    Toast.makeText(MainActivity.this,"没有搜索到蓝牙设备",Toast.LENGTH_LONG);
                    MainActivity.this.textdata = findViewById(R.id.connectData);
                    textdata.setText("[未找到设备]");
                    mBluetoothAdapter.stopLeScan(mLeScanCallback);
                    isScanning = false;
                    Log.e(TAG, "扫描停止");
                    break;
            }
        };
    };
    //连接蓝牙
    @SuppressLint("ResourceAsColor")
    public boolean connect() {
        if (mBluetoothDevice == null) {
            Log.e(TAG, "没有找到蓝牙设备");
            Log.e(TAG, String.valueOf(mBluetoothDevice));

            return false;
        }
        mBluetoothGatt = mBluetoothDevice.connectGatt(mContext, false, mGattCallback);  //mGattCallback为回调接口

        if (mBluetoothGatt != null) {

            if (mBluetoothGatt.connect()) {
                MainActivity.this.bluetoothImage.setBackgroundColor(Color.GREEN);
                MainActivity.this.textdata.setText("[蓝牙已连接]");
                Log.e(TAG, "蓝牙连接成功");
                return true;
            } else {
                Log.e(TAG, "蓝牙连接失败");
                MainActivity.this.textdata.setText("[未连接]");
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
//                System.out.println("链接成功");
//                Toast.makeText(mContext, "连接成功", Toast.LENGTH_LONG).show();
                gatt.discoverServices(); //执行到这里其实蓝牙已经连接成功了

                MainActivity.this.bluetoothImage = findViewById(R.id.imageView);
                MainActivity.this.bluetoothImage.setBackgroundColor(Color.GREEN);
                MainActivity.this.textdata = findViewById(R.id.connectData);

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
//                enableNotification(true,gatt,getCharcteristic());
                Log.i(TAG, "onServicesDiscovered status------>" + status);
            }
        }

        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            Log.d(TAG, "onCharacteristicRead------>" +bytesToHex(characteristic.getValue()));

//判断UUID是否相等
            if (uuidWrite.equals(characteristic.getUuid().toString())) {
            }
        }

        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            byte[] arrayOfByte = characteristic
                    .getValue();
            Log.e(TAG, "onCharacteristicChanged------>" + bytesToHex(characteristic.getValue()));

//判断UUID是否相等
            if (uuidWrite.equals(characteristic.getUuid().toString())) {
                Log.e("CharacteristicChanged中", "数据接收了哦"+bytesToHex(characteristic.getValue()));
            }
        }

        //接受Characteristic被写的通知,收到蓝牙模块的数据后会触发onCharacteristicWrite
        @Override
        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            Log.d(TAG,"status = " + status);
            Log.d(TAG, "onCharacteristicWrite------>" + bytesToHex(characteristic.getValue()));
        }
    };



    public void getBatteryLevel() {
        BluetoothGattCharacteristic batteryLevelGattC = getCharcteristic(UUID.fromString(uuidWrite));
        if (batteryLevelGattC != null) {
            readCharacteristic(batteryLevelGattC);
           Log.e("5445455", String.valueOf(setCharacteristicNotification(batteryLevelGattC, true))); //设置当指定characteristic值变化时，发出通知。


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

    private BluetoothGattCharacteristic getCharcteristic(UUID charauuid) {

//得到服务对象
        BluetoothGattService service = getService(UUID.fromString("0000fee9-0000-1000-8000-00805f9b34fb"));  //调用上面获取服务的方法

        if (service == null) {
            Log.e(TAG, "Can not find 'BluetoothGattService22222'");
            return null;
        }

//得到此服务结点下Characteristic对象
        final BluetoothGattCharacteristic gattCharacteristic = service.getCharacteristic(charauuid);
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
        characteristic = getCharcteristic(UUID.fromString(uuidRead));
        Boolean isBoolean = mBluetoothGatt.readCharacteristic(characteristic);
        Log.e(TAG, "读取数据："+String.valueOf(isBoolean));
    }



//接收数据
    public boolean setCharacteristicNotification(BluetoothGattCharacteristic characteristic, boolean enabled) {

        enabled =true;
        characteristic = getCharcteristic(UUID.fromString(uuidRead));
        if (mBluetoothAdapter == null || mBluetoothGatt == null) {
            Log.e(TAG, "BluetoothAdapter not initialized");
            return false;
        }
        for(BluetoothGattDescriptor dp:characteristic.getDescriptors()){
            dp.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
            mBluetoothGatt.writeDescriptor(dp);
        }
        return mBluetoothGatt.setCharacteristicNotification(characteristic, enabled);


    }


    /**
     * 向蓝牙发送数据
     */
    public void dataSend(){

       byte[] bytes = {0x55};


        write(bytes);
    }

    public void dataSend2(){

        byte[] bytes = {(byte) 0xaa} ;

     write(bytes);
    }

    public void write(byte[] data) {   //一般都是传byte
        //得到可写入的characteristic Utils.isAIRPLANE(mContext) &&

        BluetoothGattCharacteristic writeCharacteristic = getCharcteristic(UUID.fromString(uuidWrite));  //这个UUID都是根据协议号的UUID
        if (writeCharacteristic == null) {
            Log.e(TAG, "Write failed. GattCharacteristic is null.");
            return;
        }
        writeCharacteristic.setValue(data); //为characteristic赋值
        writeCharacteristicWrite(writeCharacteristic);

    }



    public void writeCharacteristicWrite(BluetoothGattCharacteristic characteristic) {
        if (mBluetoothAdapter == null || mBluetoothGatt == null) {
            Log.e(TAG, "BluetoothAdapter not initialized");
            return;
        }
        Log.e(TAG, "BluetoothAdapter 写入数据");
        boolean isBoolean = false;
        isBoolean = mBluetoothGatt.writeCharacteristic(characteristic);
        Log.e(TAG, "BluetoothAdapter_writeCharacteristic = " +isBoolean);  //如果isBoolean返回的是true则写入成功
    }

    private String bytesToHex(final byte[] dataBytes) {
        char temp;
        String str = "";
        for (int n=0; n<dataBytes.length; n++) {
            temp = (char) ((dataBytes[n] & 0xf0) >> 4);
            str += (char)(  temp >= 10? 'A'+(temp-10):'0'+temp);
            temp = (char) ((dataBytes[n] & 0x0f) >> 0);
            str += (char)( temp >= 10? 'A'+(temp-10):'0'+temp);
            str +=  ' ';
        }
        return str;
    }



}
