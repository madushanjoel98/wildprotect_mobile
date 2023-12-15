package com.javainuse.model;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AdminTypes {
	  @Id 
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private int admintyID;
	   
	  @Column
	  private String name;
	   
	  @JsonIgnore
	  @OneToMany(mappedBy = "adminTypes")
	  private Set<Admin> admintyAdmins=new HashSet<>();

	public Set<Admin> getAdmintyAdmins() {
		return admintyAdmins;
	}

	public void setAdmintyAdmins(Set<Admin> admintyAdmins) {
		this.admintyAdmins = admintyAdmins;
	}

	public int getAdmintyID() {
		return admintyID;
	}

	public void setAdmintyID(int admintyID) {
		this.admintyID = admintyID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	  
	

}
