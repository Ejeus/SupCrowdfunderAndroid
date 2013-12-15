package com.supinfo.supcrowdfunder.dao;

public class DaoFactory {
	private DaoFactory() {}
	
	public static UserDao getUserDao() {
		return new UserDao();
	}
}