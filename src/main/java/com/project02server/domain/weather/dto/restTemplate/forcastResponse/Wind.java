package com.project02server.domain.weather.dto.restTemplate.forcastResponse;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
class Wind {
	private double speed;
	private int deg;
	private double gust;
}
