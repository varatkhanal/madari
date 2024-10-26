package com.madari.model.response;

import java.time.LocalDate;

public class ExperienceResponseModel {
	private String title;
	private String name;
	private String description;
	private String content;
	private LocalDate stat_date;
	private LocalDate end_date;
	private UserDetailResponseModel userRespModel;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LocalDate getStat_date() {
		return stat_date;
	}
	public void setStat_date(LocalDate stat_date) {
		this.stat_date = stat_date;
	}
	public LocalDate getEnd_date() {
		return end_date;
	}
	public void setEnd_date(LocalDate end_date) {
		this.end_date = end_date;
	}
	public UserDetailResponseModel getUserRespModel() {
		return userRespModel;
	}
	public void setUserRespModel(UserDetailResponseModel userRespModel) {
		this.userRespModel = userRespModel;
	}	

}
