package com.shop.site.products;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.shop.common.entity.Products;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Products, Integer> {
	
	@Query("SELECT p from Products p WHERE p.enabled = true AND (p.category.categoryId = ?1 OR p.category.allParentId LIKE %?2%)")
//			+ "ORDER BY p.productName ASC")
	Page<Products> listByCategory(Integer categoryId, String categoryIdMatch, Pageable pageable);
	
	@Query(value="SELECT * FROM PRODUCTS WHERE p.enabled =true AND"
			+ "MATCH(name, short_description, full-description) AGAINST (?1)", nativeQuery = true)
	Page<Products> search(String keyword, Pageable pageable);
	
	Products findByProductAlias(String alias);

}
