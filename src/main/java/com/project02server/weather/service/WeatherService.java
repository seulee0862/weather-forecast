package com.project02server.weather.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project02server.weather.domain.Weather;
import com.project02server.weather.dto.WeatherDto;
import com.project02server.weather.dto.response.WeattherForecaseResponse;
import com.project02server.weather.repository.WeatherJdbcRepository;
import com.project02server.weather.repository.WeatherRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class WeatherService {

	private final WeatherRepository weatherRepository;
	private final WeatherJdbcRepository weatherJdbcRepository;
	private final WeatherOpenAPI weatherOpenAPI;

	public WeattherForecaseResponse getWeatherForecast(Double lat, Double lon) {
		//현재시간 기준이후 48시간동안의 데이터만 리스트로 변환한다.
		List<WeatherDto> weatherDtos = weatherOpenAPI.retrieveForeCast(lat, lon)
			.getList()
			.stream()
			.map(WeatherDto::from)
			.filter(
				weatherDto -> weatherDto
					.getDateTime()
					.isAfter(LocalDateTime.now(ZoneId.of("Asia/Seoul")))
			)
			.limit(16)
			.toList();

		return WeattherForecaseResponse.from(weatherDtos);
	}

	public List<Weather> findWeatherWithin24Hours(String regionName, LocalDateTime startDateTime) {
		LocalDateTime endDateTime = startDateTime.plusHours(24);
		return weatherRepository.findWeatherWithin24Hours(regionName, startDateTime, endDateTime);
	}

	public Optional<Weather> getFirstByOrderByDateTimeDesc() {
		return weatherRepository.findFirstByOrderByDateTimeDesc();
	}

	public Integer saveAll(List<Weather> weathers) {
		return weatherJdbcRepository.saveAll(weathers);
	}

}
