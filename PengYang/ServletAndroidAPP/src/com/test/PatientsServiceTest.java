package com.test;

import java.util.List;

import com.modle.Patients;
import com.pengyang.service.PatientsService;
import com.service.impl.PatientsServiceImpl;

public class PatientsServiceTest {

	public static void main(String[] args) {
		
		PatientsServiceImpl ps = new PatientsServiceImpl();
		
		try {
			List<Patients> lp = ps.findAll();
			System.out.println(lp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
