package com.shop.site.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.shop.common.entity.AuthenticationType;
import com.shop.common.entity.Customer;
import com.shop.site.customer.CustomerService;

@Component
public class Oauth2LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	
	@Autowired
	private CustomerService customerService;
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		CustomerOauth2User customerOauth2User = (CustomerOauth2User) authentication.getPrincipal();
		
		String name = customerOauth2User.getName();
		String email = customerOauth2User.getEmail();
		String countryCode = request.getLocale().getCountry();
		
		Customer customer = customerService.getCustomerByEmail(email);
		if(customer == null) {
			customerService.addNewCustomerUponOauthLogin(name, email, countryCode);
		}else {
			customerService.updateAuthentication(customer, AuthenticationType.GOOGLE);
		}
		super.onAuthenticationSuccess(request, response, authentication);
	}
}
