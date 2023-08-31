package com.project02server.domain.core.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project02server.domain.coordinate.repository.CoordinateRepository;
import com.project02server.domain.core.dto.response.CoreServiceResponse;
import com.project02server.domain.weather.dto.WeatherInfo;
import com.project02server.domain.weather.util.WeatherUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class CoreService {

	private final WeatherUtil weatherUtil;

	public CoreServiceResponse getWeatherInfos(Double lat, Double lon) {

		//현재시간 기준이후 48시간동안의 데이터만 리스트로 변환한다.
		List<WeatherInfo> weatherInfos = weatherUtil.getForeCastByCoordinate(lat, lon).getList()
			.stream()
			.map(WeatherInfo::from)
			.filter(
				weatherInfo -> weatherInfo
					.getDateTime()
					.isAfter(LocalDateTime.now(ZoneId.of("Asia/Seoul")))
			)
			.limit(16)
			.toList();

		return CoreServiceResponse.from(weatherInfos);

	}
}
