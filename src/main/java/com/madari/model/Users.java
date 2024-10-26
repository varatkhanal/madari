package com.vadin.model;

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
    
    public Users(){
    	
    }
    
    

	public Users(long id, String userId, String firstName, String lastName, String userName, String email,
			String encryptedPassword, Role role, List<Post> posts) {
		super();
		this.id = id;
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email = email;
		this.encryptedPassword = encryptedPassword;
		this.role = role;
		this.posts = posts;
		//this.projects = projects;
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
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



	public String getEncryptedPassword() {
		return encryptedPassword;
	}



	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}



	public Role getRole() {
		return role;
	}



	public void setRole(Role role) {
		this.role = role;
	}



	public List<Post> getPosts() {
		return posts;
	}



	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}



//	public List<Project> getProjects() {
//		return projects;
//	}
//
//
//
//	public void setProjects(List<Project> projects) {
//		this.projects = projects;
//	}



	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
