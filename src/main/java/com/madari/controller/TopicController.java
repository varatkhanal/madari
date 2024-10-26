package com.madari.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.madari.dto.TopicDTO;
import com.madari.dto.TopicDetailDTO;
import com.madari.exceptions.ChapterNotFoundException;
import com.madari.exceptions.ErrorMessages;
import com.madari.exceptions.TopicServiceException;
import com.madari.model.Topic;
import com.madari.services.ChapterService;
import com.madari.services.TopicService;
import com.madari.shared.OperationStatusModel;


@RestController
@RequestMapping("/courses/{courseId}/chapters/{chapterId}/topics")
public class TopicController {
	
	private TopicService topicService;
	
	private ChapterService chapterService;
	
	public TopicController(TopicService topicService, ChapterService chapterService){
		this.topicService=topicService;
		this.chapterService = chapterService;
		
	}
	
//	@GetMapping(path="/{id}", produces= {MediaType.APPLICATION_XML_VALUE})
//	public TopicDto getTopicByID(@PathVariable Long id) {
//		TopicDto topicDto = new TopicDto();
//		Topic topic;
//		try {
//			topic = topicService.getTopicById(id);
//		} catch (NotFoundException e) {
//			// TODO Auto-generated catch block
//			throw new RuntimeException("Topic not found");
//		}
//		ModelMapper modelMapper = new ModelMapper();
//		
//		topicDto=modelMapper.map(topic, TopicDto.class);
//		return topicDto;
//	}
//	
	
	
	@PostMapping(
			consumes= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
			produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<TopicDTO> createTopic(@RequestBody TopicDTO topicDto, @PathVariable Long chapterId, @PathVariable Long courseId) throws ChapterNotFoundException {
		ModelMapper modelMapper = new ModelMapper();
		Topic topic = new Topic();
		
		topic= modelMapper.map(topicDto, Topic.class);
		topic.setChapter(chapterService.getChaptersById(chapterId));
		TopicDTO savedTopicDto = new TopicDTO();
		topicService.saveTopic(topic);
		savedTopicDto = modelMapper.map(topic, TopicDTO.class);	
		return ResponseEntity.ok(savedTopicDto);
	}
	
	@PutMapping(path="/{id}",consumes= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})   
    public TopicDTO updateTopic(@PathVariable Long id, @RequestBody TopicDTO topicDto) {
		TopicDTO returnedValue= new TopicDTO();
    	
    	Topic topic = new Topic();
    	
    	if(topicDto.getTitle().isEmpty()) throw new TopicServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());		
		ModelMapper modelMapper = new ModelMapper();
		topic=modelMapper.map(topicDto, Topic.class);  	
		Topic updatedTopic = topicService.updateTopic(id,topic);
		returnedValue=modelMapper.map(updatedTopic,TopicDTO.class);
    	return returnedValue;	
    }
	
	@DeleteMapping(path="/{id}")
	public OperationStatusModel deleteTopic(@PathVariable long id) {
		OperationStatusModel operationStatus = new OperationStatusModel();
		topicService.deleteTopic(id);
		operationStatus.setOperationName("delete course");
		operationStatus.setOperationResult("success");
		
		return operationStatus;
	} 
	
	@GetMapping(path="/{id}", produces= {MediaType.APPLICATION_XML_VALUE})
	public List<TopicDTO> getTopicByChaperId(@PathVariable Long chapterId) {
	    List<Topic> topic = topicService.findByChapterId(chapterId);
	    ModelMapper modelMapper = new ModelMapper();
	    List<TopicDTO> topicList = topic.stream().map(top -> {
    		    	TopicDTO topicDto = new TopicDTO();	    	
    		    	modelMapper.map(topicDto, TopicDetailDTO.class);
    		        return topicDto;
    		    })
    		    .collect(Collectors.toList());    
	    return topicList;
	}
	@GetMapping
    public ResponseEntity<List<Topic>> getAllTopicsByChapterId(@PathVariable Long courseId, @PathVariable Long chapterId) {
        List<Topic> topics = topicService.getAllTopicsByChapterId(chapterId);
        return ResponseEntity.ok(topics);
    }
}
