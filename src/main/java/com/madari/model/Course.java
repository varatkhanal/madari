package com.madari.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "course")
//@PrimaryKeyJoinColumn(name = "course_id")
public class Course extends Post{
	
		@Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_sequence")
		//@GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "course_id")
	    private Long id; 
	
		@Column(nullable = false, length = 50)
		private String name;
	
		
		@JsonIgnore
		@OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<Chapter> chapters = new ArrayList<>();
		  
//		@OneToMany(mappedBy = "course")
//	    private List<Project> projects;
		
		

		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public String getName() {
			return name;
		}

		
		public void setName(String name) {
			this.name = name;
		}

		public List<Chapter> getChapters() {
			return chapters;
		}

		public void setChapters(List<Chapter> chapters) {
			this.chapters = chapters;
		}

//		public List<Project> getProjects() {
//			return projects;
//		}
//
//		public void setProjects(List<Project> projects) {
//			this.projects = projects;
//		}
	
}
