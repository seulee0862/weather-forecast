package com.project02server.domain.weather.dto.restTemplate;

import java.time.LocalDateTime;

public interface OpenWeather {

	Double getTemperature();

	LocalDateTime getDateTimeInKorea();
}
