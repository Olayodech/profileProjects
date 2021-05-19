package com.shop.common.entity;

import java.util.List;

public class SettingBag {

	private List<Setting> listSettings;

	public SettingBag(List<Setting> listSettings) {
		super();
		this.listSettings = listSettings;
	}	
	
//	create utility method that get settings by key
	
	public Setting get(String key) {
		int index = listSettings.indexOf(new Setting(key)); //this will be used by the hashcode in setting class
		
//if hashcode is equal, index will be greater than 0 otherwise it will be 0
		if(index > 0) {
			return listSettings.get(index);
		}
		return null;
	}
	//create utility to get the value of a key
	public String getValue(String key) {
		Setting setting = get(key); //call the get(key) method above
		if(setting != null) {
			return setting.getValue();
		}
		return null;
	}
	
	//utility to update key and value of setting
	public void updateSettings(String key, String value) {
		Setting setting = get(key);
		if(setting != null && value!=null) {
			setting.setValue(value);
		}
	}
	
	//utility to list settings
	public List<Setting> list(){
		return listSettings;
	}

}
