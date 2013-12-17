package com.supinfo.supcrowdfunder.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.supinfo.supcrowdfunder.entity.Project;

public class ProjectAdapter extends BaseAdapter {
	
	private Context context;
	private ArrayList<Project> projects;
	
	public ProjectAdapter(Context context, ArrayList<Project> projects) {
		this.context = context;
		this.projects = projects;
	}
	
	public void setQuotes(ArrayList<Project> projects) {
		this.projects = projects;
	}

	@Override
	public int getCount() {
		return projects.size();
	}

	@Override
	public Object getItem(int position) {
		return projects.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		float currentFunding = projects.get(position).getCurrentFunding();
		float goal = projects.get(position).getGoal();
		float percentage  = (currentFunding/goal)*100;
		String strTmp = projects.get(position).getName() + " \n" + projects.get(position).getCreator().getFullName() +"\n"+ percentage + "%" ;
		
		TextView textView = new TextView(context);
		textView.setTextColor(Color.WHITE);
		textView.setTextSize(18);
		textView.setPadding(30, 30, 30, 30);
		
		textView.setText(strTmp);
		if(position%2 == 0)
			textView.setBackgroundColor(Color.parseColor("#3498DB"));
		else
			textView.setBackgroundColor(Color.parseColor("#2980B9"));
		return textView;
		
	}

}
