
package com.modle;

import java.io.Serializable;

/**
 * @author weipenghui
 * 2017年12月7日下午5:29:23
 */
public class FeedBack implements Serializable {

	private int id;
	private String feed;
	private UserLogin user;
	
	public FeedBack() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public FeedBack(int id, String feed, UserLogin user) {
		super();
		this.id = id;
		this.feed = feed;
		this.user = user;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFeed() {
		return feed;
	}
	public void setFeed(String feed) {
		this.feed = feed;
	}
	public UserLogin getUser() {
		return user;
	}
	public void setUser(UserLogin user) {
		this.user = user;
	}
	
	
	@Override
	public String toString() {
		return "FeedBack [id=" + id + ", feed=" + feed + ", user=" + user + "]";
	}
	
	
}
