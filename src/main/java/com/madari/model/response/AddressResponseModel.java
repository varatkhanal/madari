package com.madari.model.response;

import org.springframework.hateoas.RepresentationModel;

public class AddressResponseModel extends RepresentationModel{
	private String streetName;
	private String city;
	private String country;
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
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
}
