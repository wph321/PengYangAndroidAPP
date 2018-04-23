package com.pengyang.service;

import java.util.List;
import java.util.Map;

import com.modle.Patients;

/*
 * 作者：魏鹏辉
 * 时间2017.9.15
 * patients业务接口
 * 
 */
public interface PatientsService {

	//分页查询方法
		public List<Patients> pageFindAll(int page)throws Exception;
		//整体查询方法  
		public List<Patients> findAll()throws Exception;
		//单个查询
		public Patients findOne(int id)throws Exception;//id
		public Map<String,Object> findByname(String name,int page)throws Exception;//name
		//模糊查询
		public List<Patients> fuzzyQuery(Patients patients)throws Exception;
		//增加方法
		public void add(Patients patients)throws Exception;
		//删除方法
		public void delete(int id)throws Exception;
		//修改方法
		public void update(Patients patients)throws Exception;
		//记录数查询
		public int patientCount()throws Exception;
		//总页数计算
		public int pageNumber() throws Exception;
	
}
