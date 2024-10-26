package com.vadin.model;

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

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(nullable=false, length=50)
    private String name;
    
//    @ManyToMany
//    @JoinColumn(name = "course_id")
//    private List<Users> mentor=new ArrayList<>();
//
//    @ManyToMany(mappedBy = "projects")
//    private List<Users> mentors = new ArrayList<>();

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

//	public List<Users> getMentor() {
//		return mentor;
//	}
//
//	public void setMentor(List<Users> mentor) {
//		this.mentor = mentor;
//	}
// 

}
