package com.vadin.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.vadin.model.Course;

public interface CourseRepository extends PagingAndSortingRepository<Course, Long>{
	
	Course findByName(String name);
	
	Course deleteById(long courseId);
	//Course findByCourseCode(String courseCode);
	List<Course> findAll();
}
