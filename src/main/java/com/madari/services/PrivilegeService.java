package com.madari.services;

import java.util.List;

import com.madari.dto.PrivilegeDTO;
import com.madari.dto.RoleDTO;

public interface PrivilegeService {
	List<PrivilegeDTO> getPrivileges(RoleDTO roleDto);
    void deletePrivilege(long privilegeId);
    PrivilegeDTO updatePrivilege(long id, PrivilegeDTO privilegeDto);
	List<PrivilegeDTO> getPrivileges();
	PrivilegeDTO createPrivilege(PrivilegeDTO privilegeDto);
}
