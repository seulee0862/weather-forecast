package com.project02server.weather.dto.restTemplate.weatherResponse;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
class Wind {

	private Double speed;
	private Integer deg;
	private Double gust;
}
