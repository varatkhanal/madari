package com.madari.model.response;

import java.util.List;


public class CourseResponseModel {
	//private long id;
	
	private String title;
	private String name;
	private String courseCode;
	private String description;
	private UserDetailResponseModel user;
	private List<ChapterResponseModel> chapters;
	
	public UserDetailResponseModel getUserResponse() {
		return user;
	}
	public void setUserResponse(UserDetailResponseModel userResponse) {
		this.user = userResponse;
	}
	public List<ChapterResponseModel> getChapRespModel() {
		return chapters;
	}
	public void setChapRespModel(List<ChapterResponseModel> chapRespModel) {
		this.chapters = chapRespModel;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
