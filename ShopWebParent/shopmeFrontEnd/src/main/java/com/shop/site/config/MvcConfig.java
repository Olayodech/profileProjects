package com.shop.site.config;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		exposeDirectory("../brand-photos", registry);
		exposeDirectory("../product-images", registry);
		exposeDirectory("../category-images", registry);
	}
	
	public void exposeDirectory(String pathPattern, ResourceHandlerRegistry registry) {
		Path path = Paths.get(pathPattern);
		String absolutePath = path.toFile().getAbsolutePath();
		
		String logicalPath = pathPattern.replaceFirst("../", "")  + "/**";
		
		registry.addResourceHandler(logicalPath).addResourceLocations("file:/" + absolutePath + "/");

	}
}
