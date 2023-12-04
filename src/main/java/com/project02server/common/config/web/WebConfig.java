package com.project02server.common.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.project02server.common.config.web.interceptor.JwtInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	private final String[] FRONTENDS = {"http://localhost:3005, https://3.34.18.190:3005", "https://nalc.site:3005"};

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins(FRONTENDS)
			.allowedMethods("GET", "POST","DELETE","PUT","PATCH")
			.maxAge(3000);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new JwtInterceptor())
			.addPathPatterns("/user/subscribe/**");
	}
}
