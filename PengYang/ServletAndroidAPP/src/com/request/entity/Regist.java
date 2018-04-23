/**
 * 
 */
package com.request.entity;

/**
 * @author admin
 * 2017年12月15日上午10:22:13
 */
public class Regist {

	private String name;
	private String username;
	private String password;
	private String hospital;
	private String hospital_sign;
	private String superiorhospital;
	
	
	public Regist(String name, String username, String password, String hospital, String hospital_sign,
			String superiorhospital) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.hospital = hospital;
		this.hospital_sign = hospital_sign;
		this.superiorhospital = superiorhospital;
	}
	
	
	public Regist() {
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getSuperiorhospital() {
		return superiorhospital;
	}
	public void setSuperiorhospital(String superiorhospital) {
		this.superiorhospital = superiorhospital;
	}
	
	
	@Override
	public String toString() {
		return "Regist [name=" + name + ", username=" + username + ", password=" + password + ", hospital=" + hospital
				+ ", hospital_sign=" + hospital_sign + ", superiorhospital=" + superiorhospital + "]";
	}
	
	
	
}
