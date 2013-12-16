package com.supinfo.supcrowdfunder.activities;

import com.supinfo.supcrowdfunder.R;
import com.supinfo.supcrowdfunder.entity.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ProfileActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		
		SupCrowdFunderApp app = (SupCrowdFunderApp)getApplication();
		User user = app.getUser();
		
		TextView fullNameView = (TextView) findViewById(R.id.full_name);
		fullNameView.setText(user.getFullName());
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
	
		
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	            
	        case R.id.edit:
	        	Intent profileIntent = new Intent(this, EditProfileActivity.class);
	        	startActivityForResult(profileIntent, 0);
	            return true;
	            
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}


}
