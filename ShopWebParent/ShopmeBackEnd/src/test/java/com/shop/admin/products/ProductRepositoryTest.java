package com.shop.admin.products;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.shop.common.entity.Brands;
import com.shop.common.entity.Category;
import com.shop.common.entity.Products;
import com.sun.el.stream.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ProductRepositoryTest {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private TestEntityManager testEntityManager;

	@Test
	public void testCreateProduct() {

		Brands brand = testEntityManager.find(Brands.class, 1L);
		Category category = testEntityManager.find(Category.class, 1);

		Products products = new Products();
		products.setProductName("Acer laptop");
		products.setShortDescription("A good smart laptop from Acer INC");
		products.setFullDescription("The best Acer type machine money can buy");
		products.setBrands(brand);
		products.setCategory(category);
		products.setPrice(1200);
		products.setCost(750);
		products.setCreatedTime(new Date());
		products.setUpdatedTime(new Date());
		products.setProductAlias("Acer Aspire");
		products.setEnabled(true);
		products.setInStock(true);

		Products savedProducts = productRepository.save(products);

		assertThat(savedProducts).isNotNull();
		assertThat(savedProducts.getProductId()).isGreaterThan(0);
	}

	@Test
	public void listAllProducts() {
		Iterable<Products> products = productRepository.findAll();

		System.out.println(products.iterator().next());

	}

	@Test
	public void getProductById() {
		java.util.Optional<Products> getProd = productRepository.findById(2);
		System.out.println(getProd.get());
		assertThat(getProd).isNotNull();
	}

	@Test
	public void testUpdateProduct() {
		java.util.Optional<Products> getProd = productRepository.findById(1);
		getProd.get().setPrice(851);
		productRepository.save(getProd.get());

		Products updated = testEntityManager.find(Products.class, 1);
		assertThat(updated.getPrice()).isEqualTo(851);

	}

	@Test
	public void testDeleteProduct() {
		productRepository.deleteById(1);
		java.util.Optional<Products> getProd = productRepository.findById(1);
		assertThat(!getProd.isPresent());
	}

	@Test
	public void testAllBrands() {

	}

	@Test
	public void testGetImages() {
		Integer productId = 2;
		Products products = productRepository.findById(productId).get();
		products.setMainImage("main image.jpg");
		products.addProductImage("extra image 1.png");
		products.addProductImage("extra_image_2.png");
		products.addProductImage("extra_image_3.png");
		
		Products savedProd = productRepository.save(products);
		assertThat(savedProd.getProductImage().size() ).isEqualTo(3);
	}
	
	@Test
	public void addDetails() {
		Products products = testEntityManager.find(Products.class, 3);
		products.addProductDetails("Processor", "AMD");
		Products savedProducts = productRepository.save(products);
		assertThat(savedProducts.getProductDetails().size()).isGreaterThan(0);
	}

}
