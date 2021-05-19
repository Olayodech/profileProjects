package com.shop.site.settings;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.common.entity.Setting;
import com.shop.common.entity.SettingCategory;

@Service
public class SettingsService {
	
	@Autowired
	private SettingsRepository settingsRepository;
	
	public List<Setting> getGeneralSettingBag() {
		return settingsRepository.findByMultipleCategory(SettingCategory.GENERAL, SettingCategory.CURRENCY);	
	}
	
	public EmailSettingBag getEmailSettings() {
		List<Setting> settings = settingsRepository.findByCategory(SettingCategory.MAIL_SERVER);
		settings.addAll(settingsRepository.findByCategory(SettingCategory.MAIL_TEMPLATE));
		return new EmailSettingBag(settings);
	} 

}
