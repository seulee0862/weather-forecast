package com.project02server.domain.weather.dto.restTemplate.forcastResponse;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
class City {
	private int id;
	private String name;
	private Coord coord;
	private String country;
	private int population;
	private int timezone;
	private long sunrise;
	private long sunset;

}
