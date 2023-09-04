package com.project02server.config;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.project02server.common.exception.customException.BusinessException;
import com.project02server.domain.weather.dto.restTemplate.forcastResponse.ForecastResponse;
import com.project02server.domain.weather.dto.restTemplate.weatherResponse.CurrentWeatherResponse;

public class WeatherUtilForTest {

	private String API_KEY = "MockKey";
	private String BASE_URL = "https://api.openweathermap.org/data/2.5/";
	private String FORECAST_URL = "forecast";
	private String CURRENT_WEATHER_URL = "weather";

	private final RestTemplate restTemplate;

	public WeatherUtilForTest(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	/**
	 * <p>
	 *     3시간 단위로 5일간의 일기예보 요청
	 *     2일동안 캐싱
	 * </p>
	 * @param lat
	 * @param lon
	 * @return ForecastResponse.class
	 */
	@Cacheable(value = "locationInfo", key = "T(String).format('%s-%s', #lat, #lon)")
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

	/**
	 * <p>
	 *     현재날씨 정보 요청
	 * </p>
	 *
	 * @param lat
	 * @param lon
	 * @return CurrentWeatherResponse.class
	 */
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
