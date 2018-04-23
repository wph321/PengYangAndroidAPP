package com.pengyang.tools.dao;

import java.io.BufferedOutputStream;
import java.io.File;
import java.util.zip.ZipOutputStream;

public interface ZipFileDeflater {

/*
 * 解压zip文件工具类接口
 * 
 * 作者： 魏鹏辉
 * 时间：2017.9.13 下午 15:59
 * 作用：解压；压缩；删除文件；关闭工作流等方法提供接口
 * 
 */
	
//	解压
	public void Decompression(String zipFilePath,String outputPath) throws Exception;
//	压缩
	public void Compression(String ZipFileName,File inputFile) throws Exception;
//	删除解压后的文件
	public boolean deleteFile(String srcFile) throws Exception;
	
}
 