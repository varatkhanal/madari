package com.vadin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vadin.model.Topic;
import com.vadin.model.TopicDetail;

@Repository
public interface TopicDetailsRepository extends JpaRepository<TopicDetail,Long>{
	List<TopicDetail> findByTopic(Topic topic);
	void deleteByTopic(Topic topic);
	//TopicDetail findById(Long id);
	TopicDetail findByTopicId(Long topicId);
	
} 
