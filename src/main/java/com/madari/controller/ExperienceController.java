package com.madari.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.madari.model.Experience;
import com.madari.model.request.ExperienceRequestModel;
import com.madari.model.response.ExperienceResponseModel;
import com.madari.services.ExperienceService;

@RestController
@RequestMapping("/experinces")
public class ExperienceController {
	
	private final ExperienceService experienceService;

    @Autowired
    public ExperienceController(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }
	

    @PostMapping("/create")
    public ResponseEntity<ExperienceResponseModel> createExperience(@RequestBody ExperienceRequestModel experienceRequestDto, @RequestParam String userId) {
        // Return the saved Experience entity
        return new ResponseEntity<>(experienceService.createExperience(experienceRequestDto, userId), HttpStatus.CREATED);
    }

    // Get all Experiences\
  
    @GetMapping 
    public List<Experience> getAllExperiences() {
        return experienceService.getAllExperience();
    }

    // Get a specific Experience by ID
    @GetMapping("/{id}")
    public Experience getExperienceById(@PathVariable Long id) {
        return experienceService.getExperienceById(id);
    }

    // Update an existing Experience
    @PutMapping("/{id}")
    public Experience updateExperience(@PathVariable Long id, @RequestBody Experience experience) {
        return experienceService.updateExperience(id, experience);
    }

    // Delete a specific Experience by ID
    @DeleteMapping("/{id}")
    public void deleteExperience(@PathVariable Long id) {
        experienceService.deleteExperience(id);
    }

}
