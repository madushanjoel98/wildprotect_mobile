package com.javainuse.dto.request;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class RegisterUserDTO {
private int countryID;


@NotBlank(message = "Email can't to be blank")
private String email;


private String mobilenumber;


@NotBlank(message = "Email can't to be blank")
private String password;


public int getCountryID() {
	return countryID;
}


public void setCountryID(int countryID) {
	this.countryID = countryID;
}


public String getEmail() {
	return email;
}


public void setEmail(String email) {
	this.email = email;
}


public String getMobilenumber() {
	return mobilenumber;
}


public void setMobilenumber(String mobilenumber) {
	this.mobilenumber = mobilenumber;
}


public String getPassword() {
	return password;
}


public void setPassword(String password) {
	this.password = password;
}



}
