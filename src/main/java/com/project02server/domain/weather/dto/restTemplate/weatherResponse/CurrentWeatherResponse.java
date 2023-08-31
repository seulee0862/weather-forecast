package com.project02server.domain.weather.dto.restTemplate.weatherResponse;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import com.project02server.domain.weather.dto.restTemplate.OpenWeather;

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
			: (int)(this.rain.getH1() * 100);
	}

	//TODO fetchPop메서드는 오직 현재날씨에서만 기능구현하지 않은 예외케이스이다.
	// 코드변형있이 생겨 문제가 발생할 경우 OpenWeather interface Refactoring예정
	@Override
	public int fetchPop() {
		return -1;
	}
}

