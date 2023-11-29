package com.project02server.coordinate.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project02server.coordinate.domain.Coordinate;

public interface CoordinateRepository extends JpaRepository<Coordinate, Long> {

	Optional<Coordinate> findByLatAndLon(double lat, double lon);

	@Query("select "
		+ "case when count (c) > 0 then true "
		+ "else false "
		+ "end "
		+ "from Coordinate c")
	boolean existAny();

	boolean existsByRegionName(String regionName);
}
