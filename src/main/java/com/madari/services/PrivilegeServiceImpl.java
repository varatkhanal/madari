package com.madari.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madari.dto.PrivilegeDTO;
import com.madari.dto.RoleDTO;
import com.madari.exceptions.ErrorMessages;
import com.madari.exceptions.UserServiceException;
import com.madari.model.Privilege;
import com.madari.repository.PrivilegeRepository;

@Service
public class PrivilegeServiceImpl implements PrivilegeService{
	
	@Autowired
	PrivilegeRepository privilegeRepository;
	@Override
	public List<PrivilegeDTO> getPrivileges(RoleDTO roleDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePrivilege(long privilegeId) {
		Privilege role=privilegeRepository.findById(privilegeId);	
		if(role==null)
			throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());	
		privilegeRepository.delete(role);		
	}

	@Override
	public PrivilegeDTO updatePrivilege(long id, PrivilegeDTO privilegeDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PrivilegeDTO> getPrivileges() {
		List<PrivilegeDTO> privilegeDtos = new ArrayList<>();
		List<Privilege> privilegeList = (List<Privilege>) privilegeRepository.findAll();
		ModelMapper modelMapper = new ModelMapper();
		privilegeList.stream().map(priv->modelMapper.map(priv,PrivilegeDTO.class)).forEach(privilegeDtos::add);
		//collect(Collectors.toList());	
		return privilegeDtos;
	}

	@Override
	public PrivilegeDTO createPrivilege(PrivilegeDTO privilegeDto) {
		Privilege privilege = new Privilege();
		ModelMapper modelMapper = new ModelMapper();
		privilege = modelMapper.map(privilegeDto, Privilege.class);
		Privilege savedPrivilege = privilegeRepository.save(privilege);
		PrivilegeDTO returned = modelMapper.map(savedPrivilege, PrivilegeDTO.class);
		return returned;
	}

}
