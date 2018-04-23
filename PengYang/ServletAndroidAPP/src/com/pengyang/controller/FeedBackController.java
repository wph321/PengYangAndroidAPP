/**
 * 
 */
package com.pengyang.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.modle.FeedBack;
import com.modle.Patients;
import com.modle.UserLogin;
import com.pengyang.service.FeedBackService;

/**
 * @author weipenghui
 * 2017年12月8日上午11:02:02
 */

@Controller
@RequestMapping(value="/feed")
public class FeedBackController {



	UserLogin user = new UserLogin();
	@Autowired
	private FeedBackService fs;
	
	@RequestMapping(value="/add")
	public String add(String content,HttpSession session){
		user = (UserLogin) session.getAttribute("user");
		FeedBack fb = new FeedBack();
		fb.setFeed(content);
		fb.setUser(user);
		try {
			fs.insertFeedBack(fb);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "feedback";
	}
	
	@RequestMapping(value="/show")
	public String showFeedList(int page,HttpServletRequest request){
		
		
		try {
		int	feedCount = fs.FeedBackCount();
	
		int feedpageNum = fs.pageNumber();
		List<FeedBack> fbList = fs.findFeedBackByPage(page);

		request.setAttribute("fbList", fbList);
		request.setAttribute("fbCount", feedCount);
		request.setAttribute("pagenum", feedpageNum);
		request.setAttribute("nowPage", page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "feedback-list";
	}
	
	
	@RequestMapping(value="/del")
	public String delfeed(int id){
		
		try {
			fs.deleteFeedBack(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/feed/show.do?page=1";
		
		
	}
	
	
	
}
