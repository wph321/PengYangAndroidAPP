package com.crud.dao;

import java.util.List;
import com.modle.Admin;

public interface Admin_Dao {

	public List<Admin> findAllAdmin();
	
	public Admin findAdminById(int id);
	
	public void insertAdmin(Admin admin);
	
	public void updateAdmin(Admin admin);
	
	public void deleteAdmin(int id);
	
}
