package com.project02server.coordinate.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project02server.common.code.ErrorCode;
import com.project02server.common.exception.customException.BusinessException;
import com.project02server.coordinate.domain.Coordinate;
import com.project02server.coordinate.dto.CoordinateDto;
import com.project02server.coordinate.properties.CoordinateProperties;
import com.project02server.coordinate.repository.CoordinateRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CoordinateService {

	private final CoordinateProperties coordinateProperties;
	private final CoordinateRepository coordinateRepository;

	public List<CoordinateDto> getCoordinateInfos(){
		return CoordinateDto.from(coordinateProperties.getCoordinates());
	}

	@Transactional
	@PostConstruct
	public void initializingCoordinateValues(){
		List<CoordinateDto> coordinateDtos = getCoordinateInfos();

		if (coordinateRepository.existAny()) {
			return;
		}

		coordinateDtos
			.stream()
			.map(Coordinate::from)
			.forEach(this::save);
	}

	public Coordinate save(Coordinate from) {
		return coordinateRepository.save(from);
	}

	public boolean existByRegionName(String regionName) {
		return coordinateRepository.existsByRegionName(regionName);
	}

	public Coordinate findByLatAndLon(double lat, double lon) {
		return coordinateRepository.findByLatAndLon(lat, lon)
			.orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_ENTITY));
	}
}
