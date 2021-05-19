package com.shop.common.entity;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "brand", schema = "shopdata")
public class Brands {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long brandId;
	
	@Column(name = "brand_name", unique = true, length = 45, nullable = false, updatable = false )
	private String brandName;
	
	@Column(name = "brand_logo", unique = true, length = 45, nullable = false, updatable = false )
	private String brandLogo;
	
	@ManyToMany
	@JoinTable(name = "brand_category", schema = "shopdata",
		joinColumns = @JoinColumn(name= "brand_id"),
		inverseJoinColumns = @JoinColumn(name = "category_id")
			)
	private Set<Category> brandCategory = new HashSet<>();
	
	

	public Brands(Long brandId, String brandName, String brandLogo, Set<Category> brandCategory) {
		super();
		this.brandId = brandId;
		this.brandName = brandName;
		this.brandLogo = brandLogo;
		this.brandCategory = brandCategory;
	}
	
	

	public Brands(Long brandId, String brandName) {
		super();
		this.brandId = brandId;
		this.brandName = brandName;
	}



	public Brands() {
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBrandLogo() {
		return brandLogo;
	}

	public void setBrandLogo(String brandLogo) {
		this.brandLogo = brandLogo;
	}

	public Set<Category> getBrandCategory() {
		return brandCategory;
	}

	public void setBrandCategory(Set<Category> brandCategory) {
		this.brandCategory = brandCategory;
	}
	
	@Transient
	public String getLogoPath() {
		if(this.brandId == null) return "images/ecom.jpg";
		return "/brands-logo/" + this.brandId + "/" + this.brandLogo;
	}
	
	

}
