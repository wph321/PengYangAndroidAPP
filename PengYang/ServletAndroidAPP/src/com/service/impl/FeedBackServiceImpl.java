/**
 * 
 */
package com.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.crud.dao.FeedBack_Dao;
import com.modle.FeedBack;
import com.pengyang.service.FeedBackService;

/**
 * @author admin
 * 2017年12月8日上午10:39:29
 */
@Service(value="FeedBackService")
public class FeedBackServiceImpl implements FeedBackService {

	@Resource
	private volatile FeedBack_Dao fbd;
	
	
	/* (non-Javadoc)
	 * @see com.pengyang.service.FeedBackService#insertFeedBack(com.modle.FeedBack)
	 */
	@Override
	public synchronized void insertFeedBack(FeedBack feed) throws Exception {
		// TODO Auto-generated method stub

		fbd.insertFeedBack(feed);
	}

	/* (non-Javadoc)
	 * @see com.pengyang.service.FeedBackService#deleteFeedBack(int)
	 */
	@Override
	public synchronized void deleteFeedBack(int id) throws Exception {
		// TODO Auto-generated method stub

		fbd.deleteFeedBack(id);
	}

	/* (non-Javadoc)
	 * @see com.pengyang.service.FeedBackService#findFeedBackByPage()
	 */
	@Override
	public List<FeedBack> findFeedBackByPage(int page) throws Exception {
		// TODO Auto-generated method stub
		int pages = (page-1)*8;
		
		Map param = new HashMap();
		param.put("start", pages);
		param.put("end", 8);
		return fbd.findAllFeedBack(param);
	}

	/* (non-Javadoc)
	 * @see com.pengyang.service.FeedBackService#findFeedBack(int)
	 */
	@Override
	public FeedBack findFeedBack(int id) throws Exception {
		// TODO Auto-generated method stub
		return fbd.findOneFeedBack(id);
	}

	/* (non-Javadoc)
	 * @see com.pengyang.service.FeedBackService#FeedBackCount()
	 */
	@Override
	public int FeedBackCount() throws Exception {
		// TODO Auto-generated method stub
		return fbd.feedBackCount();
	}

	/* (non-Javadoc)
	 * @see com.pengyang.service.FeedBackService#pageNumber()
	 */
	@Override
	public int pageNumber() throws Exception {
		int all = fbd.feedBackCount();
		int num = all%8;
		int page = all/8;
		if(all<8){
			return 1;
		}else{
		if(num==0){
			return page;
		}else{
		return page+1;
			}
		}
	}

}
