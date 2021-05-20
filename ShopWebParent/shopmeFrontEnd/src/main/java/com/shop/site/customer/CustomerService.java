package com.shop.site.customer;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shop.common.entity.AuthenticationType;
import com.shop.common.entity.Country;
import com.shop.common.entity.Customer;
import com.shop.site.country.CountryRepository;

import net.bytebuddy.utility.RandomString;

@Service
public class CustomerService {

	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomersRepository customersRepository;

	
	public List<Country> listAllCountries(){
		return countryRepository.findAllByOrderByCountryNameAsc();
	}
	
	public boolean isEmailUnique(String email) {
		Customer customer =  customersRepository.findByCustomerEmail(email);
		return customer == null;
	}
	
	public void registerCustomer(Customer customer) {
		 encodePassword(customer);
		customer.setEnabled(false);
		customer.setCreatedTime(new Date());
		
		String randomCode = RandomString.make(64);
		customer.setVerificationCode(randomCode);
		
		customersRepository.save(customer);
	}
	
	public Customer getCustomerByEmail(String email) {
		return customersRepository.findByCustomerEmail(email);
	}

	private void encodePassword(Customer customer) {
		String password = passwordEncoder.encode(customer.getCustomerPassword());
		customer.setCustomerPassword(password);
		
	}
	
	public boolean verifyAccount(String verificationCode) {
		Customer customerToGet = customersRepository.findByVerificationCode(verificationCode);
		
		if(customerToGet== null || customerToGet.isEnabled()) {
			return false;
		}
		else {
			customersRepository.enable(customerToGet.getCustomerId());
			return true;
		}
	}
	
	public void updateAuthentication(Customer customer, AuthenticationType type) {
		if(!customer.getAuthenticationType().equals(type)) {
			customersRepository.updateAuthenticationType(customer.getCustomerId(), type);
		}
	}
	
	public void addNewCustomerUponOauthLogin(String name, String email, String countryCode) {
		Customer customer = new Customer();
		customer.setCustomerUsername(email);
		customer.setCustomerEmail(email);
		setName(name, customer);
//		customer.setFirstName(name);
		customer.setEnabled(true);
		customer.setCreatedTime(new Date());
		customer.setAuthenticationType(AuthenticationType.GOOGLE);
		customer.setCustomerPassword("");
		customer.setAddressLine1("");
		customer.setAddressLine2("");
		customer.setCity("");
		customer.setState("");
		customer.setPhoneNumber("");
		customer.setPostCode("");
		customer.setCountry(countryRepository.findByCountryCode(countryCode));
		
		customersRepository.save(customer);
	}
	
	private void setName(String name, Customer customer) {
		String[] nameArray = name.split(" ");
		if (nameArray.length < 2) {
			customer.setFirstName(name);
			customer.setLastName("");
		}else {
			String firstName = nameArray[0];
			customer.setFirstName(firstName);
			String lastName = name.replaceFirst(firstName, "");
			customer.setLastName(lastName);
		}
	}
}
