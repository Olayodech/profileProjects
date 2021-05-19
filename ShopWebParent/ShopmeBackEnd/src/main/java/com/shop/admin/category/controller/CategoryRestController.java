package com.shop.admin.category.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.admin.category.service.CategoryService;

@RestController
public class CategoryRestController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/category/check_unique")
	public String checkUnique(@Param("id") Integer id, @Param("name") String name, @Param("alias") String alias) {
		System.out.println("Id passed is" + id + "name is" + name);
		return categoryService.checkUnique(id, name, alias);
	}

}
