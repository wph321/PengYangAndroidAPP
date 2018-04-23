package com.crud.dao;

import java.util.List;

import com.modle.UserLogin;

/*
 * author:weipenghui
 * data:2017.9.19
 * dao for Userlogin table interface
 */
public interface UserLogin_Dao {

	public List<UserLogin> findAll()throws Exception;
	
	public UserLogin findOne(String username) throws Exception;
	
	public void addUser(UserLogin user) throws Exception;
	
	public void update(UserLogin user) throws Exception;
	
	public void delete(int id) throws Exception;
	
	public int findCount(String username) throws Exception;
	
}
