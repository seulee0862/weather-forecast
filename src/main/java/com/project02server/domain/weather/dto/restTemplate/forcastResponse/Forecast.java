package com.project02server.domain.weather.dto.restTemplate.forcastResponse;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import com.project02server.domain.weather.dto.restTemplate.OpenWeather;

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
	public Double fetchTemperature() {

		BigDecimal temp = BigDecimal.valueOf(this.main.getTemp() - 273.15);

		return temp
			.setScale(2, RoundingMode.HALF_UP)
			.doubleValue();
	}

	@Override
	public LocalDateTime fetchDateTimeInKorea() {

		return LocalDateTime.ofInstant(
			Instant.ofEpochSecond(this.dt),
			ZoneId.of("Asia/Seoul")
		);
	}

	@Override
	public int fetchHumid() {
		return this.main.getHumidity();
	}

	@Override
	public int fetchRain() {

		return this.rain == null
			? 0
			: (int)(this.rain.get_3h() * 100);
	}

	@Override
	public int fetchPop() {
		return (int)(this.getPop() * 100);
	}
}