package com.project02server.weather.dto.restTemplate.forcastResponse;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
class MainInfo {

	private double temp;
	private double feels_like;
	private double temp_min;
	private double temp_max;
	private int pressure;
	private int sea_level;
	private int grnd_level;
	private int humidity;
	private double temp_kf;
}
