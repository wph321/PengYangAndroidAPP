package com.pengyang.tools.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
/*
 * MD5加密
 * 
 * 作者：魏鹏辉
 * 时间：2017.6.13
 * 作用：为数据加密提供MD5接口，以加密数据
 */
public class MD5 {

	// ����BASE64Encoder���м����ִ�
		public static String EncoderByMD5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {

			// ȷ�ϼ��㷽��
			MessageDigest md = MessageDigest.getInstance("MD5");
			BASE64Encoder base64en = new BASE64Encoder();

			// ���ܺ���ַ���
			String newstr = base64en.encode(md.digest(str.getBytes("utf-8")));
			return newstr;
		}

		// �ж��ַ����Ƿ�һ��
		public static boolean CheckStr(String newstr,String oldstr) throws NoSuchAlgorithmException, UnsupportedEncodingException{
			
			if(EncoderByMD5(newstr).equals(oldstr)){
				return true;
			}else{
				return false;
			}
			}

		
		// ����BASE64Decoder�����ַ�������
		public static String DecoderByMD5(String str){
			BASE64Decoder base64de = new BASE64Decoder();
			
			String b = "";
			byte[] newstr;
			try {
				newstr = base64de.decodeBuffer(str);
				b = new String(newstr);
				b.getBytes("utf-8");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return b;
			
		}
	
}
