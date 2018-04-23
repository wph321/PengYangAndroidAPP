/**
 * 
 */
package com.crud.dao;

import com.modle.CommunityHospital;

/**
 * @author admin
 * 2017年12月15日上午10:10:30
 */
public interface CommunityHospital_Dao {

	public void add(CommunityHospital ch);
	
	public int findId(String name);
	
	public int count(String name);
}
