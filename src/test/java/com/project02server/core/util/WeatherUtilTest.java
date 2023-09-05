package com.project02server.core.util;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

<<<<<<< HEAD
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
=======
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
>>>>>>> feature
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

<<<<<<< HEAD
import com.project02server.config.WeatherUtilForTest;
import com.project02server.domain.weather.dto.restTemplate.forcastResponse.ForecastResponse;
import com.project02server.domain.weather.dto.restTemplate.weatherResponse.CurrentWeatherResponse;

=======
import com.project02server.domain.weather.dto.restTemplate.forcastResponse.ForecastResponse;
import com.project02server.domain.weather.dto.restTemplate.weatherResponse.CurrentWeatherResponse;
import com.project02server.domain.weather.util.WeatherUtil;
>>>>>>> feature

@ExtendWith(MockitoExtension.class)
public class WeatherUtilTest {

<<<<<<< HEAD
	@Mock
	private RestTemplate restTemplate;

	private WeatherUtilForTest weatherUtil;

	@BeforeEach
	public void setup() {
		weatherUtil = new WeatherUtilForTest(restTemplate);
	}
=======

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private WeatherUtil weatherUtil;
>>>>>>> feature

	@Test
	@DisplayName("기상예측정보 반환 성공")
	public void testGetForeCastByCoordinate() {

		//given
		ForecastResponse mockResponse = new ForecastResponse();

		//when
		when(restTemplate.getForEntity(anyString(), any()))
			.thenReturn(ResponseEntity.ok(mockResponse));

<<<<<<< HEAD
		ForecastResponse forecastResponse = weatherUtil.getForeCastByCoordinate(
			anyDouble(), anyDouble()
		);
=======
		ForecastResponse forecastResponse = weatherUtil.getForeCastByCoordinate(anyDouble(), anyDouble());
>>>>>>> feature

		//then
		assertNotNull(forecastResponse);

	}

	@Test
	@DisplayName("현재날씨정보 반환 성공")
	public void testGetCurrentWeather() {

		// given
		CurrentWeatherResponse mockResponse = new CurrentWeatherResponse();

		// when
		when(restTemplate.getForEntity(anyString(), any()))
			.thenReturn(ResponseEntity.ok(mockResponse));

<<<<<<< HEAD
		CurrentWeatherResponse response = weatherUtil.getCurrentWeather(
			anyDouble(), anyDouble()
		);
=======
		CurrentWeatherResponse response = weatherUtil.getCurrentWeather(anyDouble(), anyDouble());
>>>>>>> feature

		//then
		assertNotNull(response);
	}
}