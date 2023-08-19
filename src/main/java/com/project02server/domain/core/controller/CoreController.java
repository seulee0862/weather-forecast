package com.project02server.domain.core.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project02server.domain.core.dto.MainServiceResponse;
import com.project02server.domain.core.service.CoreService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CoreController {

	private final CoreService coreService;

	@GetMapping("/forecast")
	public MainServiceResponse getCoreService(@RequestParam("province") String provinceName) {

		return coreService.getWeatherInfos(provinceName);
	}

}
