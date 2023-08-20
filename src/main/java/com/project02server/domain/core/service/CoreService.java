package com.project02server.domain.core.service;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.project02server.common.code.ErrorCode;
import com.project02server.common.exception.customException.BusinessException;
import com.project02server.domain.coordinate.entity.Coordinate;
import com.project02server.domain.coordinate.repository.CoordinateRepository;
import com.project02server.domain.weather.dto.WeatherInfo;
import com.project02server.domain.core.dto.response.CoreServiceResponse;
import com.project02server.domain.weather.dto.restTemplate.OpenWeather;
import com.project02server.domain.weather.dto.restTemplate.forcastResponse.Forecast;
import com.project02server.domain.weather.util.OpenWeatherUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class CoreService {

	private final OpenWeatherUtil openWeatherUtil;
	private final CoordinateRepository coordinateRepository;

	@Cacheable(value = "locationInfo", key = "#provinceName")
	public CoreServiceResponse getWeatherInfos(String provinceName) {

		Coordinate coordinate = coordinateRepository.findByProvinceName(provinceName)
			.orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_ENTITY));

		double lat = coordinate.getLat();
		double lon = coordinate.getLon();

		WeatherInfo currentWeatherInfo = WeatherInfo
			.from(openWeatherUtil.getCurrentWeather(lat, lon));

		List<WeatherInfo> forecastWeatherInfos = getWeatherInfos(
			openWeatherUtil.getForeCastByCoordinate(lat, lon).getList()
		);

		return CoreServiceResponse.of(currentWeatherInfo, forecastWeatherInfos);
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
