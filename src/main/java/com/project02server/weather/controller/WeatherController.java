package com.project02server.weather.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project02server.weather.dto.WeatherServiceResponse;
import com.project02server.weather.service.WeatherService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class WeatherController {

	private final WeatherService weatherService;

	@GetMapping("/forecast")
	public WeatherServiceResponse getMainService(String lat, String lon) {

		return weatherService.getWeatherService(lat, lon);
	}

}
