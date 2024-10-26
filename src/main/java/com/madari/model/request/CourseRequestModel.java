package com.madari.model.request;

import javax.validation.constraints.NotBlank;

public class CourseRequestModel {
	
	@NotBlank(message = "Title is required")
	private String postTitle;
	@NotBlank(message = "Name is required")
	private String name;
	private String courseCode;
	private String postDescription;
	
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String title) {
		this.postTitle = title;
	}
	public String getPostDescription() {
		return postDescription;
	}
	public void getPostDescription(String description) {
		this.postDescription = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

}
