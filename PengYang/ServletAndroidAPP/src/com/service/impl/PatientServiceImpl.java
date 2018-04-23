package com.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.crud.dao.Patient_Dao;
import com.modle.Patient;
import com.pengyang.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {

	@Resource
	private Patient_Dao patientDao;
	
	@Override
	public List<Patient> findAllPatient() {
		
		List<Patient> patientList = patientDao.findAllPatient();
		return patientList;
	}

	@Override
	public Patient findOnePatient(int id) {

		Patient patient = patientDao.findOnePatient(id);
		return patient;
	}

	@Override
	public void insertPatient(Patient pa) {

		patientDao.insertPatient(pa);
		
		
	}

	@Override
	public void updatePatient(Patient pa) {

		patientDao.updatePatient(pa);
		
	}

	@Override
	public void deletePatient(int id) {
		// TODO Auto-generated method stub
		patientDao.deletePatientById(id);
	}

}
