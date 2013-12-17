package com.supinfo.supcrowdfunder.activities;

import com.supinfo.supcrowdfunder.R;
import com.supinfo.supcrowdfunder.R.layout;
import com.supinfo.supcrowdfunder.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
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
		projectName.setTextColor(Color.parseColor("#C0392B"));
		projectName.setText(extras.getString("projectName"));
		
		TextView projectContent=new TextView(this); 
		projectContent=(TextView)findViewById(R.id.projectContent);
		projectContent.setTextColor(Color.parseColor("#E74C3C"));
		projectContent.setText(extras.getString("projectContent"));
		
		TextView projectCategoryName=new TextView(this); 
		projectCategoryName=(TextView)findViewById(R.id.projectCategoryName); 
		projectCategoryName.setTextColor(Color.parseColor("#BDC3C7"));
		projectCategoryName.setText(extras.getString("projectCategoryName"));
		
		TextView projectCreator=new TextView(this); 
		projectCreator=(TextView)findViewById(R.id.projectCreator); 
		projectCreator.setTextColor(Color.parseColor("#BDC3C7"));
		projectCreator.setText(extras.getString("projectCreatorFirstname") + " " + extras.getString("projectCreatorLastname"));
		
		TextView projectGoal=new TextView(this); 
		projectGoal=(TextView)findViewById(R.id.projectGoal); 
		projectGoal.setText("Objectif : " + extras.getString("projectGoal") + " Û ");
		
		TextView projectCurrentFunding=new TextView(this); 
		projectCurrentFunding=(TextView)findViewById(R.id.projectCurrentFunding); 
		projectCurrentFunding.setText("Avancement : " +extras.getString("projectCurrentFunding") + " Û ");
		

		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.project_details, menu);
		return true;
	}

}
