package com.shop.admin.products;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.shop.common.entity.Products;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Products, Integer> {

	@Query("SELECT p FROM Products p WHERE p.productName = :productName")
	public Products findByName(String productName);

	
	@Query("UPDATE Products p SET p.enabled = ?2 WHERE p.productId = ?1")  //?2 means second parameter in the method name and ?1 means first parameter
	@Modifying
	public Products updateEnabledProduct(Integer id, Boolean enabled);
	


}
