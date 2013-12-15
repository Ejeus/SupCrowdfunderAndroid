package com.supinfo.supcrowdfunder.activities;

import com.supinfo.supcrowdfunder.R;
import com.supinfo.supcrowdfunder.entity.User;

import android.app.Activity;
import android.os.Bundle;
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

}
