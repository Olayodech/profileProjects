package com.shop.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product_details", schema = "shopdata")
public class ProductDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productDetailsId;
	
	@Column(name = "product_details")
	private String productDetails;
	
	@ManyToOne
	@JoinColumn(name = "productId")
	Products products;
	
	

	public ProductDetails() {
		
	}

	public ProductDetails(String productDetails, Products products) {
		super();
		this.productDetails = productDetails;
		this.products = products;
	}

	public ProductDetails(String name, String value, Products products2) {
		
	}

	public int getProductDetailsId() {
		return productDetailsId;
	}

	public void setProductDetailsId(int productDetailsId) {
		this.productDetailsId = productDetailsId;
	}

	public String getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(String productDetails) {
		this.productDetails = productDetails;
	}

	public Products getProducts() {
		return products;
	}

	public void setProducts(Products products) {
		this.products = products;
	}
	
	
	

}
