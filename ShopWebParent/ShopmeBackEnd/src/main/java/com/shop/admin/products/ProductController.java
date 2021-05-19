package com.shop.admin.products;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shop.admin.FileUploadUtil;
import com.shop.admin.brand.BrandService;
import com.shop.admin.products.service.ProductService;
import com.shop.common.entity.Brands;
import com.shop.common.entity.Products;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private BrandService brandService;
	
	@GetMapping("/products")
	public String listAllProducts(Model model) {
		List<Products> prod = productService.listProducts();
		model.addAttribute("listproducts", prod);
	
		return "products/list_products";
	}
	 
	@GetMapping("/product/productForm")
	public String prodForm(Model model) {
		Products products = new Products();
		products.setEnabled(true);
		products.setInStock(true);
		List<Brands> listAllBrand = brandService.listAllBrands();
		model.addAttribute("products", products);
		model.addAttribute("listbrands", listAllBrand);
		
		model.addAttribute("pageTitle", "Create New product");
		return "products/new_productForm";
	}
	
	@PostMapping("/products/save")
	public String saveProduct(Products products, @RequestParam("fileImage") MultipartFile multipartFile, 
			RedirectAttributes redirectAttributes, @RequestParam("extraImage") MultipartFile[] extraImagemultiparts,
			@RequestParam(name = "detailsName", required = false) String[] detailNames,
			@RequestParam(name = "detailsValue", required = false) String[] detailValues) throws IOException {
		
	setMainImageName(multipartFile, products);	
	setExtraImageNames(extraImagemultiparts, products);
	setProductDetails(detailNames, detailValues, products);
	
	Products savedproduct = productService.save(products);
	
	saveUploadedImages(multipartFile,extraImagemultiparts, savedproduct);
	
	redirectAttributes.addFlashAttribute("message", "Product saved successfully");
	return "redirect:/products";
}
	
	private void setProductDetails(String[] detailNames, String[] detailValues, Products products) {
		if(detailNames == null || detailNames.length == 0) {
			return;
		}
		for (int count =0; count >= detailNames.length; count ++) {
			String name = detailNames[count];
			String value = detailValues[count];
			
			if(!name.isEmpty() && !value.isEmpty()) {
				products.addProductDetails(name, value);
			}
		}
		
	}

	private void saveUploadedImages(MultipartFile multipartFile, MultipartFile[] extraImagemultiparts,
			Products savedproduct) throws IOException {
		if(!multipartFile.isEmpty()) {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			String imageToUpload = "../product-images/" + savedproduct.getProductId();
			FileUploadUtil.saveFile(imageToUpload, fileName, multipartFile);
		}
		
		if(extraImagemultiparts.length > 0) {
			String uploadDir =  "../product-images/" + savedproduct.getProductId() + "/extras";
			for (MultipartFile multipartFile2 : extraImagemultiparts) {
				if(!multipartFile2.isEmpty()) {
					String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
					FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
				}	
			}
		}

		
	}

	private void setMainImageName(MultipartFile mainImageMultipart, Products products) throws IOException {
		if(!mainImageMultipart.isEmpty()) {
			String fileName = StringUtils.cleanPath(mainImageMultipart.getOriginalFilename());
			products.setMainImage(fileName);
			
			
		}else {
			productService.save(products);
		}
	}
	
	private void setExtraImageNames(MultipartFile[] extraImageMultiparts, Products products) {
		if(extraImageMultiparts.length > 0) {
			for (MultipartFile multipartFile : extraImageMultiparts) {
				if(!multipartFile.isEmpty()) {
					String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
					products.addProductImage(fileName);
				}
			}
		}
}
	
	@GetMapping("/products/{id}/enable/{status}")
	public String updateProductEnable(@PathVariable("id") Integer id, @PathVariable("status") boolean status, RedirectAttributes redirectAttributes) {
		productService.updateEnabled(id, status);
		redirectAttributes.addFlashAttribute("message", "Product has been enabled");
		return "products/new_productForm";
	
	}
	
	@GetMapping("/products/deleteproduct/{id}")
	public String deleteProduct(@PathVariable("id") Integer id, Model model) {
		String productImageDir =  "../product-images/" + id + "/extras";
		String productImagesDir =  "../product-images/" + id;
		FileUploadUtil.removeDir(productImageDir);
		FileUploadUtil.removeDir(productImagesDir);

		productService.deleteProductId(id);
		return "redirect:/products";
		
	}
	
	@GetMapping("/product/getupdate/{id}")
	public String editProduct(@PathVariable("id") Integer id, Products products, Model model, RedirectAttributes redirectAttributes) {
		try {
			List<Brands> brands = brandService.listAllBrands();
			model.addAttribute("listbrands", brands);
		Products products2 = productService.getIntegerId(id);
		model.addAttribute("products", products2);
		return "/products/new_productForm";
		}catch (NoSuchElementException e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
			return "redirect:/products";
		}
	}	
}






