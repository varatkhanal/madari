package com.madari.services;

import java.util.List;

import com.madari.dto.RoleDTO;


public interface RoleService {
	RoleDTO createRole(RoleDTO roleDto);
	RoleDTO getRole(String name);
    void deleteRole(long roleId);
	List<RoleDTO> getRoles(int page, int limit);
	RoleDTO updateRole(long id, RoleDTO roleDto);
}
