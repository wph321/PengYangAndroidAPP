package com.pengyang.tools.impl;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.springframework.web.portlet.HandlerExceptionResolver;
import org.springframework.web.portlet.ModelAndView;


/*
 * 错误拦截器：后转入spring配置与web。xml中拦截404与500错误
 * 
 * 作者：魏鹏辉
 * 时间：2017.7.15
 * 作用：拦截404、500错误，进入统一错误页面，统一管理
 */
public class ExceptionDo implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(RenderRequest arg0, RenderResponse arg1, Object arg2, Exception arg3) {
		// TODO Auto-generated method stub
		
		
		
		return null;
	}

	@Override
	public ModelAndView resolveException(ResourceRequest arg0, ResourceResponse arg1, Object arg2, Exception arg3) {
		// TODO Auto-generated method stub
		return null;
	}


}
