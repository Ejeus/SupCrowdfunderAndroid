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
import com.supinfo.supcrowdfunder.entity.Category;
import com.supinfo.supcrowdfunder.entity.Project;
import com.supinfo.supcrowdfunder.entity.User;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;
import android.widget.TextView;

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

	    
	    
	    Project projectTmp = new Project();
	    User userTmp = new User();
	    JSONArray array;
	    
		try {
			array = obj.getJSONArray("project");
		    for(int i = 0 ; i < array.length() ; i++){
		    	projectTmp = new Project();
		    	userTmp = new User();
		    	System.out.println(array.getJSONObject(i).getString("name"));
		    	projectTmp.setGoal(Integer.parseInt(array.getJSONObject(i).getString("goal")));
		    	projectTmp.setCurrentFunding(Integer.parseInt(array.getJSONObject(i).getString("currentFunding")));
		    	projectTmp.setName(array.getJSONObject(i).getString("name"));
		    	projectTmp.setContent(array.getJSONObject(i).getString("content"));
		    	userTmp.setFirstName(array.getJSONObject(i).getJSONObject("creator").getString("firstname"));
		    	projectTmp.setCreator(userTmp);
		    	
		    	projects.add(projectTmp);
		    }
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		ProjectAdapter adapterProject = new ProjectAdapter(this, projects);
		listViewProjects.setAdapter(adapterProject);
		
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

