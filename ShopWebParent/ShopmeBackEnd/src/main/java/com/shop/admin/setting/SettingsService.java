package com.shop.admin.setting;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.admin.SettingsRepository;
import com.shop.common.entity.GeneralSettingBag;
import com.shop.common.entity.Setting;
import com.shop.common.entity.SettingCategory;

@Service
public class SettingsService {

	@Autowired
	SettingsRepository settingsRepository;
	
	public List<Setting> listAllSettings(){
		return (List<Setting>) settingsRepository.findAll();
	}
	
	//call general setting bag
	public GeneralSettingBag generalSettingBag() {
		List<Setting> settings = new ArrayList<>();
		List<Setting> generalSettings = settingsRepository.findByCategory(SettingCategory.GENERAL);
		List<Setting> currencySettings = settingsRepository.findByCategory(SettingCategory.CURRENCY);

		settings.addAll(generalSettings);
		settings.addAll(currencySettings);
		
		return new GeneralSettingBag(settings);
		
	}
	
	//saving collection of settings object
	public void saveAllSettings(Iterable<Setting> settings) {
		settingsRepository.saveAll(settings);
	}
	
	public List<Setting> getMailSettings(){
		return settingsRepository.findByCategory(SettingCategory.MAIL_SERVER);
	}
	
	public List<Setting> getMailTemplateSettings(){
		return settingsRepository.findByCategory(SettingCategory.MAIL_TEMPLATE);
	}
	
	
	
}


