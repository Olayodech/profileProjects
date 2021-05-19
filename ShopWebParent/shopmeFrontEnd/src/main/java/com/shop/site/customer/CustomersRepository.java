package com.shop.site.customer;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shop.common.entity.AuthenticationType;
import com.shop.common.entity.Customer;

@Repository
public interface CustomersRepository extends CrudRepository<Customer, Integer>{

	@Query("SELECT c FROM Customer c WHERE c.customerEmail = ?1")
	Customer findByCustomerEmail(String email);
	
	@Query("SELECT c FROM Customer c WHERE c.verificationCode = ?1")
	Customer findByVerificationCode(String code);
	
	@Query("UPDATE Customer c SET c.enabled = true, c.verificationCode = null WHERE c.id = ?1")
	@Modifying
	void enable(Integer id);
	
	@Query("UPDATE Customer c SET c.authenticationType = ?2 WHERE c.customerId = ?1")
	@Modifying
	public void updateAuthenticationType(Integer customerId, AuthenticationType type);
}
