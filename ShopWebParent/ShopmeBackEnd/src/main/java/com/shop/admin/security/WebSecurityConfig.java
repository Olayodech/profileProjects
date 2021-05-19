package com.shop.admin.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public UserDetailsService userDetailService() {
		return new ShopMeUserdetailService();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
//	This is to specify that database will be used for authentication... not token
	public DaoAuthenticationProvider authicationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
	


	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/users/**").hasAnyAuthority("Admin")
				.antMatchers("/categories/**", "/brands/**").hasAnyAuthority("Admin", "Editor")
				.antMatchers("/products/**").hasAnyAuthority("Editor", "Admin", "Editor", "Shipper")
		.anyRequest()
		.authenticated()
		.and()
		.formLogin()
//		.loginPage("/users/login")
//		.usernameParameter("email")
		.permitAll()
		.and()
		.logout().permitAll()
		.and()
		//this configure remekmber me option
		.rememberMe()
		.key("AbcdEfghfjfkri-12345566")
		.tokenValiditySeconds(1 * 24); //2days
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**");
	}
	
	
}
