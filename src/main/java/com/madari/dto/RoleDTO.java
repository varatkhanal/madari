package com.madari.dto;

import java.util.List;

public class RoleDTO {
	
    private long id;
    private String name;
    private String description;
    private List<PrivilegeDTO> privilages;
    private List<String> privilege;

    
	public List<String> getPrivilege() {
		return privilege;
	}

	public void setPrivilege(List<String> privilege) {
		this.privilege = privilege;
	}

	public long getId() {
        return id;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	public List<PrivilegeDTO> getPrivilages() {
		return privilages;
	}

	public void setPrivilages(List<PrivilegeDTO> privilages) {
		this.privilages = privilages;
	}

}
