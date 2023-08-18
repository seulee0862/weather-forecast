package com.project02server.weather.dto.restTemplate.weatherResponse;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import com.project02server.weather.dto.restTemplate.OpenWeather;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CurrentWeatherResponse implements OpenWeather {

	private Coord coord;
	private List<Weather> weather;
	private String base;
	private Main main;
	private Integer visibility;
	private Wind wind;
	private Rain rain;
	private Clouds clouds;
	private Long dt;
	private Sys sys;
	private Integer timezone;
	private Integer id;
	private String name;
	private Integer cod;

	@Override
	public Double getTemperature() {

		BigDecimal temp = BigDecimal.valueOf(this.main.getTemp() - 273.15);

		return temp
			.setScale(2, RoundingMode.HALF_UP)
			.doubleValue();
	}

	@Override
	public LocalDateTime getDateTimeInKorea() {

		return LocalDateTime.ofInstant(
			Instant.ofEpochSecond(this.dt),
			ZoneId.of("Asia/Seoul")
		);
	}

}

