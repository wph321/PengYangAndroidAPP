package com.pengyang.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.modle.UserLogin;

/*
 * author:weipenghui 
 * data:2017.7.19
 * Service for UserLogin_Dao
 */

public interface UserLoginService {

	public List<UserLogin> findAll() throws Exception;
	
	public UserLogin findOne(String username) throws Exception;
	
	public void add(UserLogin user) throws Exception;
	
	public void update(UserLogin user) throws Exception;
	
	public void delete(int id)throws Exception;
	
	public int findCount(String username) throws Exception;
	
}
