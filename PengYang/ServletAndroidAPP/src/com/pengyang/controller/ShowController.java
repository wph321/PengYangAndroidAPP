package com.pengyang.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.modle.Doctor;
import com.pengyang.service.DoctorService;

@Controller
@RequestMapping("/show")
public class ShowController {

	@Resource
	private DoctorService doctorService;
	
	@RequestMapping("/doctor")
	public String showDoctor(){
		
		List<Doctor> doctorList = doctorService.findAllDoctor();
		System.out.println(doctorList);
		return "user-list";
	}
	
	
	
	
}
