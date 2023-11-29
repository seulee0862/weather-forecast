package com.project02server.coordinate.properties;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "env")
public class CoordinateProperties {

	private List<CoordinateProperty> coordinates;

	@Getter
	@Setter
	public static class CoordinateProperty {
		private String regionName;
		private double lat;
		private double lon;
	}
}
