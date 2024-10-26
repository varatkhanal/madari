package com.vadin.services;

import java.util.List;

import com.vadin.model.Topic;
import com.vadin.model.TopicDetail;


public interface TopicDetailService {
	TopicDetail getTopicDetailById(Long id);
    TopicDetail saveTopicDetail(TopicDetail topicDetail);
    void deleteTopicDetail(Long id);
    void deleteByTopic(Topic topic);
	List<TopicDetail> getTopicDetailsByTopic(Topic topic);
	TopicDetail updateTopicDetail(long id, TopicDetail topicDetail);
	TopicDetail getTopicDetailsByTopicId(Long topicId);
}
