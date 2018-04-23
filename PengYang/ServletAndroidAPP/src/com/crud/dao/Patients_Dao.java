package com.crud.dao;

import java.util.List;
import java.util.Map;

import com.modle.Patients;

/*
 * 作者：魏鹏辉
 * 时间：2017.9.15
 * dao层接口,提供数据库操作基本方法接口
 */
public interface Patients_Dao {

	//分页查询方法
	public List<Patients> pageFindAll(Map param)throws Exception;
	//整体查询方法  
	public List<Patients> findAll()throws Exception;
	//单个查询
	public Patients findOne(int id)throws Exception;
	//根据姓名分页查询
	public int findCountByName(String name)throws Exception;
	public List<Patients> findByName(Map param)throws Exception;
	//根据医疗号分页查询
	public int findCountByNum(int num)throws Exception;
	public List<Patients> findByNum(Map param)throws Exception;
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
	
}
