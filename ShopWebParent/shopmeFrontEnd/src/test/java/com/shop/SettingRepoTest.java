package com.shop;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.shop.common.entity.Setting;
import com.shop.common.entity.SettingCategory;
import com.shop.site.settings.SettingsRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class SettingRepoTest {

	@Autowired
	SettingsRepository settingRepository;
	
	@Test
	public void testGetGeneralSettings() {
		List<Setting> set = settingRepository.findByMultipleCategory(SettingCategory.GENERAL, SettingCategory.CURRENCY);
		set.forEach(System.out::println);
	}
	
}
