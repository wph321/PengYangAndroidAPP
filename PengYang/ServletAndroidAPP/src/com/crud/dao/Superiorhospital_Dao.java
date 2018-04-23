/**
 * 
 */
package com.crud.dao;

import java.util.List;

import com.modle.Superiorhospital;

/**
 * @author weipenghui
 * 2017年12月15日上午10:14:41
 */
public interface Superiorhospital_Dao {

	public void add(Superiorhospital sh);
	
	public List<Superiorhospital> findAll();
	
	public int findId(String name);
	
	public int count(String name);
}
