package com.supinfo.supcrowdfunder.activities;
import com.supinfo.supcrowdfunder.adapter.CategoryAdapter;
import com.supinfo.supcrowdfunder.adapter.ProjectAdapter;
import com.supinfo.supcrowdfunder.entity.Category;
import com.supinfo.supcrowdfunder.entity.Project;

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


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class CategoryActivity extends Activity {

	private ListView listViewCategories;
	final ArrayList<Category> categories = new ArrayList<Category>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category);
		
		listViewCategories = (ListView) this.findViewById(R.id.listViewCategories);
		JSONObject obj = sendGetRequest(SupCrowdFunderApp.getAppURL() + "/SupCrowdFunder/resources/categories");
		
		Category categoryTmp = new Category();
	    JSONArray array;
		try {
			array = obj.getJSONArray("category");
		    for(int i = 0 ; i < array.length() ; i++){
		    	categoryTmp = new Category();
		    	categoryTmp.setName(array.getJSONObject(i).getString("name"));
		    	categories.add(categoryTmp);
		    }
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		CategoryAdapter adapterCategory = new CategoryAdapter(this, categories);
		listViewCategories.setAdapter(adapterCategory);
		
		
		listViewCategories.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(CategoryActivity.this, CategoryDetailActivity.class);
				
				intent.putExtra("categoryName", categories.get(arg2).getName());
				intent.putExtra("categoryId", categories.get(arg2).getId());
				
				startActivityForResult(intent, 0);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.category, menu);
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
