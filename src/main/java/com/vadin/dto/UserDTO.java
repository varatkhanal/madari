package com.vadin.dto;

import java.io.Serializable;
import java.util.List;

import com.vadin.model.Privilege;

public class UserDTO implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userId;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private String encryptedPassword;
    private String emailVarificationToken;
    private Boolean emailVerificationStatus=false;
    private RoleDTO role;

	private List<Privilege> privileges; 
	
	public RoleDTO getRole() {
		return role;
	}
	  
	public void setRole(RoleDTO role) { 
		this.role = role; 
	}

	public String getEmailVarificationToken() {
		return emailVarificationToken;
	}

	public void setEmailVarificationToken(String emailVarificationToken) {
		this.emailVarificationToken = emailVarificationToken;
	}
 
    public String getUserId() {
        return userId;
    }
 
    public void setUserId(String userId) {
        this.userId = userId;
    }
 
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
 
    public String getEncryptedPassword() {
        return encryptedPassword;
    }
 
    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

	public Boolean getEmailVerificationStatus() {
		return emailVerificationStatus;
	}

	public void setEmailVerificationStatus(Boolean emailVerificationStatus) {
		this.emailVerificationStatus = emailVerificationStatus;
	}

	public void setPrivileges(List<Privilege> list) {
		// TODO Auto-generated method stub
		this.privileges= list;
		
	}
	public List<Privilege> getPrivileges(){
		return privileges;
	}
    
    
}
