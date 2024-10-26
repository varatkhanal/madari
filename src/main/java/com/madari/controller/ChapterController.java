package com.madari.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.madari.dto.ChapterDTO;
import com.madari.model.Chapter;
import com.madari.model.Course;
import com.madari.model.response.ChapterResponseModel;
import com.madari.services.ChapterService;
import com.madari.services.CourseService;
import com.madari.shared.OperationStatusModel;


@RestController
@RequestMapping("/Courses/{courseId}/chapters")
public class ChapterController {

	@Autowired
	private ChapterService chapterService;
	
	@Autowired
	private CourseService courseService;
	
	@PostMapping("/create")
	public ChapterResponseModel saveChapter(@RequestBody ChapterDTO chapterDto, @PathVariable Long courseId){
		Chapter chapter = new Chapter();
		ModelMapper modelMapper = new ModelMapper();
		chapter = modelMapper.map(chapterDto, Chapter.class);		
		Course course = courseService.getCourseById(courseId);
		chapter.setCourse(course);
		Chapter chap =chapterService.saveChapter(chapter);
		ChapterResponseModel responseModel = modelMapper.map(chap, ChapterResponseModel.class);
		return responseModel;
	}
	
//	@GetMapping(path="/{chapterId}", produces= {MediaType.APPLICATION_XML_VALUE})
//	public ChapterDto getChapterById(@PathVariable Long chapterId)throws Exception{
//		ChapterDto chapterDto = new ChapterDto();
//		 Chapter chap = chapterService.getChaptersById(chapterId);
//		ModelMapper modelMapper = new ModelMapper();
//		modelMapper.map(chapterDto, ChapterDto.class);
//		
//		return  chapterDto;
//	}
	
	@GetMapping
    public ResponseEntity<List<Chapter>> getAllChapters(@PathVariable Long courseId) {
        List<Chapter> chapters = chapterService.getAllChaptersByCourseId(courseId);
        return ResponseEntity.ok(chapters);
    }
	
	
	@PutMapping(path="/{chapterId}",
			consumes= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
    		produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}
    		)
	public ChapterResponseModel updateChapter(@PathVariable Long chapterId, @RequestBody ChapterDTO chapterDto) throws Exception{
		Chapter chapter =  new Chapter();	
		ModelMapper modelMapper = new ModelMapper();
		chapter = modelMapper.map(chapterDto, Chapter.class);
		ChapterResponseModel responseModel = new ChapterResponseModel();
		responseModel = modelMapper.map(chapterService.updateChapter(chapterId, chapter),ChapterResponseModel.class);    
		
		return responseModel;
	}
	
	
	@DeleteMapping(path = "/{chapterId}")
	public OperationStatusModel deleteChapter(@PathVariable Long chapterId) {
		OperationStatusModel operationStatus = new OperationStatusModel();
		
		chapterService.deleteChapter(chapterId);
		operationStatus.setOperationName("delete chapter ");
		operationStatus.setOperationResult("success");
		
		return operationStatus;
	}
}
