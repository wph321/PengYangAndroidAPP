/**
 * 
 */
package com.pengyang.service;

import java.util.List;

import com.modle.FeedBack;

/**
 * @author weipenghui
 * 2017年12月8日上午10:21:10
 */
public interface FeedBackService {

	//增加意见
	public void insertFeedBack(FeedBack feed) throws Exception;
	//删除
	public void deleteFeedBack(int id) throws Exception;
	//分页查询
	public List<FeedBack> findFeedBackByPage(int page) throws Exception;
	//查询单个意见
	public FeedBack findFeedBack(int id) throws Exception;
	//记录数查询
	public int FeedBackCount()throws Exception;
	//总页数计算
	public int pageNumber() throws Exception;
}
