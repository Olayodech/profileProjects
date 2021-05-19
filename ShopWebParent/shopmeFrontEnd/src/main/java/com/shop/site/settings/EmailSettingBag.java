package com.shop.site.settings;

import java.util.List;

import com.shop.common.entity.Setting;
import com.shop.common.entity.SettingBag;

public class EmailSettingBag extends SettingBag {

	public EmailSettingBag(List<Setting> listSettings) {
		super(listSettings);
	}
	
	public String getSmtpHost() {
		return super.getValue("MAIL_HOST");
	}
	
	public String getUserName() {
		return super.getValue("MAIL_USERNAME");
	}
	
	public Integer getPort() {
		return Integer.parseInt(super.getValue("MAIL_PORT"));
	}

	public String getMailPassword() {
		return super.getValue("MAIL_PASSWORD");
	}
	
	public String getSmtpAuth() {
		return super.getValue("SMTP_AUTH");
	}
	
	public String getSmtpSecured() {
		return super.getValue("SMTP_SECURED");
	}
	
	public String getMailFrom() {
		return super.getValue("MAIL_FROM");
	}
	
	public String getSenderName() {
		return super.getValue("MAIL_SENDER_NAME");
	}
	
	public String getCustomerVerificationSubject() {
		return super.getValue("CUSTOMER_VERIFY_SUBJECT");
	}
	
	public String getCustomerVerificationContent() {
		return super.getValue("CUSTOMER_VERIFY_CONTENT");
	}
}
