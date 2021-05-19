package com.shop.admin;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shop.common.entity.Setting;
import com.shop.common.entity.SettingCategory;

@Repository
public interface SettingsRepository extends CrudRepository<Setting, String> {
	
	List<Setting> findByCategory(SettingCategory category); //find by setting category
}
