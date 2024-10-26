package com.madari.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Project {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable=false, length=50)
	private String stack;
	
	@Column(nullable=false, length=50)
	private String language;
	
	@Column(nullable=false, length=50)
	private String projectType;
	
	@Column(nullable=false, length=50)
	private String framework;
	

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(nullable=false, length=50)
    private String name;
    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Course getTutorial() {
		return course;
	}

	public void setTutorial(Course course) {
		this.course = course;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
