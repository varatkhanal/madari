package com.madari.services;

import java.util.List;

import com.madari.model.Topic;
import com.madari.model.TopicDetail;


public interface TopicDetailService {
	TopicDetail getTopicDetailById(Long id);
    TopicDetail saveTopicDetail(TopicDetail topicDetail);
    void deleteTopicDetail(Long id);
    void deleteByTopic(Topic topic);
	List<TopicDetail> getTopicDetailsByTopic(Topic topic);
	TopicDetail updateTopicDetail(long id, TopicDetail topicDetail);
	TopicDetail getTopicDetailsByTopicId(Long topicId);
}
