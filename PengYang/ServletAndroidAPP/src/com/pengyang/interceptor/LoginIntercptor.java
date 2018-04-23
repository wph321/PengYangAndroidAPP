package com.pengyang.interceptor;

import java.io.PrintWriter;

import javax.servlet.annotation.HandlesTypes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.pengyang.tools.impl.MD5;

/*
 * 登陆拦截器：防止未经允许登陆网站，除登陆界面外页面拦截
 * 
 * 作者：魏鹏辉
 * 时间：2017.9.13
 * 作用：请求拦截，判断是否正常登陆进入系统，否，返回新窗口登陆，关闭非法窗口，是执行控制器操作
 */
public class LoginIntercptor implements HandlerInterceptor {

	
	MD5 md5 = new MD5();//MD5加密
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

       
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		String url = arg0.getRequestURI();//获取请求地址
		if(url.indexOf("login")>=0){//检查是否登陆页面
			return true;
		}
		HttpSession session = arg0.getSession();
		String str = (String) session.getAttribute("isLogin");//获取登陆状态  
        if(str!=null){ //登陆状态不为空  
            return true;  
        }  
        PrintWriter out = arg1.getWriter();
       
//      在新窗口中打开登陆界面  关闭父窗口
//      关闭父窗口:window.opener.opener=null; window.opener.close();
//        out.print("<script> window.open('/test/login.jsp'); </script>"); //返回登陆界面
        out.print("<script> if(window.opener==null){"
        		+ "window.top.location.href=\"/test/login.jsp\";"
        		+ "}else{"
        		+ "window.opener.top.location.href=\"/test/login.jsp\";"
        		+ "window.close();}</script>"); //返回登陆界面
        return false;  
    }  
}
