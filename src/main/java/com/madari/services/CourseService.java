package com.madari.services;

import java.util.List;

import com.madari.dto.CourseDTO;
import com.madari.model.Course;
import com.madari.model.request.CourseRequestModel;
import com.madari.model.response.CourseResponseModel;

public interface CourseService {
	CourseResponseModel createCourse(CourseRequestModel courseRequestDto, String userId) throws Exception;
	Course getCourseById(Long id);
	Course updateCourse(Long id, Course course);
    void deleteCourse(Long id);
	CourseResponseModel getCourse(Long courseId);
	List<CourseDTO> getCourses(String userId);
	List<CourseDTO> getCourses();
}
