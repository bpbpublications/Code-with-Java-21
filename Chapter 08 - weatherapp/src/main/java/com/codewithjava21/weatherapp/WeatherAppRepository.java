package com.codewithjava21.weatherapp;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherAppRepository extends CassandraRepository<WeatherEntity,WeatherPrimaryKey> {

	@Query("SELECT * FROM weather_by_station_by_month WHERE station_id=?0 AND month_bucket=?1 LIMIT 1")
	WeatherEntity findByStationIdAndMonthBucket(String stationId, int monthBucket);
}
