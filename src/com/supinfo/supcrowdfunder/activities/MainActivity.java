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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.supinfo.supcrowdfunder.R;
import com.supinfo.supcrowdfunder.adapter.ProjectAdapter;
import com.supinfo.supcrowdfunder.entity.Project;
import com.supinfo.supcrowdfunder.entity.User;

public class MainActivity extends Activity {
	
	private ListView listViewProjects;
	final ArrayList<Project> projects = new ArrayList<Project>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		listViewProjects = (ListView) this.findViewById(R.id.listViewProjects);

	    JSONObject obj = sendGetRequest("http://192.168.1.13:8080/SupCrowdFunder/resources/index");

	    
	    
	    Project projectTmp = new Project();
	    JSONArray array;
		try {
			array = obj.getJSONArray("project");
		    for(int i = 0 ; i < array.length() ; i++){
		    	projectTmp = new Project();
		    	projectTmp.setGoal(Integer.parseInt(array.getJSONObject(i).getString("goal")));
		    	projectTmp.setCurrentFunding(Integer.parseInt(array.getJSONObject(i).getString("currentFunding")));
		    	projectTmp.setName(array.getJSONObject(i).getString("name"));
		    	projectTmp.setContent(array.getJSONObject(i).getString("content"));
		    	
		    	projects.add(projectTmp);
		    }
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, list);
		listViewProjects.setAdapter(adapter);*/
		
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
	
	
	@SuppressLint("NewApi")
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		invalidateOptionsMenu();
	}


	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		menu.clear();
		
		SupCrowdFunderApp app = (SupCrowdFunderApp) getApplication();
		User user = app.getUser();
		
		
		if(user == null) {
			getMenuInflater().inflate(R.menu.main, menu);
		}
		else {
			getMenuInflater().inflate(R.menu.logged_in, menu);
		}
		
		return true;
	}
	
	@SuppressLint("NewApi")
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.login:
	        	Intent intent = new Intent(this, LoginActivity.class);
	        	startActivityForResult(intent, 0);
	            return true;
	        case R.id.register:
	        	Intent i = new Intent(this, RegisterActivity.class);
	        	startActivityForResult(i, 0);
	            return true;
	        case R.id.my_profile:
	        	Intent profileIntent = new Intent(this, ProfileActivity.class);
	        	startActivityForResult(profileIntent, 0);
	            return true;
	        case R.id.logout:
	        	SupCrowdFunderApp app = (SupCrowdFunderApp) getApplication();
	        	app.logout();
	        	
	        	invalidateOptionsMenu();
	        	return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

}
