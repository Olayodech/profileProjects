package com.shop.site;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shop.common.entity.Category;
import com.shop.site.mains.CategoryService;

@Controller
public class MainController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping(" ")
	public String viewSiteHomePage(Model model) {
		List<Category> category = categoryService.listNoChildrenCategory();
		model.addAttribute("listenabled", category);
		return  "index";
	}
	
	@GetMapping("/login")
	public String viewLoginPage() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("authentication value " + authentication.getName());
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "login";
		}
		return "redirect:/";
	}
}
