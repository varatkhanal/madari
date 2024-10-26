package com.madari.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name="addresses")
public class Address {
	@Id
	@GeneratedValue
	private long id;
	
	@Column(length=100, nullable=false)
	private String streetName;
	
	@Column(length=15, nullable=false)
	private String city;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="uid")
	private Users user;
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getStreetName() {
		return streetName;
	}
	
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	
}
