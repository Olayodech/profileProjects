package com.shop.admin.brand;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shop.admin.FileUploadUtil;
import com.shop.admin.category.service.CategoryService;
import com.shop.common.entity.Brands;
import com.shop.common.entity.Category;
import com.shop.common.entity.User;

@Controller
public class BrandController {

	@Autowired
	private BrandService brandService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/brands")
	public String getBrands(Model model) {
		System.out.println("Inside Brands brands");
		Brands brandsobject = new Brands();
		Iterable<Brands> brands = brandService.listBrand();
		System.out.println("Is there any brands from the database" + brands);
		model.addAttribute("listbrands", brands);
		model.addAttribute("brands", brandsobject);
		return "brands/list_brands";
	}
	
	@GetMapping("/brands/brand_newform")
	public String getNewForm(Model model) {
		Brands brandsobject = new Brands();
		List<Category> category = categoryService.listCategoriesForForm();
		model.addAttribute("brands", brandsobject);
		model.addAttribute("listcategory", category);
		System.out.println("The catgegory is" + category);
		return "brands/newbrand_form";
	}
	
	@PostMapping("/brands/new_brand")
	public String saveNewBrand(Brands brands, RedirectAttributes redirectAttributes, Model model, @RequestParam("fileImage") MultipartFile multipartFile) throws IOException {
		
		if(!multipartFile.isEmpty()) {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			brands.setBrandLogo(fileName);
			Brands savedBrands = brandService.saveBrand(brands);
			redirectAttributes.addFlashAttribute("message", savedBrands);
			
			String uploadDir = "brand-photos/" + brands.getBrandId();
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
			
		}
		
		return "redirect:/brands";
	}
		
	
}
