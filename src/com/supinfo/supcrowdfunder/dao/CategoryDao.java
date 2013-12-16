package com.supinfo.supcrowdfunder.dao;

import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.supinfo.supcrowdfunder.activities.SupCrowdFunderApp;
import com.supinfo.supcrowdfunder.entity.Category;
import com.supinfo.supcrowdfunder.entity.User;

public class CategoryDao {

	public CategoryDao() {
	}
	public Category getCategoryWithId(Long id) {
		Category category = null;
		
		return category;
	}
	
	public Category parse(JSONObject jsonResult) {
		Category category = new Category();
		
		try {
			category.setName(jsonResult.getString("name"));
			category.setId(jsonResult.getLong("id"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return category;
	}

}
