package com.shop.admin.products.service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.admin.products.ProductRepository;
import com.shop.common.entity.Products;

@Service
@Transactional
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<Products> listProducts(){
		return (List<Products>) productRepository.findAll();
	}
	
	public Products save(Products products) {
		if(products.getProductId() == null) {
		products.setCreatedTime(new Date());
		}
		
		if (products.getProductAlias() == null || products.getProductAlias().isEmpty()) {
			products.setProductAlias(products.getProductName().replaceAll(" ", "-"));
		}else {
			products.setProductAlias(products.getProductAlias().replaceAll(" ", "-"));
		}
		
		products.setUpdatedTime(new Date());
		
		return productRepository.save(products);
			
	}
	
	public String checkunique(Integer id, String name){
		boolean iscreatingNew;
		Products prodByName = productRepository.findByName(name);
		if(id == null) {
			iscreatingNew = false;
			if(iscreatingNew == false) {
				if(prodByName != null) {
					return "Duplicate";
			}
			}else {
				if (prodByName != null && prodByName.getProductId() != id) {
					return "Duplicate";
				}
			}
		}
		return "OK";	
	}
		
		public void updateEnabled(Integer id, Boolean enabled) {
			productRepository.updateEnabledProduct(id, enabled);
		}

		public void deleteProductId(Integer id) {
			try {
			Products products = productRepository.findById(id).get();
			productRepository.deleteById(products.getProductId());
			}catch (NoSuchElementException e) {
				throw new RuntimeException();
			}
		}

//	
//	public Products updateEnable(Products products) {
//		Products upProducts = productRepository.findById(products.getProductId()).get();
//		upProducts.setEnabled(products.isEnabled());
//		upProducts = productRepository.save(upProducts);
//		return upProducts;
//	}

		public Products getIntegerId(Integer id) {
			return productRepository.findById(id).get();
		}
}

