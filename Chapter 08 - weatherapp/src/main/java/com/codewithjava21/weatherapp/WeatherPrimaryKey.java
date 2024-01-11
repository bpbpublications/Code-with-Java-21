package com.codewithjava21.weatherapp;

import java.time.Instant;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class WeatherPrimaryKey {

    @PrimaryKeyColumn(name = "station_id",
    		          ordinal = 0,
    		          type = PrimaryKeyType.PARTITIONED)
    private String stationId;

    @PrimaryKeyColumn(name = "month_bucket",
    		          ordinal = 1,
    		          type = PrimaryKeyType.PARTITIONED)
    private int monthBucket;

    @PrimaryKeyColumn(name = "reading_timestamp",
    		          ordinal = 2,
    		          type = PrimaryKeyType.CLUSTERED)
    private Instant timestamp;

    public WeatherPrimaryKey(String stationId, int monthBucket, Instant timestamp) {
    	this.stationId = stationId;
    	this.monthBucket = monthBucket;
    	this.timestamp = timestamp;
    }
    
	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public int getMonthBucket() {
		return monthBucket;
	}

	public void setMonthBucket(int monthBucket) {
		this.monthBucket = monthBucket;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}
}
