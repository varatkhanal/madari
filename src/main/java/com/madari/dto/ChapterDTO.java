package com.madari.dto;

import java.util.ArrayList;
import java.util.List;


public class ChapterDTO {
    //private Long id;
    private CourseDTO courseDto;
    private String title;
    private String description;
    private List<TopicDTO> topicDtos = new ArrayList<>();
	public CourseDTO getCourseDto() {
		return courseDto;
	}
	public void setCourseDto(CourseDTO courseDto) {
		this.courseDto = courseDto;
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
	public List<TopicDTO> getTopicDtos() {
		return topicDtos;
	}
	public void setTopicDtos(List<TopicDTO> topicDtos) {
		this.topicDtos = topicDtos;
	}
    
    
    

}
