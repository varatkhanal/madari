package com.madari.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.madari.dto.TopicDetailDTO;
import com.madari.exceptions.ErrorMessages;
import com.madari.exceptions.TopicServiceException;
import com.madari.model.TopicDetail;
import com.madari.services.TopicDetailService;
import com.madari.services.TopicService;
import com.madari.shared.OperationStatusModel;

import javassist.NotFoundException;

@RestController
@RequestMapping("/courses/{courseId}/chapters/{chapterId}/topics/{topicId}")
public class TopicDetailController {
	
	
	private TopicDetailService topicDetailService;
	private TopicService topicService;
	
	public TopicDetailController(TopicDetailService topicDetailService, TopicService topicService) {
		this.topicDetailService = topicDetailService;
		this.topicService = topicService;
	}
	
	@GetMapping(path="/topicDetails/{id}", produces= {MediaType.APPLICATION_XML_VALUE})
	public TopicDetailDTO getTopicDetail(@PathVariable Long topicId) {
		TopicDetailDTO topicDetailDto = new TopicDetailDTO();
		TopicDetail topicDetail = topicDetailService.getTopicDetailById(topicId);
		ModelMapper modelMapper = new ModelMapper();	
		topicDetailDto=modelMapper.map(topicDetail, TopicDetailDTO.class);
		return topicDetailDto;
	}
	
	@PostMapping(path="/topicDetails",
			consumes= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
			produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public TopicDetailDTO createTopicDetail(@RequestBody TopicDetailDTO topicDetailDto, @PathVariable Long topicId) throws NotFoundException {
		ModelMapper modelMapper = new ModelMapper();
		TopicDetail topicDetail = new TopicDetail();	
		topicDetail= modelMapper.map(topicDetailDto, TopicDetail.class);
		topicDetail.setTopic(topicService.getTopicById(topicId));
		
		
		TopicDetailDTO savedTopicDetailDto = new TopicDetailDTO();
		topicDetailService.saveTopicDetail(topicDetail);
		savedTopicDetailDto = modelMapper.map(topicDetail, TopicDetailDTO.class);	
		return savedTopicDetailDto;
	}
	
	@PutMapping(path="/topicDetails/{id}",consumes= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})   
    public TopicDetailDTO updateTopicDetail(@PathVariable Long topicId, @RequestBody TopicDetailDTO topicDetailDto) {
		TopicDetailDTO returnedValue= new TopicDetailDTO();
    	
    	TopicDetail topicDetail = new TopicDetail();
    	
    	if(topicDetailDto.getTitle().isEmpty()) throw new TopicServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		
		ModelMapper modelMapper = new ModelMapper();
		topicDetail=modelMapper.map(topicDetailDto, TopicDetail.class);
		
		
    	
		TopicDetail updatedTopicDetail= topicDetailService.updateTopicDetail(topicId,topicDetail);
		returnedValue=modelMapper.map(updatedTopicDetail,TopicDetailDTO.class);
    	return returnedValue;	
    }
	
	@DeleteMapping(path="/topicDetails/{id}")
	public OperationStatusModel deleteTopicDetail(@PathVariable Long topicId) {
		OperationStatusModel operationStatus = new OperationStatusModel();
		
		topicDetailService.deleteTopicDetail(topicId);
		operationStatus.setOperationName("delete course");
		operationStatus.setOperationResult("success");
		
		return operationStatus;
	}
	
	@GetMapping("/topics/{topicId}/details")
	public TopicDetailDTO getTopicDetailsByTopicId(@PathVariable Long topicId) {
	    TopicDetail topicDetails = topicDetailService.getTopicDetailsByTopicId(topicId);
	    ModelMapper modelMapper = new ModelMapper();
	    return modelMapper.map(topicDetails, TopicDetailDTO.class);
	}
	

}
