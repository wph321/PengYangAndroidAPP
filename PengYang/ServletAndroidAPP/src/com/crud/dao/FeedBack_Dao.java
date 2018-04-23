package com.crud.dao;

import java.util.List;
import java.util.Map;

import com.modle.FeedBack;

/**
 * interface dao for feedback
 * @author weipenghui
 * 2017年12月7日下午5:26:21
 */
public interface FeedBack_Dao {

	public void insertFeedBack(FeedBack feedBack) throws Exception;
	
	public void deleteFeedBack(int id) throws Exception;
	
	public List<FeedBack> findAllFeedBack(Map param) throws Exception;
	
	public FeedBack findOneFeedBack(int id) throws Exception;
	
	public int feedBackCount() throws Exception;
}
