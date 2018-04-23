package com.crud.dao;

import java.util.List;

import com.modle.Doctor;

public interface Doctor_Dao {

	public List<Doctor> findAllDoctor();
	
	public Doctor findDoctorById(int id);
	
	public void insertDoctor(Doctor doctor);
	
	public void updateDoctor(Doctor doctor);
	
	public void deleteDoctor(int id);
	
}
