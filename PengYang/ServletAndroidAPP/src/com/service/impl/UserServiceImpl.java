/**
 * 
 */
package com.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.crud.dao.User_Dao;
import com.modle.User;
import com.pengyang.service.UserService;

/**
 * @author admin
 * 2017年12月14日下午4:52:37
 */
@Service(value="UserService")
public class UserServiceImpl implements UserService {

	@Resource
	private User_Dao ud;
	
	/* (non-Javadoc)
	 * @see com.pengyang.service.UserService#addUser(com.modle.User)
	 */
	@Override
	public void addUser(User user) throws Exception {
		// TODO Auto-generated method stub

		ud.addUser(user);
		
	}

}
