package com.vadin.services;

import java.util.List;

import com.vadin.exceptions.ChapterNotFoundException;
import com.vadin.model.Chapter;


public interface ChapterService {
	Chapter saveChapter(Chapter chapters);
    void deleteChapter(Long id);
	Chapter getChaptersById(Long id) throws ChapterNotFoundException;
	Chapter updateChapter(Long id, Chapter chapter) throws ChapterNotFoundException;
	List<Chapter> getAllChaptersByCourseId(Long courseId);
}
