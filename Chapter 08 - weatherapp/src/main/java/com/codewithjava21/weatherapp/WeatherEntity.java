package com.codewithjava21.weatherapp;

import java.util.Map;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("weather_by_station_by_month")
public class WeatherEntity {

	@PrimaryKey
	private WeatherPrimaryKey primaryKey;
	
	@Column("reading_icon")
	private String readingIcon;

	@Column("station_coordinates_lat")
	private float stationCoordinatesLatitude;

	@Column("station_coordinates_lon")
	private float stationCoordinatesLongitude;
	
	@Column("temperature_c")
	private float temperatureCelsius;

	@Column("wind_direction_deg")
	private int windDirectionDegrees;
	
	@Column("wind_speed_kmh")
	private float windSpeedKMH;
	
	@Column("wind_gust_kmh")
	private float windGustKMH;
	
	@Column("visibility_m")
	private int visibilityM;
	
	@Column("precipitation_last_hour")
	private float precipitationLastHour;
	
	@Column("cloud_cover")
	private Map<Integer,String> cloudCover;
	
	public WeatherPrimaryKey getPrimaryKey() {
		return primaryKey;
	}
	
	public void setPrimaryKey(WeatherPrimaryKey primaryKey) {
		this.primaryKey = primaryKey;
	}
	
	public String getReadingIcon() {
		return readingIcon;
	}
	
	public void setReadingIcon(String readingIcon) {
		this.readingIcon = readingIcon;
	}
	
	public float getStationCoordinatesLatitude() {
		return stationCoordinatesLatitude;
	}
	
	public void setStationCoordinatesLatitude(float stationCoordinatesLatitude) {
		this.stationCoordinatesLatitude = stationCoordinatesLatitude;
	}
	
	public float getStationCoordinatesLongitude() {
		return stationCoordinatesLongitude;
	}
	
	public void setStationCoordinatesLongitude(float stationCoordinatesLongitude) {
		this.stationCoordinatesLongitude = stationCoordinatesLongitude;
	}
	
	public float getTemperatureCelsius() {
		return temperatureCelsius;
	}
	
	public void setTemperatureCelsius(float temperatureCelsius) {
		this.temperatureCelsius = temperatureCelsius;
	}
	
	public int getWindDirectionDegrees() {
		return windDirectionDegrees;
	}
	
	public void setWindDirectionDegrees(int windDirectionDegrees) {
		this.windDirectionDegrees = windDirectionDegrees;
	}
	
	public float getWindSpeedKMH() {
		return windSpeedKMH;
	}
	
	public void setWindSpeedKMH(float windSpeedKMH) {
		this.windSpeedKMH = windSpeedKMH;
	}
	
	public float getWindGustKMH() {
		return windGustKMH;
	}
	
	public void setWindGustKMH(float windGustKMH) {
		this.windGustKMH = windGustKMH;
	}
	
	public int getVisibilityM() {
		return visibilityM;
	}
	
	public void setVisibilityM(int visibilityM) {
		this.visibilityM = visibilityM;
	}
	
	public float getPrecipitationLastHour() {
		return precipitationLastHour;
	}
	
	public void setPrecipitationLastHour(float precipitationLastHour) {
		this.precipitationLastHour = precipitationLastHour;
	}
	
	public Map<Integer, String> getCloudCover() {
		return cloudCover;
	}
	
	public void setCloudCover(Map<Integer, String> cloudCover) {
		this.cloudCover = cloudCover;
	}
}
