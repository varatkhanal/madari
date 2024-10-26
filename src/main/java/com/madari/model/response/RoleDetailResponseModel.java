package com.madari.model.response;

import java.util.List;

public class RoleDetailResponseModel {
	private long id;
    private String name;
    private String description;
    private List<String> privileges;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<String> getPrivilages() {
		return privileges;
	}
	public void setPrivileges(List<String> privileges) {
		this.privileges = privileges;
	}
	
    

}