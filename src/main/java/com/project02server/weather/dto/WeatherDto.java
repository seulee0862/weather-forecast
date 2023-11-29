package com.project02server.weather.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.project02server.weather.domain.Weather;
import com.project02server.weather.dto.restTemplate.OpenWeather;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter @Setter
public class WeatherDto {

	private double temp;
	private LocalDateTime dateTime;
	private String dateTimeStr;
	private String humidity;
	private String rainInfo;

	@Builder
	public WeatherDto(double temp, LocalDateTime dateTime, int humidity, int pop, int rain) {
		this.temp = temp;
		this.dateTime = dateTime;
		this.dateTimeStr = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH시"));
		this.humidity = "습도 " + humidity + "%";
		this.rainInfo = "강수확률: " + pop + "%, 예상 강수량: " + rain +"mm";
	}

	public static WeatherDto from(Weather weather) {
		return WeatherDto.builder()
			.temp(weather.getTemp())
			.dateTime(weather.getDateTime())
			.humidity(weather.getHumidity())
			.pop(weather.getPop())
			.rain(weather.getRain())
			.build();
	}

	public static WeatherDto from(OpenWeather openWeatherResponse) {
		return WeatherDto.builder()
			.temp(openWeatherResponse.fetchTemperature())
			.dateTime(openWeatherResponse.fetchDateTimeInKorea())
			.humidity(openWeatherResponse.fetchHumid())
			.pop(openWeatherResponse.fetchPop())
			.rain(openWeatherResponse.fetchRain())
			.build();
	}
}
