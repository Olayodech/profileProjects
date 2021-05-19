package com.shop.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "currency", schema = "shopdata")
public class Currency {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer currencyId;
	
	@Column(name = "currency_name", nullable = false)
	private String currencyName;
	
	@Column(name = "currency_symbol", nullable = false)
	private String currencySymbol;
	
	@Column(name = "currency_code", nullable = false)
	private String currencyCode;
	
	

	public Currency() {
		super();
	}

	public Currency(String currencyName, String currencySymbol, String currencyCode) {
		super();
		this.currencyName = currencyName;
		this.currencySymbol = currencySymbol;
		this.currencyCode = currencyCode;
	}

	public Integer getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(Integer currencyId) {
		this.currencyId = currencyId;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public String getCurrencySymbol() {
		return currencySymbol;
	}

	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	@Override
	public String toString() {
		return currencyName + " - " + currencyCode + " - " + currencySymbol;
//		return currencyName + " - " + currencyCode + " - " + currencySymbol;

	}
	

}
