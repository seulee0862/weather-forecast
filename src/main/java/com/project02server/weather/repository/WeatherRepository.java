package com.project02server.weather.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project02server.weather.domain.Weather;

public interface WeatherRepository extends JpaRepository<Weather, Long> {

	Optional<Weather> findFirstByOrderByDateTimeDesc();

	@Query("SELECT w FROM Weather w LEFT JOIN FETCH w.coordinate c " +
		"WHERE c.regionName = :regionName " +
		"AND w.dateTime >= :startDateTime AND w.dateTime <= :endDateTime")
	List<Weather> findWeatherWithin24Hours(
		@Param("regionName") String regionName,
		@Param("startDateTime") LocalDateTime startDateTime,
		@Param("endDateTime") LocalDateTime endDateTime
	);


}
