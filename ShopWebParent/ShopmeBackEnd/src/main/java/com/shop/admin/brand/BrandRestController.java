package com.shop.admin.brand;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.admin.category.controller.CategoryDto;
import com.shop.common.entity.Brands;
import com.shop.common.entity.Category;


@RestController
public class BrandRestController {
	
	@Autowired
	BrandService brandService;
	
	@PostMapping("/brands/checkBrands")
	public String checkDuplicateBrand(@Param("name") String name) {
		Boolean returnedBrands = brandService.checkDuplicate(name);
		System.out.println("Returned brand from rest endpoint is " + returnedBrands);
		if(returnedBrands != true) {
			return "Duplicate";
		}else return "OK";
	}
	
	@GetMapping("/brands/{id}/categories")
	public List<CategoryDto> listCategoryByBrand(@PathVariable("id") Long id){
		List<CategoryDto> listCats = new ArrayList<>();
		Brands brands = brandService.getCategoryBrand(id);
		Set<Category> category = brands.getBrandCategory();
		
		for (Category category2 : category) 
		{
			System.out.println(category2.getCategoryName());
			CategoryDto dto = new CategoryDto(category2.getCategoryId(), category2.getCategoryName());
			listCats.add(dto);
		}
		return listCats;
	}

}
