package com.shop.admin.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.admin.products.service.ProductService;
import com.shop.common.entity.Products;

@RestController
public class ProductRestController {
	
	@Autowired
	ProductService productService;;
	
	
	@PostMapping("/products/checkunique")
	public String checkunique(@Param("id") Integer id, @Param("name") String name){
		System.out.println("Id is " + id + "name is" + name);
		return productService.checkunique(id, name);
	}
}
