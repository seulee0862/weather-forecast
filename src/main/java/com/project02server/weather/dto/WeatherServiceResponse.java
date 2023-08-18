package com.project02server.weather.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class WeatherServiceResponse {

	WeatherInfo currentWeatherInfo;

	List<WeatherInfo> forecastInfos;

	@Builder
	public WeatherServiceResponse(WeatherInfo currentWeatherInfo
		, List<WeatherInfo> forecastInfos) {

		this.currentWeatherInfo = currentWeatherInfo;
		this.forecastInfos = forecastInfos;
	}

	public static WeatherServiceResponse of (WeatherInfo currentWeatherInfo,
		List<WeatherInfo> forecastInfos) {

		return WeatherServiceResponse.builder()
			.currentWeatherInfo(currentWeatherInfo)
			.forecastInfos(forecastInfos)
			.build();
	}
}
