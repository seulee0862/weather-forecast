package com.project02server.domain.core.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project02server.common.response.ApiResponse;
import com.project02server.domain.core.dto.response.CoreServiceResponse;
import com.project02server.domain.core.service.CoreService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CoreController {

	private final CoreService coreService;

	@GetMapping("/forecast")
	public ApiResponse<CoreServiceResponse> getCoreService(Double lat, Double lon) {
		CoreServiceResponse weatherInfos = coreService.getWeatherInfos(lat, lon);
		return ApiResponse.success(weatherInfos);
	}

}
