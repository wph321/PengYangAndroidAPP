package com.pengyang.com.web;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by admin on 2018/3/29.
 */

public class WebServicePost {

    private static String IP = "192.168.1.112:8080/ServletAndroidAPP/";

    public static String executeHttpPost(String username, String password, String address) {


        HttpURLConnection conn = null;
        String urlStr = "http://" + IP + address;
        InputStream is = null;
        String resultData = "";
        try {
            URL url = new URL(urlStr); //URL对象
            conn = (HttpURLConnection) url.openConnection(); //使用URL打开一个链接,下面设置这个连接
            conn.setRequestMethod("POST"); //使用POST请求

            //参数字符串
            String param = "name=" + URLEncoder.encode(username, "UTF-8")//服务器不识别汉字
                    + "&password=" + URLEncoder.encode(password, "UTF-8");

            //用输出流向服务器发出参数，要求字符，所以不能直接用getOutputStream
            DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
            dos.writeBytes(param);
            dos.flush();
            dos.close();

            if (conn.getResponseCode() == 200) {//返回200表示相应成功
                is = conn.getInputStream();   //获取输入流
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader bufferReader = new BufferedReader(isr);
                String inputLine = "";
                while ((inputLine = bufferReader.readLine()) != null) {
                    resultData += inputLine + "\n";
                }
                return resultData;
//                showRes("post方法取回内容：" + resultData);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
            return null;
    }
}
