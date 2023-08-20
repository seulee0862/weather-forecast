package com.project02server.domain.coordinate.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project02server.domain.coordinate.entity.Coordinate;

public interface CoordinateRepository extends JpaRepository<Coordinate, Long> {

	Optional<Coordinate> findByProvinceName(String provinceName);
}
