package com.test;

import com.pengyang.service.PatientService;
import com.service.impl.PatientServiceImpl;

public class PatientServiceTest {

	
	public static void main(String[] args) {
		
	PatientService ps = new PatientServiceImpl();
	
	System.out.println(ps.findAllPatient());
	
	}
	
}
