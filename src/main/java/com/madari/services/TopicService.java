package com.madari.services;

import java.util.List;

import com.madari.model.Topic;

import javassist.NotFoundException;


public interface TopicService {
	Topic getTopicById(Long id) throws NotFoundException;
    Topic saveTopic(Topic topic);
    void deleteTopic(Long id);
    Topic updateTopic(long topicId, Topic topic);
    List<Topic> findByChapterId(long chapterId);
	List<Topic> getAllTopicsByChapterId(Long chapterId);
}
