package com.madari.model.response;

import java.util.List;


public class ChapterResponseModel {
	private String title;
    private String description;
    private List<TopicResponseModel> topics;
    
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
	public List<TopicResponseModel> getTopicResponeModel() {
		return topics;
	}
	public void setTopicResponeModel(List<TopicResponseModel> topicResponeModel) {
		this.topics = topicResponeModel;
	}
    
    

}
