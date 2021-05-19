package com.shop.site.products;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shop.common.entity.Category;
import com.shop.common.entity.Products;
import com.shop.common.exceptions.ProductNotFoundException;
import com.shop.site.mains.CategoryService;

@Controller
public class ProductController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/c/{categoryAlias}")
	public String viewCategoryFirstPage(@PathVariable("categoryAlias") String categoryAlias, Model model) {
		System.out.println("before view looking for products");

		return viewCategoryByPage(categoryAlias, model, 1);
	}
	
	@GetMapping("/c/{categoryAlias}/page/{pageNum}")
	public String viewCategoryByPage(@PathVariable("categoryAlias") String categoryAlias, Model model, @PathVariable("pageNum") int pageNum) {

		Category category = categoryService.getCategoryByAlias(categoryAlias);
		if(category ==  null) {
			return "error/404";
		}
		List<Category> listCategoryParents = categoryService.getCategoryPatents(category);
		
		Page<Products> productPage = productService.listByCategory(pageNum, category.getCategoryId());
		
		List<Products> listProducts = productPage.getContent();
		
		long startCount = (pageNum -1) + ProductService.PRODUCT_PER_PAGE + 1;
		long endCount = startCount + productService.PRODUCT_PER_PAGE -1;
		
		if(endCount > productPage.getTotalElements()) {
			endCount = productPage.getTotalElements();
		}
		
		model.addAttribute("listcategory", listCategoryParents);
		model.addAttribute("title", category.getCategoryName());
		model.addAttribute("listproducts", listProducts);
		model.addAttribute("category", category);


		return "productsbycategory";
	}
	
	@GetMapping("/p/{productAlias}")
	public String viewProductDetails(@PathVariable("productAlias") String alias, Model model) {
		try {
			Products products = productService.getProducts(alias);
			List<Category> listCategoryParents = categoryService.getCategoryPatents(products.getCategory());
			model.addAttribute("listcategory", listCategoryParents);
			model.addAttribute("products", products);

			return "productDetails";
		} catch (ProductNotFoundException e) {
			return "error/404";
		}
	}
	

}
