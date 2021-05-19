package com.shop.site.settings;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shop.common.entity.Setting;
import com.shop.common.entity.SettingCategory;

@Repository
public interface SettingsRepository extends CrudRepository<Setting, String> {
	
	List<Setting> findByCategory(SettingCategory category); //find by setting category
	 
	@Query("SELECT s FROM Setting s WHERE s.category = ?1 OR s.category= ?2")
	List<Setting> findByMultipleCategory(SettingCategory catOne, SettingCategory catTwo); //find by setting category

}
