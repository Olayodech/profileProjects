package com.shop.common.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import antlr.collections.List;


@Entity
@Table(name = "products", schema = "shopdata")
public class Products {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productId;
	
	@Column(name = "product_name", unique = true, length = 256, nullable = false)
	private String productName;
	
	@Column(name = "product_alias", unique = true, length = 256, nullable = false)
	private String productAlias;
	
	@Column(name = "product_shortdescription", length = 512, nullable = false)
	private String shortDescription;
	
	@Column(name = "product_fulldecription", length = 4096, nullable = false)
	private String fullDescription;
	
	@Column(name = "created_time ")
	private Date createdTime;
	
	@Column(name = "updated_time")
	private Date updatedTime;
	
	private boolean enabled;
	private boolean inStock;
	
	private float cost;
	private float price;
	
	@Column(name = "discount_percent")
	private float discountPercent;
	
	private float lenght;
	private float width;
	private float height;
	private float weight;
	
	@Column(name = "main_image")
	private String mainImage;
	
	@ManyToOne
	@JoinColumn(name = "category_Id")
	private Category category;

	@ManyToOne
	@JoinColumn(name = "brand_Id")
	private Brands brands;
	
	@OneToMany(mappedBy = "products", cascade = javax.persistence.CascadeType.ALL)
	private Set<ProductImage> productImage = new HashSet<>();
	
	@OneToMany(mappedBy ="products", cascade = CascadeType.ALL)
	private java.util.List<ProductDetails> productDetails = new ArrayList<ProductDetails>();

	
	public Products(Integer productId, String productName, String productAlias, String shortDescription,
			String fullDescription, Date createdTime, Date updatedTime, boolean enabled, boolean inStock, float cost,
			float price, float discountPercent, float lenght, float width, float height, float weight,
			Category category, Brands brands) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productAlias = productAlias;
		this.shortDescription = shortDescription;
		this.fullDescription = fullDescription;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.enabled = enabled;
		this.inStock = inStock;
		this.cost = cost;
		this.price = price;
		this.discountPercent = discountPercent;
		this.lenght = lenght;
		this.width = width;
		this.height = height;
		this.weight = weight;
		this.category = category;
		this.brands = brands;
	}

	public Products() {
	}
	
	
	
	
	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductAlias() {
		return productAlias;
	}

	public void setProductAlias(String productAlias) {
		this.productAlias = productAlias;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getFullDescription() {
		return fullDescription;
	}

	public void setFullDescription(String fullDescription) {
		this.fullDescription = fullDescription;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isInStock() {
		return inStock;
	}

	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(float discountPercent) {
		this.discountPercent = discountPercent;
	}

	public float getLenght() {
		return lenght;
	}

	public void setLenght(float lenght) {
		this.lenght = lenght;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Brands getBrands() {
		return brands;
	}

	public void setBrands(Brands brands) {
		this.brands = brands;
	}
	

	public String getMainImage() {
		return mainImage;
	}

	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}

	public Set<ProductImage> getProductImage() {
		return productImage;
	}

	public void setProductImage(Set<ProductImage> productImage) {
		this.productImage = productImage;
	}

	@Override
	public String toString() {
		return "Products [productId=" + productId + ", productName=" + productName + ", productAlias=" + productAlias
				+ "]";
	}
	
	public  void addProductImage(String imageName){
		this.productImage.add(new ProductImage(imageName, this));
	}
	
	
	@Transient
	public String getMainImagePath() { 
		if(productId == null || mainImage == null) return "/images/ecom.png ";
		return "/product-images/" + this.productId + "/" + this.mainImage;
	}

	public java.util.List<ProductDetails> getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(java.util.List<ProductDetails> productDetails) {
		this.productDetails = productDetails;
	}
	
	public void addProductDetails(String name, String value) {
		this.productDetails.add(new ProductDetails(name, value, this));
	}
	
	@Transient
	public float getDiscountPrice() {
		if(discountPercent > 0) {
			return price - (price * discountPercent/100);
		}
		
		return price;
	}

	

}
