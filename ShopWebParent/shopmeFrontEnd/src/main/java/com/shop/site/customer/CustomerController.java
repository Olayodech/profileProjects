package com.shop.site.customer;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.shop.common.entity.Country;
import com.shop.common.entity.Customer;
import com.shop.site.settings.EmailSettingBag;
import com.shop.site.settings.SettingsService;
import com.shop.site.utility.Utilityclass;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private SettingsService settingService;
	
	@GetMapping("/register")
	public String showRegisterForm(Model model) {
		List<Country> listAllCountries = customerService.listAllCountries();
		
		model.addAttribute("listcountry", listAllCountries);
		model.addAttribute("pageTitle", "Customer registration");
		model.addAttribute("customer", new Customer());
		
		return "register/registerForm";
	}
	
	@PostMapping("/create_customer")
	public String createCustomer(Customer customer, Model model, HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {
		
		customerService.registerCustomer(customer);
		
		sendVerificationEmail(request, customer);
		
		return "register/register_success";
	}

	private void sendVerificationEmail(HttpServletRequest request, Customer customer) throws UnsupportedEncodingException, MessagingException {
		EmailSettingBag emailSettingBag = settingService.getEmailSettings();
		JavaMailSenderImpl mailSender = Utilityclass.prepareMailSender(emailSettingBag);
		
		String toAddress = customer.getCustomerEmail();
		String subject = emailSettingBag.getCustomerVerificationSubject();
		String content = emailSettingBag.getCustomerVerificationContent();
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper mimeHelper = new MimeMessageHelper(message);
		
		mimeHelper.setFrom(emailSettingBag.getMailFrom(), emailSettingBag.getSenderName());
		mimeHelper.setTo(toAddress);
		mimeHelper.setSubject(subject);
		
		content = content.replace("[[name]]", customer.getFullName());
		
		String verifyURL = Utilityclass.getSiteURL(request) + "/verify?code=" +customer.getVerificationCode();
		content = content.replace("[[URL]]", verifyURL);
		
		System.out.println("to addres" + toAddress);
		System.out.println("url" + verifyURL);
		
		mailSender.send(message);
		
		
 	}
	
	@GetMapping("/verify")
	public String verifyAccount(@Param("code") String code, Model model) {
		 Boolean verified = customerService.verifyAccount(code);
		 
		 return "register/" + (verified ? "verify_success" : "verify_fail");
	}
	
}
