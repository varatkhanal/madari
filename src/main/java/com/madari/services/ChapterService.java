package com.madari.services;

import java.util.List;

import com.madari.exceptions.ChapterNotFoundException;
import com.madari.model.Chapter;


public interface ChapterService {
	Chapter saveChapter(Chapter chapters);
    void deleteChapter(Long id);
	Chapter getChaptersById(Long id) throws ChapterNotFoundException;
	Chapter updateChapter(Long id, Chapter chapter) throws ChapterNotFoundException;
	List<Chapter> getAllChaptersByCourseId(Long courseId);
}
