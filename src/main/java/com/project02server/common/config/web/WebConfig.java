package com.project02server.common.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	private final String FRONTEND = "http://localhost:3005";

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins(FRONTEND)
			.allowedMethods("GET", "POST","DELETE","PUT","PATCH")
			.maxAge(3000);
	}
}

