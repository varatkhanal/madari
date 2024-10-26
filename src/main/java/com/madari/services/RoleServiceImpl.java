package com.madari.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madari.dto.PrivilegeDTO;
import com.madari.dto.RoleDTO;
import com.madari.exceptions.ErrorMessages;
import com.madari.exceptions.UserServiceException;
import com.madari.model.Privilege;
import com.madari.model.Role;
import com.madari.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	RoleRepository roleRepository;

	@Override
	public RoleDTO createRole(RoleDTO roleDto) {
		if(roleRepository.findByName(roleDto.getName())!=null) throw new RuntimeException("Record already exist");
    	
		Role role = new Role();		
		List<PrivilegeDTO> privilegeDtos = roleDto.getPrivilages();
		List<Privilege> privileges = new ArrayList<>();
		
		ModelMapper modelMapper = new ModelMapper();
		
		RoleDTO returnedDto;
		role = modelMapper.map(roleDto, Role.class);		
		privilegeDtos.stream().map(privDto -> modelMapper.map(privDto, Privilege.class))
        .forEach(privileges::add);
		
		role.setPrivileges(privileges);
		Role createdEntity = roleRepository.save(role);
		
		returnedDto = modelMapper.map(createdEntity, RoleDTO.class);	
		return returnedDto;
	}

	@Override
	public RoleDTO getRole(String name) {
		Role role = new Role();
		switch (name) {
		case "user":
			role = roleRepository.findByName("ROLE_USER");
			break;
		case "admin":
			role = roleRepository.findByName("ROLE_ADMIN");
			break;
		case "moderator":
			role = roleRepository.findByName("ROLE_MODERATOR");
			break;
		case "author":
			role = roleRepository.findByName("ROLE_AUTHOR");
			break;
		default:
			role = roleRepository.findByName("ROLE_USER");
	}
//		role = roleRepository.findByName(name);
		List<Privilege> privileges = role.getPrivileges();
		
		ModelMapper modelMapper = new ModelMapper();
    	    	
    	List<PrivilegeDTO> privilegeDto = privileges.stream()
    			.map(priv->modelMapper.map(priv, PrivilegeDTO.class))
    		    .collect(Collectors.toList());
    	
    	RoleDTO returnedDto=modelMapper.map(role, RoleDTO.class);
    	returnedDto.setPrivilages(privilegeDto);
		return returnedDto;
	}

	@Override
	public void deleteRole(long roleId) {
		Role role=roleRepository.findById(roleId);
		
		if(role==null)
			throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());	
		roleRepository.delete(role);	
	}

	@Override
	public List<RoleDTO> getRoles(int page, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoleDTO updateRole(long roleId, RoleDTO roleDto) {
		Role role =roleRepository.findById(roleId);	
		if(role==null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		
    	role.setName(roleDto.getName());
    	List<PrivilegeDTO> privilegeDtos = roleDto.getPrivilages();
		List<Privilege> privileges = new ArrayList<>();
		
		ModelMapper modelMapper = new ModelMapper();
	
		privilegeDtos.stream().map(privDto -> modelMapper.map(privDto, Privilege.class))
        .forEach(privileges::add);
    	
    	role.setPrivileges(privileges);
    	
    	Role updatedEntity = roleRepository.save(role); 	 
    	RoleDTO returnedDto=modelMapper.map(updatedEntity, RoleDTO.class);
   	
    	List<PrivilegeDTO> privilegeDto = privileges.stream()
    			.map(priv->modelMapper.map(priv, PrivilegeDTO.class))
    		    .collect(Collectors.toList());	
    	
    	returnedDto.setPrivilages(privilegeDto);
    	
		return returnedDto;
	}


	

}
