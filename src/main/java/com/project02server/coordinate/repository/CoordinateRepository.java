package com.project02server.coordinate.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project02server.coordinate.domain.Coordinate;

/**
 * <p>
 *     Depricated, 다시 사용할 가능성 존재해서 보류중
 * </p>
 */
public interface CoordinateRepository extends JpaRepository<Coordinate, Long> {

	Optional<Coordinate> findByProvinceName(String provinceName);
}
