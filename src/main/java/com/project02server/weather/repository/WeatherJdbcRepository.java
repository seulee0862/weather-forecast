package com.project02server.weather.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project02server.weather.domain.Weather;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Repository
public class WeatherJdbcRepository {

	private final JdbcTemplate jdbcTemplate;

	public Integer saveAll(List<Weather> weathers) {
		String sql =
			"INSERT INTO weather"
				+ "(humidity, pop, rain, temp, date_time, coordinate_id, create_date) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";

		int[] results = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Weather weather = weathers.get(i);
				ps.setInt(1, weather.getHumidity());
				ps.setInt(2, weather.getPop());
				ps.setInt(3, weather.getRain());
				ps.setDouble(4, weather.getTemp());
				ps.setTimestamp(5, Timestamp.valueOf(weather.getDateTime()));
				ps.setLong(6, weather.getCoordinate().getId());
				ps.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
			}

			@Override
			public int getBatchSize() {
				return weathers.size();
			}
		});

		return results.length;
	}
}
