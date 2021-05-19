package com.shop.admin.brand;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.shop.admin.category.CategoryRepository;
import com.shop.common.entity.Brands;
import com.shop.common.entity.Category;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class BrandTest {
	
	@Autowired
	private BrandRepository brandRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Test
	public void testCreateBrand() {
		Brands brands = new Brands();
		Category category = categoryRepository.findById(1).get();
		Set<Category> cat = new HashSet<>();
		cat.add(category);
		brands.setBrandLogo("Hp");
		brands.setBrandName("hewletparker");
		brands.setBrandCategory(cat);
		brandRepository.save(brands);
	}
	
	@Test
	public void testAllBrands() {
		Iterable<Brands> brands = brandRepository.findByAll();
		System.out.println(brands.iterator().next());
	}

}
