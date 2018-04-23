/**
 * 
 */
package com.pengyang.service;

import java.util.List;

import com.modle.Superiorhospital;

/**
 * @author admin
 * 2017年12月15日上午10:16:16
 */
public interface SuperiorHospitalService {

	public void add(Superiorhospital sh) throws Exception;
	
	public List<Superiorhospital> findAll() throws Exception;
	
	public int findId(String name) throws Exception;
	
	public int count(String name) throws Exception;
}
