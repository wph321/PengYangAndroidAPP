package com.crud.dao;

import java.util.List;

import com.modle.Patient;

public interface Patient_Dao {

	public List<Patient> findAllPatient();
	
	public Patient findOnePatient(int id);
	
	public void insertPatient(Patient pa);
	
	public void updatePatient(Patient pa);
	
	public void deletePatientById(int id);
	
}
