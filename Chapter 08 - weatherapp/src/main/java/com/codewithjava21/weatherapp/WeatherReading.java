package com.codewithjava21.weatherapp;

import java.time.Instant;
import java.util.Map;

public class WeatherReading {
	private String stationId;
	private int monthBucket;
	private Instant timestamp;
	private String readingIcon;
	private float stationCoordinatesLatitude;
	private float stationCoordinatesLongitude;
	private float temperatureCelsius;
	private int windDirectionDegrees;
	private float windSpeedKMH;
	private float windGustKMH;
	private int visibilityM;
	private float precipitationLastHour;
	private Map<Integer,String> cloudCover;
	
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
