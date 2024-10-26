package com.madari.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.madari.dto.PrivilegeDTO;
import com.madari.dto.RoleDTO;
import com.madari.exceptions.ErrorMessages;
import com.madari.exceptions.UserServiceException;
import com.madari.model.request.RoleDetailsRequestModel;
import com.madari.model.response.RoleDetailResponseModel;
import com.madari.services.RoleService;
import com.madari.shared.OperationStatusModel;

@RestController
public class RoleController {
	@Autowired
    private RoleService roleService;
	
	@GetMapping(path="/roles", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<RoleDetailResponseModel> getRoles(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "2") int limit) {
		
		List<RoleDetailResponseModel> returnedValue= new ArrayList<>();
		
		List<RoleDTO> users=roleService.getRoles(page,limit);
		
		Type listType = new TypeToken<List<RoleDetailResponseModel>>() {
		}.getType();
			
		returnedValue = new ModelMapper().map(users, listType);
		  
		return returnedValue;
	}
	
//	@GetMapping(path="/roles", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
//	public List<RoleDto> getRoles(@RequestParam(value = "page", defaultValue = "0") int page,
//			@RequestParam(value = "limit", defaultValue = "2") int limit) {
//		
//		List<RoleDto> returnedValue= new ArrayList<>();
//		
//		List<Role> returnedRoles=roleService.getRoles(page,limit);
//	
//		ModelMapper modelMapper = new ModelMapper();
//		returnedValue = returnedRoles.stream().map(role->modelMapper(role,RoleDto.class).collect(Collectors.toList());
//		return returnedValue;
//	}
		
	@GetMapping(path="/roles/{name}",produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    
    public String getRole(@PathVariable String name) {
    	
    	RoleDTO roleDto = roleService.getRole(name);  	   	
    	List<String> privileges = roleDto.getPrivilages().stream()
    			.map(PrivilegeDTO::getName)
    			.collect(Collectors.toList());
    	
    	RoleDetailResponseModel roleDetailResponseModel = new RoleDetailResponseModel();
        roleDetailResponseModel.setName(roleDto.getName());
        roleDetailResponseModel.setPrivileges(privileges);

        ObjectMapper objectMapper = new ObjectMapper();
        String roleJson=null;
		try {
			roleJson = objectMapper.writeValueAsString(roleDetailResponseModel);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return roleJson;
    }
	
	@PostMapping(path="/roles",
    		consumes= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
    		produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public RoleDetailResponseModel createRole(@RequestBody RoleDetailsRequestModel roleDetailReqMod)throws Exception {   	
    	if(roleDetailReqMod.getName().isEmpty()) throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
    	
    	RoleDetailResponseModel roleDetResp =new RoleDetailResponseModel();
       	RoleDTO roleDto = new RoleDTO();
       	
    	ModelMapper modelMapper = new ModelMapper();
    	roleDto=modelMapper.map(roleDetailReqMod,RoleDTO.class);
    	
    	List<String> privileges = roleDetailReqMod.getPrivileges();
  	
    	List<PrivilegeDTO> privilegeList = privileges.stream()
    		    .map(priv -> {
    		        PrivilegeDTO privilegeDto = new PrivilegeDTO();
    		        privilegeDto.setName(priv);
    		        return privilegeDto;
    		    })
    		    .collect(Collectors.toList());
   
    	roleDto.setPrivilages(privilegeList);
    	
    	RoleDTO createdRole= roleService.createRole(roleDto);
    	
    	roleDetResp=modelMapper.map(createdRole,RoleDetailResponseModel.class);
    	roleDetResp.setPrivileges(privileges);   	
    	return roleDetResp; 
    }
		@PutMapping(path="/role/{id}",
			consumes= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
    		produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public RoleDetailResponseModel updateUsers(@PathVariable long id, @RequestBody RoleDetailsRequestModel roleDetailReqMod) {
		RoleDTO roleDto = new RoleDTO();
    	
    	if(roleDetailReqMod.getName().isEmpty()) throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
    	
		RoleDetailResponseModel roleDetResp =new RoleDetailResponseModel();	
		ModelMapper modelMapper = new ModelMapper();
		roleDto=modelMapper.map(roleDetailReqMod, RoleDTO.class);
		
		List<String> privileges = roleDetailReqMod.getPrivileges();	  	
    	List<PrivilegeDTO> privilegeList = privileges.stream()
    		    .map(priv -> {
    		        PrivilegeDTO privilegeDto = new PrivilegeDTO();
    		        privilegeDto.setName(priv);
    		        return privilegeDto;
    		    })
    		    .collect(Collectors.toList());
   
    	roleDto.setPrivilages(privilegeList);	
    	RoleDTO createdRole= roleService.updateRole(id,roleDto);
    	roleDetResp=modelMapper.map(createdRole,RoleDetailResponseModel.class);
    	
    	List<String> updatedPrivileges = roleDto.getPrivilages().stream()
    			.map(PrivilegeDTO::getName)
    			.collect(Collectors.toList());
    	roleDetResp.setPrivileges(updatedPrivileges);   	
    	return roleDetResp;
	}
	
	@DeleteMapping(path="/roles")
    public OperationStatusModel deleteRole(@PathVariable int roleId) {
    	OperationStatusModel returnValue = new OperationStatusModel();
    	returnValue.setOperationName("DELETE");
    	returnValue.setOperationResult("SUCCESS");
    	roleService.deleteRole(roleId);
    	return returnValue;
    }	
	

}
