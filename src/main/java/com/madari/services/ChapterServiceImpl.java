package com.madari.services;

import java.util.List;
import java.util.Optional;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madari.exceptions.ChapterNotFoundException;
import com.madari.model.Chapter;
import com.madari.repository.ChapterRepository;


@Service
public class ChapterServiceImpl implements ChapterService {

	@Autowired
    private ChapterRepository chapterRepository;

    @Override
    public Chapter getChaptersById(Long id) throws ChapterNotFoundException {	
    	return chapterRepository.findById(id).orElseThrow(() -> new ChapterNotFoundException("Chapter with id " + id + " not found"));          
    }


    @Override
    public Chapter saveChapter(Chapter chapters) {
         return chapterRepository.save(chapters);
    }

    @Override
    public void deleteChapter(Long id) {
    	chapterRepository.deleteById(id);
    }

	@Override
	public Chapter updateChapter(Long id, Chapter chapter) throws ChapterNotFoundException {
		Optional<Chapter> updatedChapter = chapterRepository.findById(id);
		if(updatedChapter.isPresent()) {
			updatedChapter.get().setTitle(chapter.getTitle());
			updatedChapter.get().setDescription(chapter.getDescription());
			updatedChapter.get().setTopics(chapter.getTopics());
			
			chapterRepository.save(new ModelMapper().map(updatedChapter,Chapter.class));
		}else{throw new ChapterNotFoundException("Chapter not found");}
	
		return new ModelMapper().map(updatedChapter,Chapter.class);
	}


	@Override
	public List<Chapter> getAllChaptersByCourseId(Long courseId) {
		List<Chapter> chapters = chapterRepository.getAllChaptersByCourseId(courseId);
		return chapters;
	}

}
