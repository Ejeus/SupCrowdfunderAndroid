package com.supinfo.supcrowdfunder.adapter;

import java.util.ArrayList;

import com.supinfo.supcrowdfunder.activities.DrawerItem;

import android.R;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DrawerItemAdapter extends BaseAdapter {

	Context mContext;
	ArrayList<DrawerItem> mDrawerItems;
	
	public DrawerItemAdapter(Context context, ArrayList<DrawerItem> drawerItems) {
		this.mContext = context;
		this.mDrawerItems = drawerItems;
	}

	@Override
	public int getCount() {
		return mDrawerItems.size();
	}

	@Override
	public Object getItem(int position) {
		return mDrawerItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		
		if(view == null) {
			LayoutInflater viewInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = viewInflater.inflate(R.layout.simple_list_item_1, null);
		}
		
		DrawerItem item = (DrawerItem) getItem(position);
		
		TextView itemTextView = (TextView) view.findViewById(R.id.text1);
		itemTextView.setText(item.getTitle());

		return view;
	}

}
