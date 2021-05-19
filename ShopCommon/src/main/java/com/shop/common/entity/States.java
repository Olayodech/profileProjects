package com.shop.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "states", schema = "shopdata")
public class States {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int stateId;
	
	@Column(name = "state_name", nullable = false)
	private String stateName;
	
	@ManyToOne
	@JoinColumn(name = "countryId")
	private Country country;
	
	public States() {
	}

	public States(String stateName, Country country) {
		super();
		this.stateName = stateName;
		this.country = country;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	
	
}
