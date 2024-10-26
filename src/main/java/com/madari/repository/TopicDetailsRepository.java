package com.madari.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.madari.model.Topic;
import com.madari.model.TopicDetail;

@Repository
public interface TopicDetailsRepository extends JpaRepository<TopicDetail,Long>{
	List<TopicDetail> findByTopic(Topic topic);
	void deleteByTopic(Topic topic);
	//TopicDetail findById(Long id);
	TopicDetail findByTopicId(Long topicId);
	
} 
