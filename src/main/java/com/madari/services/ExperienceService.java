package com.vadin.services;

import java.util.List;

import com.vadin.model.Experience;
import com.vadin.model.request.ExperienceRequestModel;
import com.vadin.model.response.ExperienceResponseModel;

public interface ExperienceService {
	List<Experience> getAllExperience();
	Experience getExperienceById(Long id);
	void deleteExperience(Long experienceId);
	ExperienceResponseModel createExperience(ExperienceRequestModel experience, String userId);
	Experience updateExperience(Long experienceId, Experience Experience);
	List<Experience> getAllExperience(ExperienceRequestModel experienceRequestDto, String userId);
	
}
