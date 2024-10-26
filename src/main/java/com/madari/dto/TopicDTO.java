package com.madari.dto;

import java.util.List;


public class TopicDTO {
	private Long id;
    private String title;
    private String description;
    private List<TopicDetailDTO> topicDetails;
    
    public TopicDTO(){
    	
    }

    public TopicDTO(Long id, String title, String description, List<TopicDetailDTO> topicDetails) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.topicDetails = topicDetails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<TopicDetailDTO> getTopicDetails() {
        return topicDetails;
    }

    public void setTopicDetails(List<TopicDetailDTO> topicDetails) {
        this.topicDetails = topicDetails;
    }
    
    
}
