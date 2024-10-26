package com.madari.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.madari.model.Topic;
import com.madari.model.TopicDetail;
import com.madari.repository.TopicDetailsRepository;

@Service
public class TopicDetailServiceImpl implements TopicDetailService {
	//@Autowired
	TopicDetailsRepository topicDetailRepository;
	
	public TopicDetailServiceImpl(TopicDetailsRepository topicDetailRepository) {
		this.topicDetailRepository=topicDetailRepository;
	}
	@Override
    public TopicDetail saveTopicDetail(TopicDetail topicDetail) {
        return topicDetailRepository.save(topicDetail);
    }

    @Override
    public TopicDetail getTopicDetailById(Long id) {
        return topicDetailRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TopicDetail with id " + id + " not found"));
    }
    
    @Override
    public List<TopicDetail> getTopicDetailsByTopic(Topic topic) {
        return topicDetailRepository.findByTopic(topic);
    }

	@Override
	public void deleteTopicDetail(Long id) {
		topicDetailRepository.deleteById(id);
		
	}

	@Override
	public void deleteByTopic(Topic topic) {
		topicDetailRepository.deleteByTopic(topic);
	}

	@Override
	public TopicDetail updateTopicDetail(long id, TopicDetail topicDetail) {
		//update topic using topicId
		Optional<TopicDetail> updatedTopicDetail = topicDetailRepository.findById(id);
		//  
		topicDetailRepository.save(topicDetail);
		return null;
	}
	@Override
	public TopicDetail getTopicDetailsByTopicId(Long topicId) {
		    return topicDetailRepository.findByTopicId(topicId);
	}


}
