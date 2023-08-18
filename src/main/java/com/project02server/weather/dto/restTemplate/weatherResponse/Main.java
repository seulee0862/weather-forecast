package com.project02server.weather.dto.restTemplate.weatherResponse;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Main {

	private Double temp;
	private Double feels_like;
	private Double temp_min;
	private Double temp_max;
	private Integer pressure;
	private Integer humidity;
	private Integer sea_level;
	private Integer grnd_level;
	
}
