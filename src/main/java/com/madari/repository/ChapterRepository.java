package com.madari.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.madari.model.Chapter;
import com.madari.model.Course;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {
	//void saveChapter(Chapter chapter);
	void deleteById(Long chapterId);
	List<Chapter> findByCourseId(Long courseId);
	List<Chapter> findByCourse(Course course);
	List<Chapter> getAllChaptersByCourseId(Long courseId);
}
