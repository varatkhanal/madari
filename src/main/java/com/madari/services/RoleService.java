package com.vadin.services;

import java.util.List;


import com.vadin.dto.RoleDTO;


public interface RoleService {
	RoleDTO createRole(RoleDTO roleDto);
	RoleDTO getRole(String name);
    void deleteRole(long roleId);
	List<RoleDTO> getRoles(int page, int limit);
	RoleDTO updateRole(long id, RoleDTO roleDto);
}
