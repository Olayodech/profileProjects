package com.shop.site.utility;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.shop.site.settings.EmailSettingBag;

public class Utilityclass {

	public static String getSiteURL(HttpServletRequest request) {
		String siteURL = request.getRequestURL().toString();
		return siteURL.replace(request.getServletPath(), "");
	}
	
	public static JavaMailSenderImpl prepareMailSender(EmailSettingBag emailSettingBag) {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(emailSettingBag.getSmtpHost());
		mailSender.setPort(emailSettingBag.getPort());
		mailSender.setUsername(emailSettingBag.getUserName());
		mailSender.setPassword(emailSettingBag.getMailPassword());
		
		Properties mailProperties = new Properties();
		mailProperties.setProperty("mail.smtp.auth", emailSettingBag.getSmtpAuth());
		mailProperties.setProperty("mail.smtp.starttls.enable", emailSettingBag.getSmtpSecured());
		
		mailSender.setJavaMailProperties(mailProperties);
		
		return mailSender;
	}
	
	
}
