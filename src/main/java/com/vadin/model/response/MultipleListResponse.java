package com.vadin.model.response;

import java.util.List;

import com.vadin.model.Course;
import com.vadin.model.Experience;

public class MultipleListResponse {
	private List<Experience> experienceList;
	private List<Course> courseList;
	
	public List<Experience> getExperienceList() {
		return experienceList;
	}
	public void setExperienceList(List<Experience> experienceList) {
		this.experienceList = experienceList;
	}
	public List<Course> getCourseList() {
		return courseList;
	}
	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}
	
	
	
 }