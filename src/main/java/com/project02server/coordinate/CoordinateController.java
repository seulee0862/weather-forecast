package com.project02server.coordinate;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project02server.coordinate.properties.CoordinateProperties;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/coordinate")
public class CoordinateController {

	private final CoordinateProperties properties;

	@GetMapping("/all")
	public List<CoordinateProperties.CoordinateProperty> allCoordinateInfo() {
		return properties.getCoordinates();
	}
}
