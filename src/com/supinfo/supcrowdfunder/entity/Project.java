package com.supinfo.supcrowdfunder.entity;

import java.sql.Date;
import java.util.Collection;


public class Project {
	private Long id;
	private String name;

	private String content;
	
	private Date createdAt;
	
	private Date limitDate;
	
	private Integer goal;
	private Integer currentFunding = 0;
	
	private User creator;

	private Category category;
	
	
	private Collection<Fund> funds;

	public Collection<Fund> getFunds() {
		return funds;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	
	public Date getLimitDate() {
		return limitDate;
	}

	public void setLimitDate(Date limitDate) {
		this.limitDate = limitDate;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getGoal() {
		return goal;
	}

	public void setGoal(Integer goal) {
		this.goal = goal;
	}
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Integer getCurrentFunding() {
		return currentFunding;
	}

	public void setCurrentFunding(Integer currentFunding) {
		this.currentFunding = currentFunding;
	}
}
