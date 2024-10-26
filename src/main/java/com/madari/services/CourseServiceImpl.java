package com.madari.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.madari.dto.CourseDTO;
import com.madari.exceptions.ErrorMessages;
import com.madari.exceptions.UserServiceException;
import com.madari.model.Chapter;
import com.madari.model.Course;
import com.madari.model.Topic;
import com.madari.model.TopicDetail;
import com.madari.model.Users;
import com.madari.model.request.CourseRequestModel;
import com.madari.model.response.ChapterResponseModel;
import com.madari.model.response.CourseResponseModel;
import com.madari.model.response.TopicDetailResponseModel;
import com.madari.model.response.TopicResponseModel;
import com.madari.model.response.UserDetailResponseModel;
import com.madari.repository.ChapterRepository;
import com.madari.repository.CourseRepository;
import com.madari.repository.TopicDetailsRepository;
import com.madari.repository.TopicRepository;
import com.madari.repository.UserRepository;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;

@Service
public class CourseServiceImpl implements CourseService{
	
	private final PostService postService;
   
	private final UserRepository userRepository;
	
	private final CourseRepository courseRepository;
    private final ChapterRepository chapterRepository;
    private final TopicRepository topicRepository;
    private final TopicDetailsRepository topicDetailRepository;
	private Authentication auth;
    
    public CourseServiceImpl(CourseRepository courseRepository, ChapterRepository chapterRepository,
                         TopicRepository topicRepository, TopicDetailsRepository topicDetailRepository, PostService postService, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.chapterRepository = chapterRepository;
        this.topicRepository = topicRepository;
        this.topicDetailRepository = topicDetailRepository;
        
        this.postService = postService;
        this.userRepository = userRepository;
       
    }

	public CourseResponseModel createCourse(CourseRequestModel courseRequestDto, String userId){
		
		// Extract attributes related to the Post entity
        String title = courseRequestDto.getPostTitle();
        String description = courseRequestDto.getPostDescription();
        
        // Extract attributes related to the Course entity
        String courseName = courseRequestDto.getName();
        
        Users user = userRepository.findByUserId(userId);
                     
        // Create and save the Course entity
        Course course = new Course();
        course.setTitle(title);
        course.setDescription(description);
        course.setName(courseName);
        course.setAuthors(user);
        course.setDescription(description);
        // Set other Course attributes
   
        // Perform any necessary validation or business logic
		if(courseRepository.findByName(courseRequestDto.getName())!=null) 
			throw new RuntimeException("course"+courseRequestDto.getName()+" already exist");
               
        // Save the new course
        Course createdCourse = courseRepository.save(course);
        
        ModelMapper modelMapper = new ModelMapper();
        UserDetailResponseModel userRep = modelMapper.map(user, UserDetailResponseModel.class);
        
        CourseResponseModel courseRespMod = new CourseResponseModel();
        courseRespMod.setName(createdCourse.getName());
        courseRespMod.setTitle(createdCourse.getTitle());
        courseRespMod.setDescription(createdCourse.getDescription());
        courseRespMod.setUserResponse(userRep);
        // Perform any additional operations if needed     
        return courseRespMod;
    }

	public CourseResponseModel getCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course with id " + courseId + " not found"));

        List<ChapterResponseModel> chapRespMods = new ArrayList<>();
        List<TopicResponseModel> topicRespMods = new ArrayList<>();
        List<TopicDetailResponseModel> topicDetailRespMods = new ArrayList<>();
        
        List<Chapter> chapters = chapterRepository.findByCourse(course);
        chapters.forEach(chapter -> {
        	ChapterResponseModel chapRespMod = new ModelMapper().map(chapter,ChapterResponseModel.class);
            List<Topic> topics = topicRepository.findByChapter(chapter);
            topics.forEach(topic -> {
            	TopicResponseModel topicRespMod = new ModelMapper().map(topic,TopicResponseModel.class);
                List<TopicDetail> topicDetails = topicDetailRepository.findByTopic(topic);
                topicDetails.forEach(topicDetail -> {
                	TopicDetailResponseModel topicDetailRespMod = new ModelMapper().map(topicDetail,TopicDetailResponseModel.class);              	
                	topicDetailRespMods.add(topicDetailRespMod);          	
                });
                topicRespMod.setTopicDetailsRespModel(topicDetailRespMods);
                topicRespMods.add(topicRespMod);
                topic.setTopicDetails(topicDetails);
                
            });
            chapRespMod.setTopicResponeModel(topicRespMods);
            chapRespMods.add(chapRespMod);  
            chapter.setTopics(topics);
        });
        Users user = course.getAuthor();  
        UserDetailResponseModel userRep = new ModelMapper().map(user, UserDetailResponseModel.class);
        
        CourseResponseModel courseRespMod = new ModelMapper().map(course, CourseResponseModel.class);
        
        courseRespMod.setUserResponse(userRep);
        courseRespMod.setChapRespModel(chapRespMods);      

        course.setChapters(chapters);
        
        return courseRespMod;
    }

	@Override
	public Course getCourseById(Long id) {
    	
    	Optional<Course> course= courseRepository.findById(id);
    	//BeanUtils.copyProperties(course, returnedValue); 	
    	return (Course)course.get();
	} 


	public Course updateCourse(Long courseId, Course updatedCourse) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course with id " + courseId + " not found"));

        // Update the course properties with the new values
        course.setName(updatedCourse.getName());
        // Update other properties as needed
        // Save the updated course
        Course savedCourse = courseRepository.save(course);
        return savedCourse;
    }

	@Override
	public void deleteCourse(Long id) {
			Optional<Course> course=courseRepository.findById(id);		
			if(course==null)
				throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());		
			courseRepository.delete(course.get());	
	}
	@Override
	public List<CourseDTO> getCourses() {
		//Users user = userRepository.findByUserId(userId);		
		List<Course> courses = (List<Course>) courseRepository.findAll();
		ModelMapper modelMapper = new ModelMapper();
		return courses.stream().map(course -> modelMapper.map(course, CourseDTO.class)).collect(Collectors.toList());
	}

	@Override
	public List<CourseDTO> getCourses(String userId) {
		Users user = userRepository.findByUserId(userId);		
		List<Course> courses = (List<Course>) courseRepository.findByAuthor(user);
		ModelMapper modelMapper = new ModelMapper();
		return courses.stream().map(course -> modelMapper.map(course, CourseDTO.class)).collect(Collectors.toList());
	}
}
