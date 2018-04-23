/**
 * 
 */
package com.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.crud.dao.CommunityHospital_Dao;
import com.modle.CommunityHospital;
import com.pengyang.service.CommunityHospitalService;

/**
 * @author admin
 * 2017年12月15日下午3:27:17
 */
@Service(value="CommunityHospitalService")
public class CommunityHospitalServiceImpl implements CommunityHospitalService {

	@Resource
	private CommunityHospital_Dao chd;
	/* (non-Javadoc)
	 * @see com.pengyang.service.CommunityHospitalService#add(com.modle.CommunityHospital)
	 */
	@Override
	public void add(CommunityHospital ch) throws Exception {
		// TODO Auto-generated method stub

		chd.add(ch);
		
	}
	/* (non-Javadoc)
	 * @see com.pengyang.service.CommunityHospitalService#findId(java.lang.String)
	 */
	@Override
	public int findId(String name) throws Exception {
		// TODO Auto-generated method stub
		return chd.findId(name);
	}
	/* (non-Javadoc)
	 * @see com.pengyang.service.CommunityHospitalService#count(java.lang.String)
	 */
	@Override
	public int count(String name) throws Exception {
		// TODO Auto-generated method stub
		return chd.count(name);
	}

}
