package com.supinfo.supcrowdfunder.entity;

import java.sql.Date;

public class Fund {

	private Long id;
	private Date fundedAt;
	private Integer amount;
	
	private User funder;
	
	private Project project;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getFundedAt() {
		return fundedAt;
	}
	public void setFundedAt(Date fundedAt) {
		this.fundedAt = fundedAt;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public User getFunder() {
		return funder;
	}
	public void setFunder(User funder) {
		this.funder = funder;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
}
