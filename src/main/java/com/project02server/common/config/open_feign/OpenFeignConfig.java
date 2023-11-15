package com.project02server.common.config.open_feign;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;

@Configuration
@EnableFeignClients(basePackages = {"com.project02server.oauth.service"})
public class OpenFeignConfig {

	@Bean
	Logger.Level feignLoggerLevel() {

		return Logger.Level.FULL;
	}
}
