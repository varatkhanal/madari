package com.madari.model.request;

import java.time.LocalDate;

public class ExperienceRequestModel {
	private String experienceTitle;
	private String name;
	private String postDescription;
	private String content;
	private LocalDate stat_date;
	private LocalDate end_date;
	
	public String getExperienceTitle() {
		return experienceTitle;
	}
	public void setExperienceTitle(String postTitle) {
		this.experienceTitle = postTitle;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPostDescription() {
		return postDescription;
	}
	public void setPostDescription(String postDescription) {
		this.postDescription = postDescription;
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

}
