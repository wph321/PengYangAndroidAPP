package com.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.crud.dao.Patients_Dao;
import com.modle.Patients;
import com.pengyang.service.PatientsService;

@Service(value="patientsService")
public class PatientsServiceImpl implements PatientsService {

	@Resource
	private Patients_Dao patientsdao; 

	public Patients_Dao getPatientsdao() {
		return patientsdao;
	}
	
	public void setPatientsdao(Patients_Dao patientsdao) {
		this.patientsdao = patientsdao;
	}

	@Override
	public List<Patients> pageFindAll(int page) throws Exception {

		int pages = (page-1)*8;
		
		Map param = new HashMap();
		param.put("start", pages);
		param.put("end", 8);
		
		
		return patientsdao.pageFindAll(param);
	}

	@Override
	public List<Patients> findAll() throws Exception {

		List<Patients> pList = patientsdao.findAll();
		return pList;
	}


	@Override
	public Patients findOne(int id) throws Exception {
		// TODO Auto-generated method stub
		return patientsdao.findOne(id);
	}

	@Override
	public List<Patients> fuzzyQuery(Patients patients) throws Exception {
		// TODO Auto-generated method stub
		return patientsdao.fuzzyQuery(patients);
	}

	@Override
	public void add(Patients patients) throws Exception {
		// TODO Auto-generated method stub
		patientsdao.add(patients);
	}

	@Override
	public void delete(int id) throws Exception {
		// TODO Auto-generated method stub
		patientsdao.delete(id);
	}

	@Override
	public void update(Patients patients) throws Exception {
		// TODO Auto-generated method stub
		patientsdao.update(patients);
	}

	@Override
	public int patientCount() throws Exception {
		// TODO Auto-generated method stub
		return patientsdao.patientCount();
	}
	
	@Override
	public int pageNumber() throws Exception{
		int all = patientsdao.patientCount();
		int num = all%8;
		int page = all/8;
		if(all<8){
			return 1;
		}else{
		if(num==0){
			return page;
		}else{
		return page+1;
			}
		}
	}

	@Override
	public Map<String,Object> findByname(String name,int page) throws Exception {
		int pages = (page-1)*8;
		int count = patientsdao.findCountByName(name);
		Map param = new HashMap();
		param.put("start", pages);
		param.put("end", 8);
		param.put("name", name);
		Map<String,Object> nameMap = new HashMap();
		int num = count%8;
		int pagenum = count/8;
		if(count<8){
			nameMap.put("allpage", 1);
		}else{
		if(num==0){
			nameMap.put("allpage", pagenum);
		}else{
		int numpage = pagenum+1;
			nameMap.put("allpage", numpage);
			}
		}
		nameMap.put("count",count);
		nameMap.put("patient", patientsdao.findByName(param));
		return nameMap;
	}

}
