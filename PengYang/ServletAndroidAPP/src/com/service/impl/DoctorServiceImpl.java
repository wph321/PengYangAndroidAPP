package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.dao.Doctor_Dao;
import com.modle.Doctor;
import com.pengyang.service.DoctorService;


@Service("doctorService")
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	private Doctor_Dao doctorDao;
	
	@Override
	public List<Doctor> findAllDoctor() {

		return doctorDao.findAllDoctor();
	}

	@Override
	public Doctor findDoctorById(int id) {

		return doctorDao.findDoctorById(id);
	}

	@Override
	public void insertDoctor(Doctor doctor) {

		doctorDao.insertDoctor(doctor);
		
	}

	@Override
	public void updateDoctor(Doctor doctor) {

		doctorDao.updateDoctor(doctor);
		
	}

	@Override
	public void deleteDoctor(int id) {

		doctorDao.deleteDoctor(id);
		
	}

}
