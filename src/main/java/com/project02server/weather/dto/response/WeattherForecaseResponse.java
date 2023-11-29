package com.project02server.weather.dto.response;

import java.util.List;

import com.project02server.weather.dto.WeatherDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter @Setter
public class WeattherForecaseResponse {

	List<WeatherDto> forecastInfos;

	@Builder
	public WeattherForecaseResponse(List<WeatherDto> forecastInfos) {
		this.forecastInfos = forecastInfos;
	}

	public static WeattherForecaseResponse from (List<WeatherDto> forecastInfos) {
		return WeattherForecaseResponse.builder()
			.forecastInfos(forecastInfos)
			.build();
	}
}
