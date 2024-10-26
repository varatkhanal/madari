package com.madari.dto;

import java.io.Serializable;
import java.util.List;

import com.madari.model.Privilege;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
	

    
    
}
