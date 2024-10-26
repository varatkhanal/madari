package com.madari.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.madari.model.Course;
import com.madari.model.Users;

public interface CourseRepository extends PagingAndSortingRepository<Course, Long>{
	
	Course findByName(String name);
	
	Course deleteById(long courseId);
	//Course findByCourseCode(String courseCode);
	List<Course> findAll();
	List<Course> findByAuthor(Users user);
}
