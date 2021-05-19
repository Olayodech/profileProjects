package com.shop.common.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "country", schema = "shopdata")
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int countryId;
	
	@Column(name = "country_name", nullable = false)
	private String countryName;
	
	@Column(name = "country_code", nullable = false)
	private String countryCode;
	
	@OneToMany(mappedBy = "country")
	private Set<States> states;


	public Country() {
	}

	public Country(String countryName, String countryCode, Set<States> states) {
		super();
		this.countryName = countryName;
		this.countryCode = countryCode;
		this.states = states;
	}

	public Country(String countryName) {
		super();
		this.countryName = countryName;
	}
	
	

	public Country(int countryId) {
		super();
		this.countryId = countryId;
	}

	public Country(String countryName, String countryCode) {
		super();
		this.countryName = countryName;
		this.countryCode = countryCode;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
//
//	public Set<States> getStates() {
//		return states;
//	}
//
//	public void setStates(Set<States> states) {
//		this.states = states;
//	}

	@Override
	public String toString() {
		return "Country [countryId=" + countryId + ", countryName=" + countryName + ", countryCode=" + countryCode
				+ ", states=" + states + "]";
	}
	
	
	
}
