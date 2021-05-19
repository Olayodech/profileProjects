package com.shop.site.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.shop.common.entity.Customer;

public class CustomerUserDetails implements UserDetails{

	private Customer customer;

	public CustomerUserDetails(Customer customer) {
		super();
		this.customer = customer;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		return customer.getCustomerPassword();
		}

	@Override
	public String getUsername() {
		return customer.getCustomerEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
//		return customer.isEnabled();
		return true;
	}
	
	public String getFullName() {
		return customer.getFirstName() + " " + customer.getLastName();
	}
	
	
}
