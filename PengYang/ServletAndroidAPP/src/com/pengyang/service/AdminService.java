package com.pengyang.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.crud.dao.Admin_Dao;
import com.modle.Admin;
public interface AdminService{

	public List<Admin> findAllAdmin();
	
	public Admin findAdminById(int id);
	
	public void insertAdmin(Admin admin);
	
	public void updateAdmin(Admin admin);
	
	public void deleteAdmin(int id);
	
}
