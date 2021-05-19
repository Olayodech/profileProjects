package com.shop.admin.category;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.shop.admin.category.service.CategoryService;
import com.shop.common.entity.Category;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class CategoryServiceTest {

	@MockBean
	private CategoryRepository categoryRepository;
	
	@InjectMocks
	private CategoryService categoryService;
	
	@Test
	public void testCheckUniqueDuplicateName() {
		Integer id = null;
		String name = "Computer";
		String alias = "Computer";
		
		Category category = new Category(id, name, alias);
		Mockito.when(categoryRepository.findByCategoryName(name)).thenReturn(category);
		Mockito.when(categoryRepository.findByCategoriesAlias(alias)).thenReturn(category);
		
		String result = categoryService.checkUnique(id, name, alias);
		
		assertThat(result).isEqualTo("DuplicateName");
		
	}
	
	@Test
	public void testCheckUniqueDuplicateAlias() {
		Integer id = null;
		String name = "Computer";
		String alias = "Computer";
		
		Category category = new Category(id, name, alias);
		Mockito.when(categoryRepository.findByCategoryName(name)).thenReturn(category);
		Mockito.when(categoryRepository.findByCategoriesAlias(alias)).thenReturn(category);
		
		String result = categoryService.checkUnique(id, name, alias);
		
		assertThat(result).isEqualTo("DuplicateAlias");	
	}
}
