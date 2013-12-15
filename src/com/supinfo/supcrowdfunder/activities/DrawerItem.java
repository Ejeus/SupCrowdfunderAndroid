package com.supinfo.supcrowdfunder.activities;

public class DrawerItem {
	public enum DrawerItemType {
		DrawerItemTypeLogin,
		DrawerItemTypeLogout,
		DrawerItemTypeRegister,
		DrawerItemTypeMyProfile,
		DrawerItemTypeCategories
	}
	
	private DrawerItemType identifier;
	private String title;

	public DrawerItem(DrawerItemType identifier, String title) {
		this.identifier = identifier;
		this.title = title;
	}

	public DrawerItemType getIdentifier() {
		return identifier;
	}

	public String getTitle() {
		return title;
	}

	
}
