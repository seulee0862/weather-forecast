package com.project02server.domain.weather.dto;

import java.time.LocalDateTime;

import com.project02server.domain.weather.dto.restTemplate.OpenWeather;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class WeatherInfo {

	private double temp;
	private LocalDateTime dateTime;

	@Builder
	public WeatherInfo(double temp, LocalDateTime dateTime) {
		this.temp = temp;
		this.dateTime = dateTime;
	}

	public static WeatherInfo from(OpenWeather openWeatherResponse) {

		return WeatherInfo.builder()
			.temp(openWeatherResponse.getTemperature())
			.dateTime(openWeatherResponse.getDateTimeInKorea())
			.build();
	}
}
