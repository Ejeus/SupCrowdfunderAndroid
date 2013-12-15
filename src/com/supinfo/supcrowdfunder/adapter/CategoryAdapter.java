package com.supinfo.supcrowdfunder.adapter;

import java.util.ArrayList;

import com.supinfo.supcrowdfunder.entity.Category;
import com.supinfo.supcrowdfunder.entity.Project;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CategoryAdapter extends BaseAdapter {

	
	private Context context;
	private ArrayList<Category> categories;
	
	public CategoryAdapter(Context context, ArrayList<Category> categories) {
		this.context = context;
		this.categories = categories;
	}
	
	
	@Override
	public int getCount() {
		return categories.size();
	}

	@Override
	public Object getItem(int position) {
		return categories.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}


	public View getView(int position, View convertView, ViewGroup parent) {
		TextView textView = new TextView(context);
		textView.setTextColor(Color.WHITE);
		String strTmp = categories.get(position).getName();
		textView.setTextSize(25);
		textView.setPadding(0, 0, 0, 30);
		textView.setText(strTmp);
		if(position%2 == 0)
			textView.setBackgroundColor(Color.DKGRAY);
		else
			textView.setBackgroundColor(Color.BLACK);
		return textView;
		
	}
	
	
}
