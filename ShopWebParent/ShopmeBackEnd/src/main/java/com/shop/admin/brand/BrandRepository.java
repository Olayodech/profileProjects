package com.shop.admin.brand;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shop.common.entity.Brands;

@Repository
public interface BrandRepository extends PagingAndSortingRepository<Brands, Long> {

	@Query("SELECT b FROM Brands b WHERE b.brandName = :brandName")
	Brands findBrandByName(@Param("brandName") String brandName);
	
	@Query("SELECT 	NEW Brands (b.brandId, b.brandName) FROM Brands b ORDER BY b.brandName ASC")
	List<Brands> findByAll();
	
}
