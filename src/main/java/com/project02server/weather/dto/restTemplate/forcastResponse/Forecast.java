package com.project02server.weather.dto.restTemplate.forcastResponse;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import com.project02server.weather.dto.restTemplate.OpenWeather;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
public class Forecast implements OpenWeather {

	private long dt;
	private MainInfo main;
	private List<Weather> weather;
	private Clouds clouds;
	private Wind wind;
	private int visibility;
	private double pop;
	private Rain rain;
	private Sys sys;
	private String dt_txt;

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