package com.supinfo.supcrowdfunder.activities;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.supinfo.supcrowdfunder.R;
import com.supinfo.supcrowdfunder.activities.DrawerItem.DrawerItemType;
import com.supinfo.supcrowdfunder.adapter.DrawerItemAdapter;
import com.supinfo.supcrowdfunder.adapter.ProjectAdapter;
import com.supinfo.supcrowdfunder.dao.DaoFactory;
import com.supinfo.supcrowdfunder.entity.Project;
import com.supinfo.supcrowdfunder.entity.User;

public class MainActivity extends Activity implements OnItemClickListener {
	
	private ListView listViewProjects;
	final ArrayList<Project> projects = new ArrayList<Project>();
	
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ArrayList<DrawerItem> drawerItems;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        
        initDrawerItems();
        
        mDrawerList.setAdapter(new DrawerItemAdapter(this, drawerItems));
        mDrawerList.setOnItemClickListener(this);
		
		
		listViewProjects = (ListView) this.findViewById(R.id.listViewProjects);

	    JSONObject obj = sendGetRequest(SupCrowdFunderApp.getAppURL() + "/SupCrowdFunder/resources/index");

	    
	    
	    Project projectTmp = new Project();
	    User userTmp = new User();
	    JSONArray array;
		try {
			array = obj.getJSONArray("project");
		    for(int i = 0 ; i < array.length() ; i++){
		    	projectTmp = new Project();
		    	projectTmp.setGoal(Integer.parseInt(array.getJSONObject(i).getString("goal")));
		    	projectTmp.setCurrentFunding(Integer.parseInt(array.getJSONObject(i).getString("currentFunding")));
		    	projectTmp.setName(array.getJSONObject(i).getString("name"));
		    	projectTmp.setContent(array.getJSONObject(i).getString("content"));

		    	User user = DaoFactory.getUserDao().parse(array.getJSONObject(i).getJSONObject("creator"));
		    	projectTmp.setCreator(user);
		    	
		    	projects.add(projectTmp);
		    }
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ProjectAdapter adapterProject = new ProjectAdapter(this, projects);
		listViewProjects.setAdapter(adapterProject);
		
		listViewProjects.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(MainActivity.this, ProjectDetails.class);
				
				intent.putExtra("projectName",projects.get(arg2).getName());
				intent.putExtra("projectContent",projects.get(arg2).getContent());
				intent.putExtra("projectCreatedAt",projects.get(arg2).getCreatedAt());
				intent.putExtra("projectCurrentFunding",Integer.toString(projects.get(arg2).getCurrentFunding()));
				intent.putExtra("projectGoal",Integer.toString(projects.get(arg2).getGoal()));
				intent.putExtra("projectCreatorFirstname", projects.get(arg2).getCreator().getFirstName());
				
				startActivityForResult(intent, 0);
			}
		});
	    
	}
	
	public JSONObject sendGetRequest(String address) {
		
		JSONObject myObject = null;
	  try {
	    HttpClient httpClient = new DefaultHttpClient();
	    HttpGet httpGet = new HttpGet();
				
	    URI uri = new URI(address);
	    httpGet.setURI(uri);
				
	    HttpResponse response = httpClient.execute(httpGet);
				
	    String result = EntityUtils.toString(response.getEntity());
	    
	    myObject = new JSONObject(result);
	  } catch (Exception e) {
	   Log.e("LOG_TAG", e.getMessage(), e);
	  }
			
	  return myObject;
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		updateDrawerMenu();
	}
	
	public void updateDrawerMenu() {
		initDrawerItems();
		DrawerItemAdapter adapter = (DrawerItemAdapter) mDrawerList.getAdapter();
		adapter.notifyDataSetChanged();
	}

	public void initDrawerItems(){
		if(drawerItems == null)
			drawerItems = new ArrayList<DrawerItem>();
		else
			drawerItems.removeAll(drawerItems);
		
		
		SupCrowdFunderApp app = (SupCrowdFunderApp) getApplication();
		User user = app.getUser();
		
		
		if(user == null) {
			drawerItems.add(new DrawerItem(DrawerItemType.DrawerItemTypeLogin, "Login"));
			drawerItems.add(new DrawerItem(DrawerItemType.DrawerItemTypeRegister, "Register"));
		}
		else {
			drawerItems.add(new DrawerItem(DrawerItemType.DrawerItemTypeMyProfile, user.getFullName()));
			drawerItems.add(new DrawerItem(DrawerItemType.DrawerItemTypeLogout, "Logout"));
		}
		
		drawerItems.add(new DrawerItem(DrawerItemType.DrawerItemTypeCategories, "Categories"));
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int index, long id) {
		DrawerItem drawerItem = drawerItems.get(index);
		
		switch (drawerItem.getIdentifier()) {
			case DrawerItemTypeLogin:
				Intent intent = new Intent(this, LoginActivity.class);
	        	startActivityForResult(intent, 0);
				break;
			case DrawerItemTypeRegister:
				Intent i = new Intent(this, RegisterActivity.class);
	        	startActivityForResult(i, 0);
	            break;
			case DrawerItemTypeMyProfile:
				Intent profileIntent = new Intent(this, ProfileActivity.class);
	        	startActivityForResult(profileIntent, 0);
	        	break;
			case DrawerItemTypeLogout:
				SupCrowdFunderApp app = (SupCrowdFunderApp) getApplication();
	        	app.logout();
	        	
	        	updateDrawerMenu();
	        	break;
			case DrawerItemTypeCategories:
				Intent categoryIntent = new Intent(this, CategoryActivity.class);
				startActivityForResult(categoryIntent, 0);
	            break;
			default:
				break;
			}
			
	}

}
