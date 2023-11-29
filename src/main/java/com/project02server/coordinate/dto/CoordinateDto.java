package com.project02server.coordinate.dto;

import java.util.List;

import com.project02server.coordinate.properties.CoordinateProperties;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CoordinateDto {

	private String regionName;
	private double lat;
	private double lon;

	public static List<CoordinateDto> from(List<CoordinateProperties.CoordinateProperty> coordinates) {
		return coordinates
			.stream()
			.map(CoordinateDto::new)
			.toList();
	}

	@Builder
	private CoordinateDto(CoordinateProperties.CoordinateProperty coordinate) {
		this.regionName = coordinate.getRegionName();
		this.lat = coordinate.getLat();
		this.lon = coordinate.getLon();
	}
}
