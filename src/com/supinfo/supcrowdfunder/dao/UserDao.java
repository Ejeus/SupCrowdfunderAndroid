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
import com.supinfo.supcrowdfunder.entity.User;

public class UserDao {

	public UserDao() {
	}
	
	public User getUserWithEmailAndPassword(String email, String password) {
		User user = null; 
		
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost();
			
			URI uri = new URI(SupCrowdFunderApp.getAppURL() + "/SupCrowdFunder/resources/login");
			httpPost.setURI(uri);
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("email", email);
			jsonObject.put("password", password);
			
			StringEntity se = new StringEntity( jsonObject.toString());
			se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			httpPost.setEntity(se);
			
			HttpResponse response = httpClient.execute(httpPost);
			String result = EntityUtils.toString(response.getEntity());
			
			JSONObject jsonResult = new JSONObject(result);
			return parse(jsonResult);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	public User registerUser(User user) {		
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost();
			
			URI uri = new URI(SupCrowdFunderApp.getAppURL() + "/SupCrowdFunder/resources/register");
			httpPost.setURI(uri);
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("email", user.getEmail());
			jsonObject.put("firstName", user.getFirstName());
			jsonObject.put("lastName", user.getLastName());
			jsonObject.put("password", user.getPassword());
			
			StringEntity se = new StringEntity( jsonObject.toString());
			se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			httpPost.setEntity(se);
			
			HttpResponse response = httpClient.execute(httpPost);
			String result = EntityUtils.toString(response.getEntity());
			
			JSONObject jsonResult = new JSONObject(result);
			return parse(jsonResult);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	private User parse(JSONObject jsonResult) {
		User user = new User();
		
		try {
			user.setEmail(jsonResult.getString("email"));
			user.setFirstName(jsonResult.getString("firstName"));
			user.setLastName(jsonResult.getString("lastName"));
			user.setId(jsonResult.getLong("id"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
		return user;
	}

}
