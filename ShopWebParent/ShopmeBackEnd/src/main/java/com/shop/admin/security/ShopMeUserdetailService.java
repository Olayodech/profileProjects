package com.shop.admin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.shop.admin.user.UserRepository;
import com.shop.common.entity.User;

public class ShopMeUserdetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User user = userRepository.findUserByEmail(email);
		
		if(user != null) {
//			return new ShopMeUserDetails(user);
			return new ShopMeUserDetails(user);
		}
		throw new UsernameNotFoundException("user not found" + email) ;
	}

}
