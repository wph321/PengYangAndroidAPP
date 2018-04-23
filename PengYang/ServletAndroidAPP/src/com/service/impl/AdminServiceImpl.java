package com.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crud.dao.Admin_Dao;
import com.modle.Admin;
import com.pengyang.service.AdminService;

@Service(value="adminService")
public class AdminServiceImpl implements AdminService {

	@Autowired
	@Resource
	private Admin_Dao adminDao;

	public Admin_Dao getAdminDao() {
		return adminDao;
	}

	public void setAdminDao(Admin_Dao adminDao) {
		this.adminDao = adminDao;
	}

	@Override
	public List<Admin> findAllAdmin() {

		List<Admin> adminList = adminDao.findAllAdmin();
		System.out.println(adminList);
		return adminList;
	}

	@Override
	public Admin findAdminById(int id) {

		Admin admin = adminDao.findAdminById(id);
		return admin;
	}

	@Override
	public void insertAdmin(Admin admin) {

		adminDao.insertAdmin(admin);
	}

	@Override
	public void updateAdmin(Admin admin) {

		adminDao.updateAdmin(admin);
	}

	@Override
	public void deleteAdmin(int id) {

		adminDao.deleteAdmin(id);
		
	}


}
