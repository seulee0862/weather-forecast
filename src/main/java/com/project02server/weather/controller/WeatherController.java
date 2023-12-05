package com.project02server.weather.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project02server.common.response.ApiResponse;
import com.project02server.coordinate.properties.CoordinateProperties;
import com.project02server.weather.dto.response.WeattherForecaseResponse;
import com.project02server.weather.service.WeatherService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class WeatherController {

	private final WeatherService weatherService;
	private final CoordinateProperties properties;

	@GetMapping("/forecast")
	public ApiResponse<WeattherForecaseResponse> getWeatherForecast(Double lat, Double lon) {
		WeattherForecaseResponse weattherForecaseResponse = weatherService.getWeatherForecast(lat, lon);
		return ApiResponse.success(weattherForecaseResponse);
	}
}
