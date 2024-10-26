
package com.madari.dto;

import java.util.List;

public class CourseDTO {
	private long id;
	private String name;
	private String courseCode;
	private List<ChapterDTO> chaptersDto;
	  
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
