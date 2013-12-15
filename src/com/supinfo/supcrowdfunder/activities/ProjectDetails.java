package com.supinfo.supcrowdfunder.activities;

import com.supinfo.supcrowdfunder.R;
import com.supinfo.supcrowdfunder.R.layout;
import com.supinfo.supcrowdfunder.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class ProjectDetails extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_project_details);
		
		Bundle extras = getIntent().getExtras();

		
		TextView projectName=new TextView(this); 
		projectName=(TextView)findViewById(R.id.projectName); 
		projectName.setText(extras.getString("projectName"));
		
		TextView projectContent=new TextView(this); 
		projectContent=(TextView)findViewById(R.id.projectContent); 
		projectContent.setText(extras.getString("projectContent"));
		
		TextView projectGoal=new TextView(this); 
		projectGoal=(TextView)findViewById(R.id.projectGoal); 
		projectGoal.setText(extras.getString("projectGoal"));
		
		TextView projectCurrentFunding=new TextView(this); 
		projectCurrentFunding=(TextView)findViewById(R.id.projectCurrentFunding); 
		projectCurrentFunding.setText(extras.getString("projectCurrentFunding"));
		/*
		TextView projectCreatedAt=new TextView(this); 
		projectCreatedAt=(TextView)findViewById(R.id.projectCreatedAt); 
		projectCreatedAt.setText(extras.getString("projectCreatedAt"));
		*/

		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.project_details, menu);
		return true;
	}

}
