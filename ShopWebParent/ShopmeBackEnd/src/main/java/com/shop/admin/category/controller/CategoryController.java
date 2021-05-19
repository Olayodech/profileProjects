package com.shop.admin.category.controller;

import com.shop.admin.CategoryNotFoundException;
import com.shop.admin.FileUploadUtil;
import com.shop.admin.category.service.CategoryService;
import com.shop.common.entity.Category;
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

import java.io.IOException;
import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public String listAllCategories(Model model) {
        List<Category> listcategory = categoryService.listAll();
        model.addAttribute("listcategory", listcategory);
        return "category/categories";
    }

    @GetMapping("/category/categoryform")
    public String getCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("pageTitle", "Category Form");
        List<Category> listCategoryHierachy = categoryService.listCategoriesForForm();
        model.addAttribute("listhierachy", listCategoryHierachy);
        return "category/category_Form";
    }

    @PostMapping("/category/save")
    public String createNewCategory(Category category, @RequestParam("fileImage") MultipartFile multipartFile,
    RedirectAttributes redirectAttributes){
        String fileName= StringUtils.cleanPath(multipartFile.getOriginalFilename());
        category.setCategoryImage(fileName);
        Category savedCat = categoryService.saveCategory(category);
        String uploadDir = "../category-images/"+ savedCat.getCategoryId();
        try {
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        redirectAttributes.addFlashAttribute("message", "Category" + savedCat.getCategoryName() + "saved successfully");
        return "redirect:/category";
    }
    
    @GetMapping("category/getupdate/{id}")
    public String getUpdateForm(@PathVariable("id") Integer id, Model model) {
    	 List<Category> listcategory = categoryService.listCategoriesForForm();
    	Category retrieveCat = categoryService.getCategory(id);
    	model.addAttribute("category", retrieveCat);
        model.addAttribute("listcategory", listcategory);
    	return "category/category_Form";
    }
    
    @GetMapping("category/deleteuser/{id}")
    public String deleteItem(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
    	
    	try {
        	categoryService.deleteCategory(id);
        	String categoryDir = "../category-images" + id;
        	FileUploadUtil.removeDir(categoryDir);
        	redirectAttributes.addFlashAttribute("message", "The category" + " " + id + "has been deleted");
    	}catch (CategoryNotFoundException e) {
    		redirectAttributes.addFlashAttribute("message", e.getMessage());
		}
    	
    	return "redirect:/category";
    }
}
