package com.pengyang.tools.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.zip.*;
import org.apache.tools.ant.*;
import org.apache.tools.ant.taskdefs.Expand;
import org.apache.tools.zip.ZipFile;

import com.pengyang.tools.dao.ZipFileDeflater;


/*
 * 作者：魏鹏辉
 * 时间：2017.9.14
 * 
 * 为解决项目文件上传的zip文件解压的接口方法实现（解压查看后调用删除方法删除解压出的文件夹，缓解服务器存储压力）
 * 大致功能包括用了多态，递归等JAVA核心技术，可以对单个文件和任意级联文件夹进行压缩和解压。 需在代码中自定义源输入路径和目标输出路径。
 * 
 * 注：会增加服务器内存压力
 * 
 */
public class ZipFileDeflaterImpl implements ZipFileDeflater {

	private int k = 1;//定义递归次数
	
//	解压方法实现
	@Override
	public void Decompression(String zipFilepath,String outputPath) throws Exception {

		/*
		 * Java.util.zip下的格式转换有问题 ，jdk中的zip存在字符编码的问题。
		 * 发生zip java.lang.IllegalArgumentException: MALFORMED 错误
		 * 
		 * 解决方案：修改jdk源码:
		 * 			要修改ZipInputStream这个类的getUTF8String这个方法

					在这个方法前面加上一段代码

					try
					{
  						String s= new String(b,off,len,"gbk");
  						return s;

					}catch(){
  						.....
					}
		 * 			
		 * 		     使用ant下的zip包
		 * 
		 * 
		 * 改为使用org.apache.ant下的zip包解压文件，正常解压
		 */
//		long startTime=System.currentTimeMillis();  
//        try {  
//        	ZipFile zipFile = new ZipFile(zipFilePath);
//        	ZipInputStream Zin=new ZipInputStream(new FileInputStream(  
//                    zipFilePath));//输入源zip路径  
//            BufferedInputStream Bin=new BufferedInputStream(Zin);  
//            String Parent=outputPath; //输出路径（文件夹目录）  
//            File Fout=null;  
//            ZipEntry entry;  
//            try {  
//                while((entry = Zin.getNextEntry())!=null && !entry.isDirectory()){  
//                    Fout=new File(Parent,entry.getName());  
//                    if(!Fout.exists()){  
//                        (new File(Fout.getParent())).mkdirs();  
//                    }  
//                    FileOutputStream out=new FileOutputStream(Fout);  
//                    BufferedOutputStream Bout=new BufferedOutputStream(out);  
//                    int b;  
//                    while((b=Bin.read())!=-1){  
//                        Bout.write(b);  
//                    }  
//                    Bout.close();  
//                    out.close();  
//                    System.out.println(Fout+"解压成功");      
//                }  
//                Bin.close();  
//                Zin.close();  
//            } catch (IOException e) {  
//                // TODO Auto-generated catch block  
//                e.printStackTrace();  
//            }  
//        } catch (FileNotFoundException e) {  
//            // TODO Auto-generated catch block  
//            e.printStackTrace();  
//        }  
		
		 if (!new File(zipFilepath).exists()) {
	            throw new RuntimeException("zip file " + zipFilepath + " does not exist.");
	        }

	        Project proj = new Project();
	        Expand expand = new Expand();
	        expand.setProject(proj);
	        expand.setTaskType("unzip");
	        expand.setTaskName("unzip");
	        expand.setEncoding("utf-8");

	        expand.setSrc(new File(zipFilepath));
	        expand.setDest(new File(outputPath));
	        expand.execute();
	        
	        System.out.println("uncompress successed.");
	    }

//  压缩文件方法实现
//	ZipFileName:压缩包名;  inputFile:需要压缩的文件;
	@Override
	public void Compression(String ZipFileName,File inputFile) throws Exception {

//		压缩程序执行状态
		System.out.println("压缩中........");
//		设置压缩文件的压缩文件名
		ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(ZipFileName));
		BufferedOutputStream bos = new BufferedOutputStream(zipOut);
		
//		执行压缩
		zip(zipOut, inputFile, inputFile.getName(), bos);  
//      关闭工作流
		bos.close(); 
	    zipOut.close();
	    System.out.println("工作流关闭正常");
//		压缩程序执行状态
		System.out.println("压缩完成"); 
	}
//  删除文件夹/文件，方法实现
	@Override
	public boolean deleteFile(String srcFile) throws Exception {
		// TODO Auto-generated method stub

		boolean success = deleteDir(new File(srcFile));
        if (success) {
            System.out.println("Successfully deleted populated directory: " + srcFile);
        } else {
            System.out.println("Failed to delete populated directory: " + srcFile);
        }     
		return success;
        
	} 
	
	
//	压缩执行方法
	public void zip(ZipOutputStream out, File f,String base, BufferedOutputStream bo)throws Exception{
		
		if (f.isDirectory()) {  
            File[] fl = f.listFiles();  
            if (fl.length == 0) {  
                out.putNextEntry(new ZipEntry(base + "/")); // 创建zip压缩进入点base  
                System.out.println(base + "/");  
            }  
            for (int i = 0; i < fl.length; i++) {  
                zip(out, fl[i], base + "/" + fl[i].getName(), bo); // 递归遍历子文件夹  
            }  
            System.out.println("第" + k + "次递归");  
            k++;  
        } else {  
            out.putNextEntry(new ZipEntry(base)); // 创建zip压缩进入点base  
            System.out.println(base);  
            FileInputStream in = new FileInputStream(f);  
            BufferedInputStream bi = new BufferedInputStream(in);  
            int b;  
            while ((b = bi.read()) != -1) {  
                bo.write(b); // 将字节流写入当前zip目录  
            }  
            bi.close();  
            in.close(); // 输入流关闭  
        }  
    }  
		
/*
*  递归删除目录下的所有文件及子目录下所有文件
*  @param dir 将要删除的文件目录
*  @return boolean Returns "true" if all deletions were successful.
*                 If a deletion fails, the method stops attempting to
*                 delete and returns "false".
*/
	 private static boolean deleteDir(File dir) {
	        if (dir.isDirectory()) {
	            String[] children = dir.list();
//      递归删除目录中的子目录下
	            for (int i=0; i<children.length; i++) {
	                boolean success = deleteDir(new File(dir, children[i]));
	                if (!success) {
	                    return false;
	                }
	            }
	        }
// 		目录此时为空，可以删除
	        return dir.delete();
	    }
	 
/*
*  删除空目录
*  @param dir 将要删除的目录路径
*/	 
	 
	 private static void doDeleteEmptyDir(String dir) {
	        boolean success = (new File(dir)).delete();
	        if (success) {
	            System.out.println("Successfully deleted empty directory: " + dir);
	        } else {
	            System.out.println("Failed to delete empty directory: " + dir);
	        }
	    }
	 
		
	}
