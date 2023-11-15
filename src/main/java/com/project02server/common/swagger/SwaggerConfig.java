package com.project02server.common.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI springOpenAPI() {
		return new OpenAPI()
			.info(
				new Info().title("API 문서")
					.description("API 스펙에 대해서 설명해주는 문서입니다.")
					.version("v0.0.1"));
	}
}
