package com.shop;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.nullValue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.shop.common.entity.Category;
import com.shop.site.mains.CategoryRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CategoryTest {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Test
	public void testListEnabledCategory() {
		List<Category> category = categoryRepository.findByCategoryEnabled();
		category.forEach(item ->{
			System.out.println(item.getCategoryName());
		});
	}
	
	@Test
	public void testGetEnabledAlias() {
		String alias = "computer";
		Category category = categoryRepository.findByAliasEnabled(alias);
		assertThat(category).isNot(null);
	}
	
	@Test
	public void testFindCategoryByAlias() {
		String alias = "something";
		Category category = categoryRepository.findByAliasEnabled(alias);
		assertThat(category).isNot(null);
	}
}
