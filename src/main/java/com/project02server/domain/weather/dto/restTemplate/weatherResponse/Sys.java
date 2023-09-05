package com.project02server.domain.weather.dto.restTemplate.weatherResponse;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
class Sys {

	private Integer type;
	private Long id;
	private String country;
	private Long sunrise;
	private Long sunset;
}
