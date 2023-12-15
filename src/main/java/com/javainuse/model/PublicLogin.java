package com.javainuse.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class PublicLogin {

	@Id
	@Column(nullable = false, updatable = false)
	@SequenceGenerator(name = "primary_sequence", sequenceName = "primary_sequence", allocationSize = 1, initialValue = 10000)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "primary_sequence")
	private Long publicid;

	@Column(nullable = false, unique = true, length = 45)
	private String email;

	@Column(unique = true, length = 45)
	private String mobilenumber;

	@JsonIgnore
	@Column(nullable = false, length = 512)
	private String password;

	@JsonIgnore
	@OneToMany(mappedBy = "publicid")
	private Set<PublicComplain> publicidPublicComplains = new HashSet();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "countryid_id", nullable = false)
	private Countries countryid;

	public Long getPublicid() {
		return publicid;
	}

	public void setPublicid(final Long publicid) {
		this.publicid = publicid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getMobilenumber() {
		return mobilenumber;
	}

	public void setMobilenumber(final String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public Set<PublicComplain> getPublicidPublicComplains() {
		return publicidPublicComplains;
	}

	public void setPublicidPublicComplains(final Set<PublicComplain> publicidPublicComplains) {
		this.publicidPublicComplains = publicidPublicComplains;
	}

	public Countries getCountryid() {
		return countryid;
	}

	public void setCountryid(final Countries countryid) {
		this.countryid = countryid;
	}

}
