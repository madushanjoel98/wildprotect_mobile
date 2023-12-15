package com.javainuse.model;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
    private  PublicLogin userlogin;
     
	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	

	public PublicLogin getLogin() {
		return userlogin;
	}



	public void setLogin(PublicLogin login) {
		this.userlogin = login;
	}



	public String getToken() {
		return this.jwttoken;
	}
}