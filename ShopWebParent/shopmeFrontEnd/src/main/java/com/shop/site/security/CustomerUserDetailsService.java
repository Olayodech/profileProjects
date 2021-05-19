package com.shop.site.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.shop.common.entity.Customer;
import com.shop.site.customer.CustomersRepository;

public class CustomerUserDetailsService  implements UserDetailsService{
	
	@Autowired
	private CustomersRepository customersRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Customer customer = customersRepository.findByCustomerEmail(email);
		if(customer == null) {
			throw new UsernameNotFoundException("username not found");
		}
		return new CustomerUserDetails(customer);
	}
	
	

}
