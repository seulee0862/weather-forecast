package com.project02server.weather.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project02server.coordinate.dto.CoordinateDto;
import com.project02server.coordinate.service.CoordinateService;
import com.project02server.weather.domain.Weather;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class WeatherScheduler {

	private final CoordinateService coordinateService;
	private final WeatherOpenAPI weatherOpenAPI;
	private final WeatherService weatherService;

	//메서드 실행시간 평균 5분, 한국시간 기준 월, 목, 일 새벽3시 실행
	// @Scheduled(cron = "0 0 18 ? * TUE,THU,SUN")
	@Scheduled(fixedDelay = 24 * 3600 * 1000)
	@Transactional
	public void saveForecastInfos() {
		List<CoordinateDto> coordinateInfos = coordinateService.getCoordinateInfos();
		LocalDateTime latestSavedTime = getLatestSavedTime();
		List<Weather> weathers = weatherOpenAPI.retrieveAllWeatherDataByCoordinates(coordinateInfos, latestSavedTime);

		weatherService.saveAll(weathers);
	}
	public LocalDateTime getLatestSavedTime() {
		return weatherService.getFirstByOrderByDateTimeDesc()
			.map(Weather::getDateTime)
			.orElse(LocalDateTime.now().atZone(ZoneId.of("Asia/Seoul")).toLocalDateTime());
	}

}
