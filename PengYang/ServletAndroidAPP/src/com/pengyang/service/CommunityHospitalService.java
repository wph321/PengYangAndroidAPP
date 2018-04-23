/**
 * 
 */
package com.pengyang.service;

import com.modle.CommunityHospital;

/**
 * @author admin
 * 2017年12月15日上午10:12:13
 */
public interface CommunityHospitalService {

	public void add(CommunityHospital ch) throws Exception;
	
	public int findId(String name) throws Exception;
	
	public int count(String name) throws Exception;
}
