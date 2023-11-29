package com.project02server.weather.dto.restTemplate.forcastResponse;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
public class ForecastResponse {

	private String cod;
	private int message;
	private int cnt;
	private List<Forecast> list;
	private City city;

}
