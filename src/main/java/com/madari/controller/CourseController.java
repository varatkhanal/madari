package com.madari.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.madari.dto.CourseDTO;
import com.madari.model.Course;
import com.madari.model.request.CourseRequestModel;
import com.madari.model.response.CourseResponseModel;
import com.madari.services.CourseService;
import com.madari.shared.OperationStatusModel;


@RestController
@RequestMapping("/courses")
public class CourseController {
	
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/create")
    public ResponseEntity<CourseResponseModel> createCourse(@Valid @RequestBody CourseRequestModel courseRequestDto, @RequestParam String userId) throws Exception{
        // Return the saved Course entity
        return new ResponseEntity<>(courseService.createCourse(courseRequestDto,userId), HttpStatus.CREATED);
    }
	
	@GetMapping(produces= {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}) 
	  public ResponseEntity<List<CourseResponseModel>> getCourses() {
		
		// Retrieve authentication object from SecurityContextHolder
	   // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    
	    // Extract userId from authentication object
	   // String userId = (String) authentication.getPrincipal(); 
	    
	    List<CourseDTO> courses = courseService.getCourses();
	    ModelMapper modelMapper = new ModelMapper();
	    List<CourseResponseModel> crm= courses.stream().map(course -> modelMapper.map(course, CourseResponseModel.class))
        .collect(Collectors.toList());
	    return ResponseEntity.ok(crm);
	  }
	
	@GetMapping(path="/{userId}", produces= {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}) 
	  public ResponseEntity<List<CourseResponseModel>> getCourse(@PathVariable String userId) {
		
		//Character ch = Character.parseChar();
		
		// Retrieve authentication object from SecurityContextHolder
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    
	    // Extract userId from authentication object
	   /// String userId = (String) authentication.getPrincipal(); 
	    
	    List<CourseDTO> courses = courseService.getCourses(userId);
	    ModelMapper modelMapper = new ModelMapper();
	    List<CourseResponseModel> crm= courses.stream().map(course -> modelMapper.map(course, CourseResponseModel.class))
      .collect(Collectors.toList());
	    return ResponseEntity.ok(crm);
	  }
	
	@GetMapping(path="/{courseId}") 
	  public ResponseEntity<CourseResponseModel> getCourse(@PathVariable Long courseId) {
		CourseResponseModel courseRespMod = courseService.getCourse(courseId);
	    return ResponseEntity.ok(courseRespMod);
	  }
	
	@PutMapping("/{courseId}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long courseId, @RequestBody Course updatedCourse) {
        Course updated = courseService.updateCourse(courseId, updatedCourse);
        return ResponseEntity.ok(updated);
    }
	
	@DeleteMapping(path="/{courseId}")
	public OperationStatusModel deleteCourse(@PathVariable long id) {
		OperationStatusModel operationStatus = new OperationStatusModel();	
		courseService.deleteCourse(id);
		operationStatus.setOperationName("delete course");
		operationStatus.setOperationResult("success");		
		return operationStatus;
	}
	

}
