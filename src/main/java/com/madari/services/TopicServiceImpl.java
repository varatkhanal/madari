package com.madari.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madari.model.Topic;
import com.madari.repository.TopicRepository;

import javassist.NotFoundException;

@Service
public class TopicServiceImpl implements TopicService{
	@Autowired
    private TopicRepository topicRepository;

    @Override
    public Topic getTopicById(Long id) throws NotFoundException {
        Optional<Topic> topicOptional = topicRepository.findById(id);
        if (topicOptional.isPresent()) {
            return topicOptional.get();
        } else {
            throw new NotFoundException("Topic not found with id: " + id);
        }
    }

    @Override
    public Topic saveTopic(Topic topic) {
        return topicRepository.save(topic);
    }

    @Override
    public void deleteTopic(Long id) {
        topicRepository.deleteById(id);
    }

	@Override
	public Topic updateTopic(long id, Topic topic) {
		
		Topic savedTopic = topicRepository.findById(id);
		savedTopic.setDescription(topic.getDescription());
		savedTopic.setTitle(topic.getTitle());
		savedTopic.setTopicDetails(topic.getTopicDetails());
		topicRepository.save(topic);
		return savedTopic;
	}

	@Override
	public List<Topic> findByChapterId(long chapterId) {	
		return topicRepository.findByChapterId(chapterId);
	}

	@Override
	public List<Topic> getAllTopicsByChapterId(Long chapterId) {
		List<Topic> topics = topicRepository.getAllTopicsByChapterId(chapterId);
		return topics;
	}
    
}
