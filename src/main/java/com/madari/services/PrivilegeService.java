package com.vadin.services;

import java.util.List;

import com.vadin.dto.PrivilegeDTO;
import com.vadin.dto.RoleDTO;

public interface PrivilegeService {
	List<PrivilegeDTO> getPrivileges(RoleDTO roleDto);
    void deletePrivilege(long privilegeId);
    PrivilegeDTO updatePrivilege(long id, PrivilegeDTO privilegeDto);
	List<PrivilegeDTO> getPrivileges();
	PrivilegeDTO createPrivilege(PrivilegeDTO privilegeDto);
}
