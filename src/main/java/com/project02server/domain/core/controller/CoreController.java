package com.project02server.domain.core.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project02server.domain.core.dto.response.CoreServiceResponse;
import com.project02server.domain.core.service.CoreService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CoreController {

	private final CoreService coreService;

	@GetMapping("/forecast")
	public CoreServiceResponse getCoreService(@RequestParam("province") String provinceName) {

		return coreService.getWeatherInfos(provinceName);
	}

}
