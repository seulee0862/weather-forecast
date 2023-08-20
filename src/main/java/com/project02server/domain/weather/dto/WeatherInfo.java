package com.project02server.domain.weather.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.project02server.domain.weather.dto.restTemplate.OpenWeather;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter @Setter
public class WeatherInfo {

	private double temp;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
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
