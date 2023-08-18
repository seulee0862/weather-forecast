package com.project02server.weather.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project02server.weather.dto.WeatherInfo;
import com.project02server.weather.dto.WeatherServiceResponse;
import com.project02server.weather.dto.restTemplate.OpenWeather;
import com.project02server.weather.dto.restTemplate.forcastResponse.Forecast;
import com.project02server.weather.dto.restTemplate.weatherResponse.CurrentWeatherResponse;
import com.project02server.weather.util.OpenWeatherUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class WeatherService {

	private final OpenWeatherUtil openWeatherUtil;

	public WeatherServiceResponse getWeatherService(String lat, String lon) {

		CurrentWeatherResponse currentWeather = openWeatherUtil.getCurrentWeather(lat, lon);
		List<Forecast> forecasts = openWeatherUtil.getForeCastByCoordinate(lat, lon).getList();

		WeatherInfo currentWeatherInfo = WeatherInfo.from(currentWeather);
		List<WeatherInfo> forecastWeatherInfos = getWeatherInfos(forecasts);

		return WeatherServiceResponse.of(currentWeatherInfo, forecastWeatherInfos);
	}


	/**
	 * 아래부터는 method 영역
	 */



	/**
	 *
	 * <p>
	 *     Forecast 타입을 OpenWeather로 casting<br/>
	 *     이후 WeatherInfo로 최종 casting
	 * </p>
	 *
	 * @param forecasts
	 * @return 예측 기상정보 목록
	 */
	private static List<WeatherInfo> getWeatherInfos(List<Forecast> forecasts) {
		return forecasts
			.stream()
			.map(item -> (OpenWeather)item)
			.toList()
			.stream()
			.map(WeatherInfo::from)
			.toList();
	}
}
