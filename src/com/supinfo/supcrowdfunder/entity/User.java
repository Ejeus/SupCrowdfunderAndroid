package com.supinfo.supcrowdfunder.entity;

import java.sql.Date;
import java.util.Collection;


public class User {
	private Long id;
	private String email;
	private String firstName;
	private String LastName;
	private String password;
	
	private Date createdDate;
	
	private Boolean admin = false;
	
	private Collection<Project> projects;
	
	private Collection<Fund> funds;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getAdmin() {
		return admin;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	
	public String getFullName(){
		return this.getFirstName() + " " + this.getLastName();
	}
	
	public boolean equals(User user) {
		return getId() == user.getId(); 
	}

	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
}
