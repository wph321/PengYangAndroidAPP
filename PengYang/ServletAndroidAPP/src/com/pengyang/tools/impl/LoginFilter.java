package com.pengyang.tools.impl;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.*;

import org.springframework.http.HttpStatus;

/*
 * 登陆拦截器
 * 
 * 作者：魏鹏辉
 * 时间：2017.9.13
 * 作用：请求拦截，判断是否正常登陆进入系统，否，返回新窗口登陆，关闭非法窗口，是执行控制器操作
 */

public class LoginFilter implements Filter {

	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		if (!(servletRequest instanceof HttpServletRequest) || !(servletResponse instanceof HttpServletResponse)) {  
            throw new ServletException("OncePerRequestFilter just supports HTTP requests");  
        }  
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;  
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;  
        HttpSession session = httpRequest.getSession(true);  
  
        StringBuffer url = httpRequest.getRequestURL();  
        
        filterChain.doFilter(servletRequest, servletResponse);  
        return;  
    }  
  
    /** 
     * 判断是否为Ajax请求 
     * 
     * @param request HttpServletRequest 
     * @return 是true, 否false 
     */  
    public static boolean isAjaxRequest(HttpServletRequest request) {  
        return request.getRequestURI().startsWith("/ajax");  
//        String requestType = request.getHeader("X-Requested-With");  
//        return requestType != null && requestType.equals("XMLHttpRequest");  
    }  

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	
}
