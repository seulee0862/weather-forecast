package com.project02server.domain.weather.dto.restTemplate.weatherResponse;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Weather {

	private Integer id;
	private String main;
	private String description;
	private String icon;
}
