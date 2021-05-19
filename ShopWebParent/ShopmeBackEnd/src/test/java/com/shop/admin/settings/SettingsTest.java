package com.shop.admin.settings;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.shop.admin.SettingsRepository;
import com.shop.common.entity.Setting;
import com.shop.common.entity.SettingCategory;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class SettingsTest {

	@Autowired
	private SettingsRepository settingsRepository;
	
	@Test
	public void testCreateSetting() {
		Setting setting = new Setting();
		setting.setKey("SITE_NAME");
		setting.setValue("shopme");
		setting.setCategory(SettingCategory.GENERAL);
		
		Setting savedSettings = settingsRepository.save(setting);
		assertThat(savedSettings).isNot(null);
	}
	
	@Test
	public void testCreateSetting2() {
		Setting setting = new Setting();
		setting.setKey("SITE_LOGO");
		setting.setValue("shopme.png");
		setting.setCategory(SettingCategory.GENERAL);
		
		Setting savedSettings = settingsRepository.save(setting);
		assertThat(savedSettings).isNot(null);
	}
	
	@Test
	public void testCreateSetting3() {
		Setting setting = new Setting();
		setting.setKey("COPY_RIGHT");
		setting.setValue("CopyRight(c) 2021");
		setting.setCategory(SettingCategory.GENERAL);
		
		Setting savedSettings = settingsRepository.save(setting);
		assertThat(savedSettings).isNot(null);
	}
	
	
	@Test
	public void testCreateSetting4() {
		Setting setting = new Setting();
		setting.setKey("CURRENCY_ID");
		setting.setValue("1");
		setting.setCategory(SettingCategory.CURRENCY);
		
		Setting savedSettings = settingsRepository.save(setting);
		assertThat(savedSettings).isNot(null);
	}
	
	@Test
	public void testCreateSetting5() {
		Setting setting = new Setting();
		setting.setKey("CURRENCY_SYMBOL");
		setting.setValue("£");
		setting.setCategory(SettingCategory.CURRENCY);
		
		Setting savedSettings = settingsRepository.save(setting);
		assertThat(savedSettings).isNot(null);
	}
	
	@Test
	public void testListSettingsByCategory() {
		List<Setting> settings = settingsRepository.findByCategory(SettingCategory.GENERAL);
		
		settings.forEach(System.out::println);
	}
	
	@Test
	public void testCreateCurrencySetting() {
		Setting symbolPosition = new Setting("CURRENCY_SYMBOL_POSITION", "before", SettingCategory.CURRENCY);
		Setting decimalPosition = new Setting("DECIMAL_POINT_TYPE", "POINT", SettingCategory.CURRENCY);
		Setting decimalDigits = new Setting("DECIMAL_DIGIT", "2", SettingCategory.CURRENCY);
		Setting thousandPointType = new Setting("THOUSAND_POINT_TYPE", "COMMA", SettingCategory.CURRENCY);
		
		settingsRepository.saveAll(List.of(symbolPosition, decimalPosition, decimalDigits, thousandPointType));


	}
}
