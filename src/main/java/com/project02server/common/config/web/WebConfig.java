package com.project02server.common.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	private final String[] FRONTENDS = {"https://3.34.18.190:3005", "https://namomdaero.site"};

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins(FRONTENDS)
			.allowedMethods("GET", "POST","DELETE","PUT","PATCH")
			.maxAge(3000);
	}
}