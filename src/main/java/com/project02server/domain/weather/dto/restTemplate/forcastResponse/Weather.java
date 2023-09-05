package com.project02server.domain.weather.dto.restTemplate.forcastResponse;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
class Weather {

	private int id;
	private String main;
	private String description;
	private String icon;

}
