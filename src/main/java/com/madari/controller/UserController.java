package com.madari.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

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
import com.madari.dto.UserDTO;
import com.madari.exceptions.ErrorMessages;
import com.madari.exceptions.UserServiceException;
import com.madari.model.request.UserDetailsRequestModel;
import com.madari.model.response.UserDetailResponseModel;
import com.madari.services.UserService;
import com.madari.shared.OperationStatusModel;

@RestController
public class UserController {
	
	@Autowired
    private UserService userService;
	
	@GetMapping(path="/users", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public String getUsers(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "2") int limit) {
		
		List<UserDetailResponseModel> returnedValue= new ArrayList<>();	
		List<UserDTO> users=userService.getUsers(page,limit);		
		Type listType = new TypeToken<List<UserDetailResponseModel>>() {
		}.getType();
			
		returnedValue = new ModelMapper().map(users, listType);
		  
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
		
	@GetMapping(path="/users/{username}",produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})   
    public String getUser(@PathVariable String username) {
    	UserDetailResponseModel returnedValue= new UserDetailResponseModel();
    	
    	UserDTO userDto = userService.getUser(username);  	
    	RoleDTO roleDto = userDto.getRole();   	
    	List<String> privileges = getPrivileges(roleDto);
    	
    	ModelMapper modelMapper = new ModelMapper();
    	returnedValue=modelMapper.map(userDto, UserDetailResponseModel.class);
    	returnedValue.setRole(roleDto.getName());
    	returnedValue.setPrivileges(privileges);
    	
    	ObjectMapper objectMapper = new ObjectMapper();
        String roleJson=null;
		try {
			roleJson = objectMapper.writeValueAsString(returnedValue);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    	
    	return roleJson;
    }
	private final List<String> getPrivileges(final RoleDTO role) {
        final List<String> privileges = new ArrayList<String>();
        final List<PrivilegeDTO> collection = role.getPrivilages();//new ArrayList<Privilege>();
       
        for (final PrivilegeDTO item : collection) {
            privileges.add(item.getName());
        }
        return privileges;
    }
	
	@PostMapping(path="/users",
    		consumes= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
    		produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public UserDetailResponseModel createUser(@Valid @RequestBody UserDetailsRequestModel userDetailReqMod)throws Exception {
    	
    	if(userDetailReqMod.getFirstName().isEmpty()) throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
    	
    	UserDetailResponseModel userDetResp =new UserDetailResponseModel();
       	RoleDTO roleDto = new RoleDTO();
       	roleDto.setName(userDetailReqMod.getRole());
       	System.out.println(userDetailReqMod.getRole());
    	ModelMapper modelMapper = new ModelMapper();
    	UserDTO userDto=modelMapper.map(userDetailReqMod,UserDTO.class);
    	userDto.setRole(roleDto);
    	
    	UserDTO createdUser= userService.createUser(userDto);

    	userDetResp.setUserId(createdUser.getUserId());
    	userDetResp.setFirstName(createdUser.getFirstName());
    	userDetResp.setLastName(createdUser.getLastName());
    	userDetResp.setUserName(createdUser.getUserName());
    	userDetResp.setEmail(createdUser.getEmail());
    	
    	String role = createdUser.getRole().getName();
    	
    	userDetResp.setRole(role);
    	//userDetResp=modelMapper.map(createdUser,UserDetailResponseModel.class);    	
    	return userDetResp; 
    }
		@PutMapping(path="/users/{id}",
			consumes= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
    		produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}
    		)
	public UserDetailResponseModel updateUsers(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetailReqMod) {
		UserDTO userDto = new UserDTO();
    	
    	if(userDetailReqMod.getFirstName().isEmpty()) throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
    	
		UserDetailResponseModel userDetResp =new UserDetailResponseModel();	
		ModelMapper modelMapper = new ModelMapper();
		userDto=modelMapper.map(userDetailReqMod, UserDTO.class);
    	
    UserDTO createdUser= userService.updateUser(id,userDto);
    	userDetResp=modelMapper.map(createdUser,UserDetailResponseModel.class);  	    	
    	return userDetResp;
	}
	
	
	@DeleteMapping(path="/users")
    public OperationStatusModel deleteUser(@PathVariable String userID) {
    	OperationStatusModel returnValue = new OperationStatusModel();
    	returnValue.setOperationName("DELETE");
    	returnValue.setOperationResult("SUCCESS");
    	userService.deleteUser(userID);
    	return returnValue;
    }

}
