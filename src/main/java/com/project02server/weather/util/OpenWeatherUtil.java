package com.project02server.weather.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.project02server.weather.dto.restTemplate.forcastResponse.ForecastResponse;
import com.project02server.weather.dto.restTemplate.weatherResponse.CurrentWeatherResponse;

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

	public ForecastResponse getForeCastByCoordinate(String lat, String lon) {

		String requestUrl = UriComponentsBuilder
			.fromHttpUrl(BASE_URL + FORECAST_URL)
				.queryParam("lat", lat)
				.queryParam("lon", lon)
				.queryParam("appid", API_KEY)
				.toUriString();

		return restTemplate
			.getForEntity(requestUrl, ForecastResponse.class)
			.getBody();
	}

	public CurrentWeatherResponse getCurrentWeather(String lat, String lon) {

		String reqeustUrl = UriComponentsBuilder
				.fromHttpUrl(BASE_URL + CURRENT_WEATHER_URL)
				.queryParam("lat", lat)
				.queryParam("lon", lon)
				.queryParam("appid", API_KEY)
				.toUriString();

		return restTemplate
			.getForEntity(reqeustUrl, CurrentWeatherResponse.class)
			.getBody();
	}


}
