package com.madari.services;

import java.util.List;

import com.madari.model.Experience;
import com.madari.model.request.ExperienceRequestModel;
import com.madari.model.response.ExperienceResponseModel;

public interface ExperienceService {
	List<Experience> getAllExperience();
	Experience getExperienceById(Long id);
	void deleteExperience(Long experienceId);
	ExperienceResponseModel createExperience(ExperienceRequestModel experience, String userId);
	Experience updateExperience(Long experienceId, Experience Experience);
	List<Experience> getAllExperience(ExperienceRequestModel experienceRequestDto, String userId);
	
}
