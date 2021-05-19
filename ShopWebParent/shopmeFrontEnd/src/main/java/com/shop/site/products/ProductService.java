package com.shop.site.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shop.common.entity.Products;
import com.shop.common.exceptions.ProductNotFoundException;

@Service
public class ProductService {

	public static final int PRODUCT_PER_PAGE = 10;
	
	@Autowired
	private ProductRepository productRepository;
	
	public Page<Products> listByCategory(int PageNum, Integer categoryId){
		String categoryIdMatch = "-" + String.valueOf(categoryId) + "-";
		Pageable pageable = PageRequest.of(PageNum -1, PRODUCT_PER_PAGE);
		
		return productRepository.listByCategory(categoryId, categoryIdMatch, pageable);
	}
	
	public Products getProducts(String alias) throws ProductNotFoundException {
		Products products = productRepository.findByProductAlias(alias);
		if(products == null) {
			throw new ProductNotFoundException("Product Not Found with alias" + alias);
		}
		return products;
	}
}
