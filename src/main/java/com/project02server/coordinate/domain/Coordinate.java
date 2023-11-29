package com.project02server.coordinate.domain;

import java.util.List;

import com.project02server.common.domain.BaseTimeEntity;
import com.project02server.coordinate.dto.CoordinateDto;
import com.project02server.weather.domain.Weather;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
	name = "coordinate",
	uniqueConstraints = {
		@UniqueConstraint(columnNames = {"lat", "lon"}),
		@UniqueConstraint(columnNames = {"regionName"})
	},
	indexes = {
		@Index(columnList = "regionName"),
		@Index(name = "index_lat_lon", columnList = "lat, lon")
	}
)
public class Coordinate extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String regionName;
	@NotNull
	private double lat;
	@NotNull
	private double lon;

	@OneToMany(mappedBy = "coordinate")
	private List<Weather> coordinates;

	public static Coordinate from(CoordinateDto coordinateDto) {
		return Coordinate.builder()
			.regionName(coordinateDto.getRegionName())
			.lat(coordinateDto.getLat())
			.lon(coordinateDto.getLon())
			.build();
	}

	@Builder
	private Coordinate(String regionName, double lat, double lon) {
		this.regionName = regionName;
		this.lat = lat;
		this.lon = lon;
	}
}
