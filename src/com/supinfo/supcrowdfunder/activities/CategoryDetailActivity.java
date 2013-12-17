package com.supinfo.supcrowdfunder.activities;

import java.net.URI;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.supinfo.supcrowdfunder.R;
import com.supinfo.supcrowdfunder.R.layout;
import com.supinfo.supcrowdfunder.R.menu;
import com.supinfo.supcrowdfunder.adapter.CategoryAdapter;
import com.supinfo.supcrowdfunder.adapter.ProjectAdapter;
import com.supinfo.supcrowdfunder.dao.DaoFactory;
import com.supinfo.supcrowdfunder.entity.Category;
import com.supinfo.supcrowdfunder.entity.Project;
import com.supinfo.supcrowdfunder.entity.User;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class CategoryDetailActivity extends Activity {
	
	private ListView listViewProjects;
	final ArrayList<Project> projects = new ArrayList<Project>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category_detail);
		
		Bundle extras = getIntent().getExtras();
		
		TextView categoryName =new TextView(this);
		categoryName = (TextView) findViewById(R.id.categoryName);
		categoryName.setText(extras.getString("categoryName"));
		
		listViewProjects = (ListView) this.findViewById(R.id.listViewCategoryDetail);
		
		
	    JSONObject obj = sendGetRequest(SupCrowdFunderApp.getAppURL() + "/SupCrowdFunder/resources/categories/"+extras.getLong("categoryId")+"/projects");
	   
	    JSONArray array;
		try {
			array = obj.getJSONArray("project");
		    for(int i = 0 ; i < array.length() ; i++){
		    	projects.add(DaoFactory.getProjectDao().parse(array.getJSONObject(i)));
		    }
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		ProjectAdapter adapterProject = new ProjectAdapter(this, projects);
		listViewProjects.setAdapter(adapterProject);
		
		listViewProjects.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(CategoryDetailActivity.this, ProjectDetails.class);
				
				intent.putExtra("projectName",projects.get(arg2).getName());
				intent.putExtra("projectContent",projects.get(arg2).getContent());
				intent.putExtra("projectCreatedAt",projects.get(arg2).getCreatedAt());
				intent.putExtra("projectCurrentFunding",Integer.toString(projects.get(arg2).getCurrentFunding()));
				intent.putExtra("projectGoal",Integer.toString(projects.get(arg2).getGoal()));
				intent.putExtra("projectCreatorFirstname", projects.get(arg2).getCreator().getFirstName());
				intent.putExtra("projectCreatorLastname", projects.get(arg2).getCreator().getLastName());
				
				startActivityForResult(intent, 0);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.category_detail, menu);
		return true;
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
}

