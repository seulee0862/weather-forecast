package com.project02server.common.config.actuator;

import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActuatorConfig {

	@Bean
	public InMemoryHttpExchangeRepository createTraceRepository() {
		return new InMemoryHttpExchangeRepository();
	}
}
