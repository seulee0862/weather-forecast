package com.project02server.weather.dto.restTemplate;

import java.time.LocalDateTime;

public interface OpenWeather {

	Double fetchTemperature();
	LocalDateTime fetchDateTimeInKorea();
	int fetchHumid();
	int fetchRain();
	int fetchPop();
}
