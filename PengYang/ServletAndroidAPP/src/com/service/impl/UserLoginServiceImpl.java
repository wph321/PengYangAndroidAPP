package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.dao.UserLogin_Dao;
import com.modle.UserLogin;
import com.pengyang.service.UserLoginService;


@Service(value="userLogin")
public class UserLoginServiceImpl implements UserLoginService {

	@Autowired
	private UserLogin_Dao userDao;
	
	@Override
	public List<UserLogin> findAll() throws Exception {

		return userDao.findAll();
	}

	@Override
	public UserLogin findOne(String username) throws Exception {

		return userDao.findOne(username);
	}

	@Override
	public void add(UserLogin user) throws Exception {

		userDao.addUser(user);
	}

	@Override
	public void update(UserLogin user) throws Exception {

		userDao.update(user);
	}

	@Override
	public void delete(int id) throws Exception {

		userDao.delete(id);
	}

	@Override
	public int findCount(String username) throws Exception {

		return userDao.findCount(username);
	}

}
