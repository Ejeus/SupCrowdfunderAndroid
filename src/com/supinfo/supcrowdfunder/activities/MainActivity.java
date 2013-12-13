package com.supinfo.supcrowdfunder.activities;

import java.io.PrintWriter;
import java.io.Serializable;
import java.lang.reflect.Array;
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


import com.supinfo.supcrowdfunder.R;
import com.supinfo.supcrowdfunder.adapter.ProjectAdapter;
import com.supinfo.supcrowdfunder.entity.Project;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {
	
	private ListView listViewProjects;
	private ProjectAdapter adapterProject;
	ArrayList<Project> projects = new ArrayList<Project>();
	private LinearLayout linearLayoutProjects;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		listViewProjects = (ListView) this.findViewById(R.id.listViewProjects);
		
	    JSONObject obj = sendGetRequest("http://192.168.0.13:8080/SupCrowdFunder/resources/index");
	    
	    JSONArray array;
		try {
			array = obj.getJSONArray("project");
		    for(int i = 0 ; i < array.length() ; i++){
		    	//projects.add(array.getJSONObject(i).getString("name"));
		    	addProject(array.getJSONObject(i).getString("name"));
		    }
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, list);
		listViewProjects.setAdapter(adapter);
		
		adapterProject = new ProjectAdapter(this, projects);
		listViewProjects.setAdapter(adapterProject);*/
		
		listViewProjects.setVisibility(View.GONE);
		
		listViewProjects.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(MainActivity.this, ProjectDetails.class);
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
	
	public void addProject(String strProject) {
		Project project = new Project();
		project.setName(strProject);
		projects.add(project);
		
		TextView textView = new TextView(this);
		textView.setText(strProject);
		if(projects.size()%2 == 1)
			textView.setBackgroundColor(Color.BLUE);
		else
			textView.setBackgroundColor(Color.GREEN);
		linearLayoutProjects.addView(textView);
	}


	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
