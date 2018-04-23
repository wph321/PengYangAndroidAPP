package com.modle;

import java.io.Serializable;

/*
 * author：weipenghui
 * data:217.9.19
 * userLogin---modle
 */
public class UserLogin implements Serializable {

	private int id;
	private String userName;
	private String password;
	
	public UserLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserLogin(int id, String userName, String password) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "UserLogin [id=" + id + ", userName=" + userName + ", password=" + password + "]";
	}
	
	
	
	
}
