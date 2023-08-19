package com.project02server.domain.core.dto;

import java.util.List;

import com.project02server.domain.weather.dto.WeatherInfo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MainServiceResponse {

	WeatherInfo currentWeatherInfo;

	List<WeatherInfo> forecastInfos;

	@Builder
	public MainServiceResponse(WeatherInfo currentWeatherInfo
		, List<WeatherInfo> forecastInfos) {

		this.currentWeatherInfo = currentWeatherInfo;
		this.forecastInfos = forecastInfos;
	}

	public static MainServiceResponse of (WeatherInfo currentWeatherInfo,
		List<WeatherInfo> forecastInfos) {

		return MainServiceResponse.builder()
			.currentWeatherInfo(currentWeatherInfo)
			.forecastInfos(forecastInfos)
			.build();
	}
}
