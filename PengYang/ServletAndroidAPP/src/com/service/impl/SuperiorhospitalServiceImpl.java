/**
 * 
 */
package com.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.crud.dao.Superiorhospital_Dao;
import com.modle.Superiorhospital;
import com.pengyang.service.SuperiorHospitalService;

/**
 * @author admin
 * 2017年12月15日下午2:37:03
 */
@Service
public class SuperiorhospitalServiceImpl implements SuperiorHospitalService {

	@Resource
	private Superiorhospital_Dao shd;
	/* (non-Javadoc)
	 * @see com.pengyang.service.SuperiorHospitalService#add(com.modle.Superiorhospital)
	 */
	@Override
	public void add(Superiorhospital sh){
		// TODO Auto-generated method stub
			shd.add(sh);
		
	}

	/* (non-Javadoc)
	 * @see com.pengyang.service.SuperiorHospitalService#findAll()
	 */
	@Override
	public List<Superiorhospital> findAll() {
		// TODO Auto-generated method stub
		
		return shd.findAll();
	}

	/* (non-Javadoc)
	 * @see com.pengyang.service.SuperiorHospitalService#findId()
	 */
	@Override
	public int findId(String name) {
		// TODO Auto-generated method stub
			int id = shd.findId(name);
			return id;
		
	}

	/* (non-Javadoc)
	 * @see com.pengyang.service.SuperiorHospitalService#count(java.lang.String)
	 */
	@Override
	public int count(String name) throws Exception {
		// TODO Auto-generated method stub
		
		return shd.count(name);
	}

}
