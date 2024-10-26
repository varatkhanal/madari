package com.madari.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.madari.model.Experience;
import com.madari.model.Users;
import com.madari.model.request.ExperienceRequestModel;
import com.madari.model.response.ExperienceResponseModel;
import com.madari.model.response.UserDetailResponseModel;
import com.madari.repository.ExperienceRepository;
import com.madari.repository.UserRepository;

@Service
public class ExperienceServiceImpl implements ExperienceService{
	
	
	final ExperienceRepository experienceRepository;
	
	//final UserService userService;
	
	final UserRepository userRepository;
	
	public ExperienceServiceImpl(ExperienceRepository experienceRepository, UserRepository userRepository) {
		this.experienceRepository = experienceRepository;
		this.userRepository = userRepository;
		
	}

	@Override
	public ExperienceResponseModel createExperience(ExperienceRequestModel experienceRequestDto, String userId) {

		// Extract attributes related to the Post entity
        String postDescription = experienceRequestDto.getPostDescription();
        
        // Extract attributes related to the Experience entity
        String experienceTitle = experienceRequestDto.getExperienceTitle();
        String experienceContent = experienceRequestDto.getContent();
        LocalDate experienceStartDate = experienceRequestDto.getStat_date();
        LocalDate experienceEndDate = experienceRequestDto.getEnd_date();
               
        Users user = userRepository.findByUserId(userId);
        
        // Create and save the Experience entity
        Experience experience = new Experience();
        experience.setContent(experienceContent);
        experience.setStartDate(experienceStartDate);
        experience.setEndDate(experienceEndDate);
        experience.setTitle(experienceTitle);
        experience.setDescription(postDescription);
        experience.setAuthor(user);   
        
        // Set the relationship with the Post entity
       // experience.setPost(post);
        
        // Save the Experience entity
        experience = experienceRepository.save(experience);
       
        ModelMapper modelMapper = new ModelMapper();
        UserDetailResponseModel userDto = modelMapper.map(user, UserDetailResponseModel.class);
        
        ExperienceResponseModel experienceRespMod = new ExperienceResponseModel();
       // experienceRespMod.setName(experience.);
        experienceRespMod.setContent(experience.getContent());
        experienceRespMod.setTitle(experience.getTitle());
        experienceRespMod.setDescription(experience.getDescription());
        experienceRespMod.setUserRespModel(userDto);
        experienceRespMod.setContent(experience.getContent());
        experienceRespMod.setEnd_date(experienceEndDate);
        experienceRespMod.setStat_date(experienceStartDate);
        // Perform any additional operations if needed   
        return experienceRespMod;
	}

	@Override
	public Experience getExperienceById(Long id) {
		Optional<Experience> optionalExp = experienceRepository.findById(id);
		Experience experince = optionalExp.orElseThrow();
		return experince;
	}

	@Override
	public void deleteExperience(Long experienceId) {
		experienceRepository.deleteById(experienceId);
    }


	@Override
	public Experience updateExperience(Long experienceId, Experience Experience) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Experience> getAllExperience() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Experience> getAllExperience(ExperienceRequestModel experienceRequestDto, String userId) {
		// TODO Auto-generated method stub
		return experienceRepository.findAll();
	}
	

}
