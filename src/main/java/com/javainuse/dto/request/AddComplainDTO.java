package com.javainuse.dto.request;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

public class AddComplainDTO {

	
	 private int countryID;
	
    @NotEmpty(message = "title can't be empty")
    private String title;
   
   
    @NotEmpty(message = "location Details can't be empty")
    private String locationDetails;

    @NotEmpty(message = "Complaint can't be empty")
    private String complain;

    
    
	public AddComplainDTO() {
		super();
	}

	public AddComplainDTO(@NotEmpty int countryID, @NotEmpty(message = "title can't be empty") String title,
			@NotEmpty(message = "location Details can't be empty") String locationDetails,
			@NotEmpty(message = "Complaint can't be empty") String complain) {
		super();
		this.countryID = countryID;
		this.title = title;
		this.locationDetails = locationDetails;
		this.complain = complain;
	}

	public int getCountryID() {
		return countryID;
	}

	public void setCountryID(int countryID) {
		this.countryID = countryID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocationDetails() {
		return locationDetails;
	}

	public void setLocationDetails(String locationDetails) {
		this.locationDetails = locationDetails;
	}

	public String getComplain() {
		return complain;
	}

	public void setComplain(String complain) {
		this.complain = complain;
	}
    
    
	
}
