package com.project02server.domain.weather.dto.restTemplate;

import java.time.LocalDateTime;

public interface OpenWeather {

	Double fetchTemperature();

	LocalDateTime fetchDateTimeInKorea();


	int fetchHumid();

	int fetchRain();

	int fetchPop();

}
