/**
 * 
 */
package com.modle;

import java.io.Serializable;

/**
 * @author weipenghui
 * 2017年12月13日下午5:11:44
 */
public class Superiorhospital implements Serializable {

	private int id;
	private String name;
	
	public Superiorhospital(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Superiorhospital() {
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
	
}
