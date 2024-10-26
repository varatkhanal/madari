package com.madari.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDetailsRequestModel {
	@NotBlank(message = "Username is required")
	private String firstName;
	 
	@NotBlank(message = "First Name is required")
	private String lastName;
	
	@NotBlank(message = "Last Name is required")
    private String userName;
	
    @Email(message = "Email should be a valid email address") 
	private String email;
    
    @Size(min = 8, message = "Password should be at least 8 characters long")
	private String password;
    
	private String role;
	
	
	public String getRole() { return role; }
	 public void setRole(String role) { this.role = role; }
	 
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {	
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
