package com.example.supcrowdfunder;

import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.supinfo.supcrowdfunder.entity.Project;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private ListView listViewProjects;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Project project = new Project();
		
		listViewProjects = (ListView) this.findViewById(R.id.listViewProjects);
		
	    JSONObject obj= sendGetRequest("http://192.168.0.13:8080/SupCrowdFunder/resources/index");

	    ArrayList<String> list = new ArrayList<String>();
	    JSONArray array;
		try {
			array = obj.getJSONArray("project");
		    for(int i = 0 ; i < array.length() ; i++){
		        list.add(array.getJSONObject(i).getString("name"));
		    }
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, list);
		listViewProjects.setAdapter(adapter);
	    
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
