package com.madari.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "users")
public class Users {
	@Id
    @GeneratedValue
    private long id;
    
    @Column(nullable = false, unique = true)
    private String userId;
    
    @Column(nullable = false, length = 50)
    private String firstName;
    
    @Column(nullable = false, length = 50)
    private String lastName;
    
    @Column(nullable = false, length = 50)
    private String userName;
    
    @Column(nullable = false, length = 120)
    private String email;
    
    @Column(nullable = false)
    private String encryptedPassword;

    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;
    
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> posts;
    
//    @ManyToMany
//    @JoinTable(
//        name = "mentor_project",
//        joinColumns = @JoinColumn(name = "user_id"),
//        inverseJoinColumns = @JoinColumn(name = "project_id"))
//    private List<Project> projects = new ArrayList();
//    

    public String getFirstName() {
    	return firstName;
    }
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
