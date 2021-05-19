package com.shop.admin.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

	@Autowired
	UserServices userServices;
	
	@PostMapping("/users/check_email")
	public String checkDuplicateEmail(@Param("id") Integer Id, @Param("email") String email) {
		return userServices.isEmailUnique(Id, email) ? "OK" : "Duplicated";
	}
}
