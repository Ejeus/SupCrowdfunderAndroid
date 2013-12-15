package com.supinfo.supcrowdfunder.activities;

import com.supinfo.supcrowdfunder.entity.User;

import android.app.Application;

public class SupCrowdFunderApp extends Application {

	private User user;
	
	public SupCrowdFunderApp() {
		// TODO Auto-generated constructor stub
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public void logout() {
		this.user = null;
	}

	static public String getAppURL(){
		return "http://192.168.1.10:8080";
	}
	
}
