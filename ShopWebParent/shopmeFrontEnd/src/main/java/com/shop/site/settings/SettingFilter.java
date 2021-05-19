package com.shop.site.settings;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.shop.common.entity.Setting;

public class SettingFilter implements Filter {
	
	@Autowired
	private SettingsService settingsService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		String url = servletRequest.getRequestURL().toString();
		
		if(url.endsWith(".css") || url.endsWith(".js") || url.endsWith(".png") || url.endsWith(".jpg")) {
			chain.doFilter(servletRequest, response);
			return;
		}
		
		List<Setting> generalSettings = settingsService.getGeneralSettingBag();
		
		
		generalSettings.forEach(settings -> {
			System.out.println(settings);
			request.setAttribute(settings.getKey(), settings.getValue());
		});
		
		chain.doFilter(request, response);
	}

}
