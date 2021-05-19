package com.shop.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "customer", schema = "shopdata")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerId;
	
	
	@Column(name = "username", length = 126, nullable = false, unique = true)
	private String customerUsername;
	
	@Column(name = "customer_email", length = 126, nullable = false, unique = true)
	private String customerEmail;
	
	@Column(name = "customer_password", length = 64, nullable = false)
	private String customerPassword;
	
	@Column(name = "first_name", length = 45, nullable = false)
	private String firstName;
	
	@Column(name = "last_name", length = 45, nullable = false)
	private String lastName;
	
	@Column(name = "phone_number", length = 16, nullable = false, unique = true)
	private String phoneNumber;
	
	@Column(name = "address_line1", length = 250, nullable = false)
	private String addressLine1;
	
	@Column(name = "address_line2", length = 250)
	private String addressLine2;
	
	@Column(name = "city", length = 250)
	private String city;
	
	@Column(name = "state", length = 250)
	private String state;
	
	@ManyToOne
	@JoinColumn(name = "countryId")
	private Country country;
	
	@Column(name = "post_code", length = 250)
	private String postCode;
	
	@Column(name = "created_time")
	private Date createdTime;
	
	private boolean enabled;
	
	@Column(name = "verification_code", length = 64)
	private String verificationCode;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "authentication_type", length = 10)
	private AuthenticationType authenticationType;

	public Customer() {
	}
	
	public Customer(String customerEmail, String customerPassword, String firstName, String lastName,
			String phoneNumber, String addressLine1, String addressLine2, String city, String state, Country country,
			String postCode, Date createdTime, Boolean enabled, String verificationCode) {
		super();
		this.customerEmail = customerEmail;
		this.customerPassword = customerPassword;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.state = state;
		this.country = country;
		this.postCode = postCode;
		this.createdTime = createdTime;
		this.enabled = enabled;
		this.verificationCode = verificationCode;
	}





	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	
	public AuthenticationType getAuthenticationType() {
		return authenticationType;
	}

	public void setAuthenticationType(AuthenticationType authenticationType) {
		this.authenticationType = authenticationType;
	}

	public Country getCountry() {
		return country;
	}



	public void setCountry(Country country) {
		this.country = country;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	

	public String getCustomerUsername() {
		return customerUsername;
	}

	public void setCustomerUsername(String customerUsername) {
		this.customerUsername = customerUsername;
	}

	@Override
	public String toString() {
		return "Customer [customerEmail=" + customerEmail + ", customerPassword=" + customerPassword + ", firstName="
				+ firstName + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber + ", country=" + country + "]";
	}
	
	public String getFullName() {
		return firstName + " " + lastName;
	}
	
	

	



	
	
	
	
}
