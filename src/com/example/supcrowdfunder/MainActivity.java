package com.example.supcrowdfunder;

import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TextView t = new TextView(this); 

	    t=(TextView)findViewById(R.id.textviewtest); 
	    t.setText(sendGetRequest("http://10.27.18.163:8080/SupCrowdFunder/resources/index"));
	}
	
	public String sendGetRequest(String address) {
	  String result = null;
			
	  try {
	    HttpClient httpClient = new DefaultHttpClient();
	    HttpGet httpGet = new HttpGet();
				
	    URI uri = new URI(address);
	    httpGet.setURI(uri);
				
	    HttpResponse response = httpClient.execute(httpGet);
				
	    result = EntityUtils.toString(response.getEntity());
	  } catch (Exception e) {
	   Log.e("LOG_TAG", e.getMessage(), e);
	  }
			
	  return result;
	}

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
