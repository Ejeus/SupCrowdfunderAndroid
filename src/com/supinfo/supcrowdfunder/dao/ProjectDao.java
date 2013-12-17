package com.supinfo.supcrowdfunder.dao;

import org.json.JSONObject;

import com.supinfo.supcrowdfunder.entity.Category;
import com.supinfo.supcrowdfunder.entity.Project;
import com.supinfo.supcrowdfunder.entity.User;

public class ProjectDao {
	
	public ProjectDao() {
	}
	
	public Project parse(JSONObject jsonResult) {
		Project project = new Project();
		
		try {
			project.setName(jsonResult.getString("name"));
			project.setContent(jsonResult.getString("content"));
			project.setGoal(Integer.parseInt(jsonResult.getString("goal")));
			project.setCurrentFunding(Integer.parseInt(jsonResult.getString("currentFunding")));
			User user = DaoFactory.getUserDao().parse(jsonResult.getJSONObject("creator"));
			Category category = DaoFactory.getCategoryDao().parse(jsonResult.getJSONObject("category"));
			project.setCategory(category);
			project.setCreator(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
		return project;
	}

}
