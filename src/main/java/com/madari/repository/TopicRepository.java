package com.madari.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.madari.model.Chapter;
import com.madari.model.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
	Topic save(Topic topic);
	void deleteById(long topicId);
	Topic findById(long topicId);
	List<Topic> findByChapterId(long chapterId);
	List<Topic> findByChapter(Chapter chapter);
	List<Topic> getAllTopicsByChapterId(Long chapterId);
}
