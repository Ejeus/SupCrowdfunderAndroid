package com.supinfo.supcrowdfunder.entity;

import java.util.Collection;


public class Category {
	
	private Long id;
	private String name;
	
	private Collection<Project> projects;
	
	
	public Collection<Project> getProjects() {
		return projects;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
