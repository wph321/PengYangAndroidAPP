package com.pengyang.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.modle.CommunityHospital;
import com.modle.Superiorhospital;
import com.modle.User;
import com.modle.UserLogin;
import com.pengyang.service.UserLoginService;
import com.pengyang.service.UserService;
import com.request.entity.Login;
import com.request.entity.Regist;
import com.service.impl.CommunityHospitalServiceImpl;
import com.service.impl.SuperiorhospitalServiceImpl;

@Controller
@RequestMapping("/login")
public class LoginController {

//	@Autowired
//	@Resource
//	private AdminService adminService;
	@Autowired
	private UserLoginService uls;
	@Autowired
	private UserService userService;
	@Autowired
	private SuperiorhospitalServiceImpl shs;
	@Autowired
	private CommunityHospitalServiceImpl chs;
	
	private static final long serialVersionUID = 369840050351775312L;

	
	
	
	
	@RequestMapping("/doctor")
	public void checkLoging(Login login,HttpSession session,HttpServletResponse response){
//		Admin admin = adminService.findAdminById(1);
		try {
			response.setCharacterEncoding("UTF-8");
	        response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			String password = new String(login.getPassword().getBytes("ISO-8859-1"), "UTF-8");
			String username = new String(login.getUsername().getBytes("ISO-8859-1"), "UTF-8");
			UserLogin user = uls.findOne(username);
//			System.out.println(login.getUsername()+"....."+login.getPassword());
//			System.out.println(user); 
				if(user.getPassword().equals(password)){
					session.setAttribute("isLogin", "2");
					session.setAttribute("userloginname", user.getUserName());
					session.setAttribute( "user", user);
					HashMap loginMap = new HashMap();
					loginMap.put("username", username);
				        
				        out.print("success");
				        out.flush();
				        out.close();
				}else{
					out.print("密码错误");
				}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 response.setCharacterEncoding("UTF-8");
		        response.setContentType("text/html");
		        PrintWriter out;
		        
				try {
					out = response.getWriter();
					out.print("登录错误，请重试");
			        out.flush();
			        out.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        
		}
//		System.out.println("username:"+login.getUsername()+"     "+"password:"+login.getPassword());
//		System.out.println("isLogin:"+session.getAttribute("isLogin"));

	}
	
//	ajax楠岃瘉鐧婚檰鐢ㄦ埛鍚嶆槸鍚﹀瓨鍦�
	@RequestMapping("/testusername")
	@ResponseBody
	public void ajaxUserName(String username,HttpServletResponse response,HttpServletRequest request){  
        try{  
            JSONObject obj=new JSONObject();//鍒涘缓json瀵硅薄  
            PrintWriter out = response.getWriter();
//            System.out.println("ajax:"+username);
            int count = uls.findCount(username);//鍦ㄦ暟鎹簱涓噸澶嶆鏁�  
            if(count > 0){  //鐧诲綍鐘舵�佲��1鈥濓紝瀛樺湪鐢ㄦ埛锛屸��2鈥濈敤鎴蜂笉瀛樺湪
                obj.put("info", "1");  //灏嗙姸鎬佺爜鏀惧叆json瀵硅薄涓�
            }else{  
                obj.put("info", "0"); 
            }  
             out.print(obj);  //杩斿洖鐧婚檰鐣岄潰json瀵硅薄
              
        }catch(Exception ex){  
            System.out.println(ex);  
        }  
    }  

//閫�鍑虹櫥闄嗭紝娓呯┖session
	@RequestMapping("/exit")
public void exitSystem(HttpServletRequest req,HttpServletResponse res,HttpSession session){

	session.removeAttribute("isLogin");//鍒犻櫎鐧诲綍鐘舵��
	session.removeAttribute("userloginname");//鍒犻櫎session涓櫥闄嗘椂淇濆瓨鐨勭敤鎴峰悕
	
	PrintWriter out;
	try {
		out = res.getWriter();
		res.setCharacterEncoding("UTF-8");
	    res.setContentType("text/html");
	    out.print("注销成功");
	    out.flush();
	    out.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}	
	
}