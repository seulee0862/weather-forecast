package com.project02server.domain.weather.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.project02server.common.exception.customException.BusinessException;
import com.project02server.domain.weather.dto.restTemplate.forcastResponse.ForecastResponse;
import com.project02server.domain.weather.dto.restTemplate.weatherResponse.CurrentWeatherResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class OpenWeatherUtil {

	@Value("${openweather.key}")
	private String API_KEY;

	private final String BASE_URL = "http://api.openweathermap.org";
	private final String FORECAST_URL = "/data/2.5/forecast";
	private final String CURRENT_WEATHER_URL = "/data/2.5/weather";

	private final RestTemplate restTemplate;

	public ForecastResponse getForeCastByCoordinate(Double lat, Double lon) {

		ForecastResponse result = null;

		String requestUrl = UriComponentsBuilder
			.fromHttpUrl(BASE_URL + FORECAST_URL)
				.queryParam("lat", lat)
				.queryParam("lon", lon)
				.queryParam("appid", API_KEY)
				.toUriString();

		try {
			result = restTemplate
				.getForEntity(requestUrl, ForecastResponse.class)
				.getBody();
		}
		catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}

		return result;
	}

	public CurrentWeatherResponse getCurrentWeather(Double lat, Double lon) {

		CurrentWeatherResponse result = null;

		String reqeustUrl = UriComponentsBuilder
				.fromHttpUrl(BASE_URL + CURRENT_WEATHER_URL)
				.queryParam("lat", lat)
				.queryParam("lon", lon)
				.queryParam("appid", API_KEY)
				.toUriString();

		try {
			result = restTemplate
				.getForEntity(reqeustUrl, CurrentWeatherResponse.class)
				.getBody();
		}
		catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}

		return result;
	}


}
