package com.pengyang.controller;

import java.awt.Window;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;
import javax.swing.text.html.parser.DocumentParser;
import javax.websocket.Session;

import org.apache.jasper.tagplugins.jstl.core.Out;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.css.DocumentCSS;
import org.w3c.dom.events.DocumentEvent;

import com.modle.Patients;
import com.pengyang.service.PatientsService;

@Controller
@RequestMapping("patient")
public class PatientController {

	
	@Resource
	private PatientsService ps;
	
	@RequestMapping("/show")
	public String showPatient(int page,HttpServletRequest request){
		
		try {
			request.setCharacterEncoding("utf-8");
			int patientCount = ps.patientCount();
			int patientpageNum = ps.pageNumber();
			List<Patients> paList = ps.pageFindAll(page);
//			for(Patients patient : paList){
//				System.out.println(patient.getPat_id());
//			}
			request.setAttribute("paList", paList);
			request.setAttribute("patientCount", patientCount);
			request.setAttribute("pagenum", patientpageNum);
			request.setAttribute("nowPage", page);			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "user-list";
	}
	
	@RequestMapping("/showbaogao")
	public void showJpg(String id,HttpServletRequest request,HttpServletResponse response){		
		String str;
		
		try {
			request.setCharacterEncoding("utf-8");
			str = new String((request.getParameter("id")).getBytes("iso-8859-1"),"utf-8");
			int pat_id = Integer.parseInt(str);
			Patients patient = ps.findOne(pat_id);

//	将获取到的地址通过方法遍历查询到文件夹下pdf地址并返回给fileAddress
//		String pdfAddress = readPDFAddress(patient.getPdf_path());
			List<String> pdfAddress = readPDFAddress(patient.getPdf_path());
			String path = pdfAddress.get(0);
//			System.out.println(path);
			pdfStreamHandeler(path,request,response);
			request.setAttribute("src", "baogao.pdf");
		} catch (Exception e) {
		}
	}
	
	@RequestMapping("/baogao")
	public String showBaoGao(String id,HttpServletRequest request,HttpServletResponse response){
			String str;
				
			HttpSession session = request.getSession();
				try {
						request.setCharacterEncoding("utf-8");
						str = new String((request.getParameter("id")).getBytes("iso-8859-1"),"utf-8");
						int pat_id = Integer.parseInt(str);
					Patients patient = ps.findOne(pat_id);

//				将获取到的地址通过方法遍历查询到文件夹下pdf地址并返回给fileAddress
//					String pdfAddress = readPDFAddress(patient.getPdf_path());
					List<String> pdfAddress = readPDFAddress(patient.getPdf_path());
//					System.out.println(pdfAddress);
						request.setAttribute("patient", patient);
						session.setAttribute("pdf_id", pat_id);
//						request.setAttribute("file", pdfAddress);
						return "user-show";
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				return "user-show";
	}

	@RequestMapping("findByNameOrNum")
	public String findByNameOrNum(String mess,int page,HttpServletRequest request){
		
		request.setAttribute("mess", mess);
		
		//如果是姓名
//			if(!isStr2Num(mess)){ 
		try {
			request.setCharacterEncoding("utf-8");
			Map paMap = ps.findByname(mess,page);
			
			List<Patients> paList = (List<Patients>) paMap.get("patient");
			
			int patientpageNum = (int) paMap.get("allpage");
			int count = (int) paMap.get("count");
			
			if(count==0){
				request.setAttribute("search", "search");
				request.setAttribute("patientCount", 0);
				request.setAttribute("returnMess", " 没有一条符合'"+mess+"'条件的数据,请重新输入查询,或刷新返回首页");
				request.setAttribute("pagenum", 1);
				request.setAttribute("paList", null);
				request.setAttribute("nowPage", 1);
				request.setAttribute("mess", mess);
				request.setAttribute("refresh", "/test/patient/show.do?page=1");
				return "user-list";
			}
			request.setAttribute("search", "search");
			request.setAttribute("patientCount", count);
			request.setAttribute("paList", paList);
			request.setAttribute("pagenum", patientpageNum);
			request.setAttribute("nowPage", page);
			request.setAttribute("mess", mess);
			request.setAttribute("refresh", "/test/patient/findByNameOrNum.do?mess="+mess+"&page="+page);
			return "user-list";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//如果是医疗号
//			}else{
//		int num = Integer.parseInt(mess);
//		try {
//			Patients patient = ps.findOne(num);
//			if(patient==null){
//					
//					request.setAttribute("search", "search");
//					request.setAttribute("patientCount", 0);
//					request.setAttribute("pagenum", 1);
//					request.setAttribute("paList", null);
//					request.setAttribute("nowPage", 1);	
//					request.setAttribute("returnMess", "没有一条符合条件的数据,,请重新输入查询,或刷新返回首页");
//					request.setAttribute("mess", mess);
//					request.setAttribute("refresh", "/test/patient/show.do?page=1");
//					return "user-list";
//			}
//			List<Patients> paList = new ArrayList();
//			paList.add(patient);
//			request.setAttribute("search", "search");
//			request.setAttribute("paList", paList);
//			request.setAttribute("patientCount", 1);
//			request.setAttribute("pagenum", 1);
//			request.setAttribute("nowPage", 1);	
//			request.setAttribute("mess", mess);
//			request.setAttribute("refresh", "/test/patient/findByNameOrNum.do?mess="+mess+"&page="+page);
//			return "user-list";
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			}
//				}
		return null;
		
	}
	
	@RequestMapping("result.do")
	public void jsonReturnResult(String id, HttpServletRequest req, HttpServletResponse res){

		HttpSession session = req.getSession();
		PrintWriter out;
		try {
			res.setCharacterEncoding("utf-8");
			req.setCharacterEncoding("utf-8");
			Patients patient;
			out = res.getWriter();
	        res.setContentType("text/text");          //设置请求以及响应的内容类型以及编码方式
	        res.setCharacterEncoding("UTF-8");
	        int a = Integer.parseInt(id);
			patient = ps.findOne(a);
			String result = patient.getResult();
			String doctor = patient.getDiag_Doctor();
			session.removeAttribute("patient");
			session.setAttribute("patient", patient);
			req.setAttribute("result", result);
			out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/** 
	* 查看一个字符串是否可以转换为数字 
	* @param str 字符串 
	* @return true 可以; false 不可以 
	*/  
	public static boolean isStr2Num(String str) {   
	  
		try {  
		    Integer.parseInt(str);  
		    return true;  
		} catch (NumberFormatException e) {  
		    return false;  
		}  
	}  
	
//	读取pdf输入流方法
	public void pdfStreamHandeler(String filePath, HttpServletRequest request, HttpServletResponse response) {
        byte[] data = null;
//        OutputStreamWriter writer = null;
//        InputStreamReader reader = null;
//        PrintWriter bw = null;
//        BufferedReader br = null;
        try {
//        	创建输入输出流
//        	response.setContentType("application/pdf ");
        	response.setCharacterEncoding("utf-8");
        	response.setHeader( "Content-pdf", "filename=Report.pdf");
        	File file = new File(filePath);
            FileInputStream input = new FileInputStream(file);
//            reader = new InputStreamReader(input);
//            br = new BufferedReader(reader);
//            ServletOutputStream os = response.getOutputStream();
//            writer = new OutputStreamWriter(os);
//            bw = response.getWriter();
//            OutputStream ops = new BufferedOutputStream(os);
            data = new byte[input.available()];
            input.read(data);
            try{ 
            		response.getOutputStream().write(data);
            		
            }catch(Exception e){
            	e.printStackTrace();
            	
            }finally{
            	input.close();
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }

    }
	
//	jpg地址
	public List<String> doPdfAddress(String address){
		String str = address+"\\Report\\";
		List<String> pathList = new ArrayList<>(); 
		 File dir = new File(str);
	        File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
	        if (files != null) {
	            for (int i = 0; i < files.length; i++) {
	                String fileName = files[i].getName();
	                if (files[i].isDirectory()) { // 判断是文件还是文件夹
	                    pathList.add(files[i].getAbsolutePath()); // 获取文件绝对路径
//	                    System.out.println("absolutepath:"+files[i].getAbsolutePath());
	                } else if (fileName.endsWith("jpg")) { // 判断文件名是否以.avi结尾
	                    String strFileName = files[i].getAbsolutePath();
//	                    System.out.println("---" + strFileName);
	                    pathList.add(files[i].getPath());
//	                    System.out.println("path:"+files[i].getPath());
	                } else {
	                    continue;
	                }
	            }

	        }
	        return pathList;
	}
	
//	PDF地址
	public List<String> readPDFAddress(String address){
		String str = address+"\\pdf\\";
		List<String> pathList = new ArrayList<>(); 
		 File dir = new File(str);//获取文件夹
	        File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
	        if (files != null) {
	            for (int i = 0; i < files.length; i++) {
	                String fileName = files[i].getName();
	                if (files[i].isDirectory()) { // 判断是文件还是文件夹
	                	String strFileName = files[i].getPath(); // 获取文件绝对路径
	                	 pathList.add(files[i].getPath()); // 获取文件绝对路径
//	                    System.out.println("absolutepath:"+files[i].getAbsolutePath());
	                } else if (fileName.endsWith("pdf")) { // 判断文件名是否以.pdf结尾
	                    String strFileName = files[i].getPath();
	                    pathList.add(files[i].getPath()); // 获取文件绝对路径
//	                    System.out.println("---" + strFileName);
	                
//	                    System.out.println("path:"+files[i].getPath());
	                } else {
	                    continue;
	                }
	            }

	        }else{
	        	 System.out.println("没有pdf文件");
	        }
	        return pathList;
	}
	
}
