/**
 * 
 */
package com.modle;

import java.io.Serializable;

/**
 * @author admin
 * 2017年12月11日下午3:44:21
 */
public class User implements Serializable {

	private String name;
	private String username;
	private String passworld;
	private String hospital;
	private String hospital_sign;
	private Superiorhospital sh;
	private CommunityHospital ch;

	
	public User(String name, String username, String passworld, String hospital, String hospital_sign,
			Superiorhospital sh, CommunityHospital ch) {
		super();
		this.name = name;
		this.username = username;
		this.passworld = passworld;
		this.hospital = hospital;
		this.hospital_sign = hospital_sign;
		this.sh = sh;
		this.ch = ch;
	}
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassworld() {
		return passworld;
	}
	public void setPassworld(String passworld) {
		this.passworld = passworld;
	}
	public String getHospital() {
		return hospital;
	}
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	public String getHospital_sign() {
		return hospital_sign;
	}
	public void setHospital_sign(String hospital_sign) {
		this.hospital_sign = hospital_sign;
	}
	public Superiorhospital getSh() {
		return sh;
	}
	public void setSh(Superiorhospital sh) {
		this.sh = sh;
	}
	public CommunityHospital getCh() {
		return ch;
	}
	public void setCh(CommunityHospital ch) {
		this.ch = ch;
	}
	
	
	
}
