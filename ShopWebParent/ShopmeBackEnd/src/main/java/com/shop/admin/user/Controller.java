package com.shop.admin.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shop.admin.FileUploadUtil;
import com.shop.common.entity.Role;
import com.shop.common.entity.User;

import javassist.compiler.ast.Keyword;

@org.springframework.stereotype.Controller
public class Controller {

	@Autowired
	private UserServices userServices;

	@GetMapping("/users")
	public String listByPage(Model model) {
		return listPage(1, model, "firstName", "asc", "keyword");
	}

	@GetMapping("/users/page/{pageNum}")
	public String listPage(@PathVariable(name = "pageNum") int pageNum, Model model,
			@Param("sortField") String sortField, @Param("sortDir") String sortDir, @Param("keyword") String keyword) {
		Page<User> pageUser = userServices.listByPage(pageNum, sortField, sortDir, keyword);
		List<User> user = pageUser.getContent();
		
		
		
		model.addAttribute("listuuser", user);

		long startCount = (pageNum - 1) * userServices.users_Page + 1;
		long endCount = startCount + userServices.users_Page - 1;

		if (endCount > pageUser.getTotalElements()) {
			endCount = pageUser.getTotalElements();
		}

		String reverseSortDirt = sortDir.equals("asc") ? "desc" : "asc";

//		model.addAttribute("listuuser", pageUser);
		model.addAttribute("currentpage", pageNum);
		model.addAttribute("totalPages", pageUser.getTotalPages());
		model.addAttribute("startcount", startCount);
		model.addAttribute("endcount", endCount);
		model.addAttribute("totalItems", pageUser.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSort", reverseSortDirt);
		model.addAttribute("keyword", keyword);
		return "users";

	}

	@GetMapping("/create_user")
	public String showNewUserForm(Model model) {
		User users = new User();
		users.setEnabled(true);
		List<Role> roles = userServices.listRoles();
		model.addAttribute("listroles", roles);
		model.addAttribute("users", users);
		model.addAttribute("pageTitle", "Create User");
		return "new_userForm";
	}

	@PostMapping("/users/save")
	public String createUser(User user, RedirectAttributes redirectAttributes, @RequestParam("image") MultipartFile multipartFile ) throws IOException {
		
		if(!multipartFile.isEmpty()) {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			user.setPhotos(fileName);
			User users = userServices.saveUser(user);
			
			String uploadDir = "user-photos/" + users.getId(); ///files will be saved here
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

			
		}
		
		redirectAttributes.addFlashAttribute("message", "user saved successfully");
		return "redirect:/users";
	}

	@GetMapping("/users/edit/{id}")
	public String editUser(@PathVariable(name = "id") Integer id, Model model, RedirectAttributes redirectAttributes) {
		List<Role> roles = userServices.listRoles();
		try {
			User users = userServices.getUserId(id);
			model.addAttribute("users", users);
			model.addAttribute("pageTitle", "Edit User: User Id: " + id);
			model.addAttribute("listroles", roles);

			return "new_userForm";
		} catch (UserNotFoundException e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
			return "redirect:/users";
		}
	}

	@GetMapping("/users/delete/{id}")
	public String deleteUser(@PathVariable(name = "id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {
		try {
			userServices.deleteUser(id);
			redirectAttributes.addFlashAttribute("message", "User deleted successfully");
		} catch (UserNotFoundException e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
		}
		return "redirect:/users";
	}
	
	@GetMapping("/users/export_csv")
	public void exportToCSV(HttpServletResponse response) throws IOException {
		Iterable<User> userslist =userServices.listAll();
		UserCsvExporter exporter = new UserCsvExporter();
		exporter.export(userslist, response);
	}
	
	
	



}
