package com.vadin.services;

import java.util.List;

import com.vadin.model.Course;
import com.vadin.model.request.CourseRequestModel;
import com.vadin.model.response.CourseResponseModel;

public interface CourseService {
	CourseResponseModel createCourse(CourseRequestModel courseRequestDto, String userId) throws Exception;
	Course getCourseById(Long id);
	Course updateCourse(Long id, Course course);
    void deleteCourse(Long id);
	CourseResponseModel getCourse(Long courseId);
	List<Course> getCourses();
}
