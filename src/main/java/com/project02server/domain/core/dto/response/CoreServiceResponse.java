package com.project02server.domain.core.dto.response;

import java.util.List;

import com.project02server.domain.weather.dto.WeatherInfo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter @Setter
public class CoreServiceResponse {

	List<WeatherInfo> forecastInfos;

	@Builder
	public CoreServiceResponse(List<WeatherInfo> forecastInfos) {
		this.forecastInfos = forecastInfos;
	}

	public static CoreServiceResponse from (List<WeatherInfo> forecastInfos) {

		return CoreServiceResponse.builder()
			.forecastInfos(forecastInfos)
			.build();
	}
}
