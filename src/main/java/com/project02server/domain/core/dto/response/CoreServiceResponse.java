package com.project02server.domain.core.dto.response;

import java.util.List;

import com.project02server.domain.weather.dto.WeatherInfo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class CoreServiceResponse {

	WeatherInfo currentWeatherInfo;

	List<WeatherInfo> forecastInfos;

	@Builder
	public CoreServiceResponse(WeatherInfo currentWeatherInfo
		, List<WeatherInfo> forecastInfos) {

		this.currentWeatherInfo = currentWeatherInfo;
		this.forecastInfos = forecastInfos;
	}

	public static CoreServiceResponse of (WeatherInfo currentWeatherInfo,
		List<WeatherInfo> forecastInfos) {

		return CoreServiceResponse.builder()
			.currentWeatherInfo(currentWeatherInfo)
			.forecastInfos(forecastInfos)
			.build();
	}
}
