package com.shop.common.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="category", schema = "shopdata")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    @Column(name = "category_name", length = 128, nullable = false, unique = true)
    private String categoryName;

    @Column(name = "category_alias", length = 128, nullable = false, unique = true)
    private String categoriesAlias;

    @Column(name = "category_image", length = 64, nullable = false)
    private String categoryImage;

    private boolean categoryEnabled;
    
    @Column(name = "all_parent_ids", length = 256)
    private String allParentId;
    
    @OneToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    @OrderBy("categoryName asc")
    private Set<Category> children = new HashSet<>();

    public Category() {
    }
    
    

    public Category(Integer categoryId, String categoryName, String categoriesAlias) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoriesAlias = categoriesAlias;
	}



	public static Category copyIdandName(Category category){
        Category newcat = new Category();
        newcat.setCategoryId(category.getCategoryId());
        newcat.setCategoryName(category.getCategoryName());
        return newcat;
    }

    public static Category copyIdandNameOnly(Integer Id, String name){
        Category newcat = new Category();
        newcat.setCategoryId(Id);
        newcat.setCategoryName(name);
        return newcat;
    }

    public static Category copyFullCategory(Category category){
        Category copycat = new Category();
        copycat.setCategoryName(category.getCategoryName());
        copycat.setCategoryId(category.getCategoryId());
        copycat.setCategoryImage(category.getCategoryImage());
        copycat.setCategoriesAlias(category.getCategoriesAlias());
        copycat.setCategoryEnabled(category.isCategoryEnabled());
        copycat.setHasChildren(category.getChilderen().size() > 0);
        return copycat;
    }

    public static Category copyfulls(Category category, String name){
        Category copycat = Category.copyFullCategory(category);
        copycat.setCategoryName(name);
        return  copycat;
    }

    public Category(Integer categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoriesAlias() {
        return categoriesAlias;
    }

    public void setCategoriesAlias(String categoriesAlias) {
        this.categoriesAlias = categoriesAlias;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

//    public String getCategoryEnabled() {
//        return categoryEnabled;
//    }
//
//    public void setCategoryEnabled(String categoryEnabled) {
//        this.categoryEnabled = categoryEnabled;
//    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public Set<Category> getChilderen() {
        return children;
    }

    public void setChildren(Set<Category> childeren) {
    	this.children = childeren;
    }
    
    public boolean isCategoryEnabled() {
		return categoryEnabled;
	}



	public void setCategoryEnabled(boolean categoryEnabled) {
		this.categoryEnabled = categoryEnabled;
	}



	@Transient
    public String getImagePath() {
    	System.out.println("image path" + "/category-images/" + this.categoryId + "/" + this.categoryImage);
    	return "/category-images/" + this.categoryId + "/" + this.categoryImage;
    }
  
    public boolean isHasChildren() {
		return hasChildren;
	}


	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
	}

	@Transient
	private boolean hasChildren;
}
