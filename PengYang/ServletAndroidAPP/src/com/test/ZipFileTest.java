package com.test;

import java.io.File;

import com.pengyang.tools.dao.ZipFileDeflater;
import com.pengyang.tools.impl.ZipFileDeflaterImpl;

public class ZipFileTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ZipFileDeflater zfd = new ZipFileDeflaterImpl();

////		解压测试
//		String zipFilePath = "D:\\新建文件夹 (4)\\张三.zip";//文件全名：文件路径+文件名；
//		String outputPath = "D:\\新建文件夹 (4)";//解压到的路径；
//		try {
//			long startTime=System.currentTimeMillis();  
//			zfd.Decompression(zipFilePath, outputPath);
//			  long endTime=System.currentTimeMillis();  
//		        System.out.println("耗费时间： "+(endTime-startTime)+" ms");
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
////		压缩测试
//		File inputFile = new File("D:\\新建文件夹 (4)\\张三");//要压缩的文件夹路径
//		String zipFileName = "D:\\新建文件夹 (4)\\"+inputFile.getName()+".zip";//要解压到的zip文件：路径+zip文件
//		try {
//			long startTime = System.currentTimeMillis();
//			zfd.Compression(zipFileName, inputFile);
//			long endTime=System.currentTimeMillis();  
//	        System.out.println("耗费时间： "+(endTime-startTime)+" ms");
//			System.out.println(zipFileName);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		删除文件测试
		
		String srcFile ="D:\\新建文件夹 (4)\\张三";
		try {
			System.out.println(zfd.deleteFile(srcFile));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
