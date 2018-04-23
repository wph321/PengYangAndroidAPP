package com.pengyang.service;

import java.util.List;

import com.modle.Patient;

public interface PatientService {

	public List<Patient> findAllPatient();
	
	public Patient findOnePatient(int id);
	
	public void insertPatient(Patient pa);
	
	public void updatePatient(Patient pa);
	
	public void deletePatient(int id);
	
}
