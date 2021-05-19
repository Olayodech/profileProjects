package com.shop.site.mains;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shop.common.entity.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
	
	@Query("SELECT c from Category c WHERE c.categoryEnabled = true ORDER BY c.categoryName ASC")
	List<Category> findByCategoryEnabled();
	
	@Query("SELECT c From Category c WHERE c.categoryEnabled = true AND c.categoriesAlias = ?1")
	Category findByAliasEnabled(String alias);
	
	
	@Query("SELECT c FROM Category c WHERE c.categoryEnabled = true and c.categoriesAlias = ?1")
	List<Category> findByCategoriesAlias(String categoriesAlias);
}
