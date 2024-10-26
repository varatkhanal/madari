package com.madari.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.madari.dto.PrivilegeDTO;
import com.madari.exceptions.ErrorMessages;
import com.madari.exceptions.UserServiceException;
import com.madari.model.request.PrivilegeDetailsRequestModel;
import com.madari.model.response.PrivilegeDetailResponseModel;
import com.madari.services.PrivilegeService;
import com.madari.shared.OperationStatusModel;

@RestController
public class PrivilegeController {
	
	@Autowired(required=true)
    private PrivilegeService privilegeService;
	
	@GetMapping(path="/privileges", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public String getPrivileges() {
		
		List<PrivilegeDetailResponseModel> returnedValue= new ArrayList<>();		
		List<PrivilegeDTO> privilegeList=privilegeService.getPrivileges();
		
		Type listType = new TypeToken<List<PrivilegeDetailResponseModel>>() {
		}.getType();		
		returnedValue = new ModelMapper().map(privilegeList, listType);			
		ObjectMapper objectMapper = new ObjectMapper();
        String roleJson=null;
		try {
			roleJson = objectMapper.writeValueAsString(returnedValue);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    	return roleJson;
	}
		
	@PostMapping(path="/privileges",
    		consumes= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
    		produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public PrivilegeDetailResponseModel createPrivilege(@RequestBody PrivilegeDetailsRequestModel privilegeDetailReqMod)throws Exception {   	
    	if(privilegeDetailReqMod.getName().isEmpty()) throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
    	
    	PrivilegeDetailResponseModel privilegeDetResp =new PrivilegeDetailResponseModel();
       	PrivilegeDTO privilegeDto = new PrivilegeDTO();
       	
    	ModelMapper modelMapper = new ModelMapper();  
    	privilegeDto=modelMapper.map(privilegeDetailReqMod,PrivilegeDTO.class);
    	
    	PrivilegeDTO createdPrivilege= privilegeService.createPrivilege(privilegeDto);    	
    	privilegeDetResp=modelMapper.map(createdPrivilege,PrivilegeDetailResponseModel.class);  	
    	return privilegeDetResp; 
    }
	
	@DeleteMapping(path="/privileges")
    public OperationStatusModel deletePrivilege(@PathVariable long privilegeId) {
    	OperationStatusModel returnValue = new OperationStatusModel();
    	returnValue.setOperationName("DELETE");
    	returnValue.setOperationResult("SUCCESS");
    	privilegeService.deletePrivilege(privilegeId);
    	return returnValue;
    }

}
