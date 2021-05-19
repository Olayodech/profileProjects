package com.shop.admin.setting;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shop.admin.FileUploadUtil;
import com.shop.admin.currency.CurrencyRepository;
import com.shop.common.entity.Currency;
import com.shop.common.entity.GeneralSettingBag;
import com.shop.common.entity.Setting;
import com.shop.common.entity.SettingBag;

@Controller
public class SettingsController {

	@Autowired
	SettingsService settingsService;
	
	@Autowired
	CurrencyRepository currencyRepository;
	
	GeneralSettingBag settingBag;
	
	@GetMapping("/settings")
	public String listAll(Model model) {
		System.out.println("Inside list all settings");
		List<Setting> settings = settingsService.listAllSettings();
		List<Currency> currency =  currencyRepository.findAllByOrderByCurrencyNameAsc();
		model.addAttribute("currencylist", currency);
		
		for (Setting setts : settings) {
			model.addAttribute(setts.getKey(), setts.getValue());
		}
		
		return "/setting/settingslist";
	}
	
	@PostMapping("/settings/savegeneral")
	//HttpServletRequest is used because the form doesn't have the object bound, to read we use HttpServlet
	public String saveGeneralSettings(@RequestParam("fileImage") MultipartFile multipartFile, HttpServletRequest request, RedirectAttributes redirectAttributes) throws IOException {
			
		System.out.println("Inside Save general ");
		 settingBag = settingsService.generalSettingBag();
		 
		saveSiteLogo(multipartFile, settingBag);
		saveCurrencySymbol(settingBag, request);
		updateSettingValueFromForm(request, settingBag.list());
		
		redirectAttributes.addFlashAttribute("name", "General Settings Saved");
		return "redirect:/settings";
		}

	private void saveSiteLogo(MultipartFile multipartFile, GeneralSettingBag settingBag) throws IOException {
		if(!multipartFile.isEmpty()) {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			String value = "site-logo" + fileName;
			
			settingBag.updateSiteLogo(value);
			
			String uploadDir = "site-logo";
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		}
	}
	
	private void saveCurrencySymbol(GeneralSettingBag generalSettingBag, HttpServletRequest request) {
		Integer currencyId = Integer.parseInt(request.getParameter("CURRENCY_ID"));
		Optional<Currency> findByIdResult = currencyRepository.findById(currencyId);
		
		if(findByIdResult.isPresent()) {
			Currency currency = findByIdResult.get();
			String symbol = currency.getCurrencySymbol();
			settingBag.updateCurrencySymbol(symbol);
		}
	}
	
	private void updateSettingValueFromForm(HttpServletRequest request, List<Setting> listSettings) {
		for (Setting setting : listSettings) {
			String value = request.getParameter(setting.getKey());
			if(value != null) {
				setting.setValue(value);
			}
		}
		settingsService.saveAllSettings(listSettings);
	}
	
	@PostMapping("/settings/savemailserver")
	public String saveMailSettings(HttpServletRequest request, RedirectAttributes redirectAttributes) throws IOException {
		List<Setting> mailServerSettings = settingsService.getMailSettings();
		updateSettingValueFromForm(request, mailServerSettings);
		
		redirectAttributes.addAttribute("message", "Mail Server saved");
		return "redirect:/settings";
	}
	
	@PostMapping("/settings/savemailtemplate")
	public String saveMailTemplatesSettings(HttpServletRequest request, RedirectAttributes redirectAttributes) throws IOException {
		List<Setting> mailTemplateSettings = settingsService.	getMailTemplateSettings();
		updateSettingValueFromForm(request, mailTemplateSettings);
		
		redirectAttributes.addAttribute("message", "Mail Templates settings saved");
		return "redirect:/settings";
	}
	
	
}
