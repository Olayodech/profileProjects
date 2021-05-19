package com.shop.common.entity;

import java.util.List;

//CLASS TO UPDATE GENERAL GETTINGS

public class GeneralSettingBag extends SettingBag {

	public GeneralSettingBag(List<Setting> listSettings) {
		super(listSettings);
	}
	
	public void updateCurrencySymbol(String value) {
		//CALL THE UPDATESETTINGS IN SUPER CLASS I:E THE SETTINGBAG
		super.updateSettings("CURRENCY_SYMBOL", value);
	}

	public void updateSiteLogo(String value) {
		super.updateSettings("SITE_LOGO", value);
	}
	
	
}
