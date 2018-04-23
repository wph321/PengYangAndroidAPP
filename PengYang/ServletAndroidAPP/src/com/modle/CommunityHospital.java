/**
 * 
 */
package com.modle;

import java.io.Serializable;

/**
 * @author admin
 * 2017年12月13日下午5:12:51
 */
public class CommunityHospital implements Serializable {

	private int id;
	private String name;
	private Superiorhospital sh;
	
	public CommunityHospital(int id, String name, Superiorhospital sh) {
		super();
		this.id = id;
		this.name = name;
		this.sh = sh;
	}
	
	public CommunityHospital() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Superiorhospital getSh() {
		return sh;
	}
	public void setSh(Superiorhospital sh) {
		this.sh = sh;
	}
	
}
