package com.project02server.weather.domain;

import java.time.LocalDateTime;

import com.project02server.common.domain.BaseTimeEntity;
import com.project02server.coordinate.domain.Coordinate;
import com.project02server.weather.dto.restTemplate.OpenWeather;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
	name = "weather",
	indexes = {
		@Index(name = "index_datetime", columnList = "dateTime")
	}
)
public class Weather extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private double temp;
	@NotNull
	private LocalDateTime dateTime;
	@NotNull
	private int humidity;
	@NotNull
	private int pop; // 강수확률
	@NotNull
	private int rain;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "coordinate_id")
	private Coordinate coordinate;

	public void addCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	@Builder
	private Weather(double temp, LocalDateTime dateTime, int humidity, int pop, int rain) {
		this.temp = temp;
		this.dateTime = dateTime;
		this.humidity = humidity;
		this.pop = pop;
		this.rain = rain;
	}

	public static Weather from(OpenWeather openWeatherResponse) {
		return Weather.builder()
			.temp(openWeatherResponse.fetchTemperature())
			.dateTime(openWeatherResponse.fetchDateTimeInKorea())
			.humidity(openWeatherResponse.fetchHumid())
			.pop(openWeatherResponse.fetchPop())
			.rain(openWeatherResponse.fetchRain())
			.build();
	}
}
