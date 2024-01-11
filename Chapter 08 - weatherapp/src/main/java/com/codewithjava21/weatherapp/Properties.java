package com.codewithjava21.weatherapp;

import java.time.Instant;

public class Properties {
	
	private String station;
	private Instant timestamp;
	private String icon;
	private Measurement temperature;
	private Measurement windDirection;
	private Measurement windSpeed;
	private Measurement windGust;
	private Measurement visibility;
	private Measurement precipitationLastHour;
	private CloudLayer[] cloudLayers;
	
	public String getStation() {
		return station;
	}
	
	public void setStation(String station) {
		this.station = station;
	}
	
	public Instant getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getIcon() {
		return icon;
	}
	
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public Measurement getTemperature() {
		return temperature;
	}
	
	public void setTemperature(Measurement temperature) {
		this.temperature = temperature;
	}
	
	public Measurement getWindDirection() {
		return windDirection;
	}
	
	public void setWindDirection(Measurement windDirection) {
		this.windDirection = windDirection;
	}
	
	public Measurement getWindSpeed() {
		return windSpeed;
	}
	
	public void setWindSpeed(Measurement windSpeed) {
		this.windSpeed = windSpeed;
	}
	
	public Measurement getWindGust() {
		return windGust;
	}
	
	public void setWindGust(Measurement windGust) {
		this.windGust = windGust;
	}
	
	public Measurement getVisibility() {
		return visibility;
	}
	
	public void setVisibility(Measurement visibility) {
		this.visibility = visibility;
	}
	
	public Measurement getPrecipitationLastHour() {
		return precipitationLastHour;
	}
	
	public void setPrecipitationLastHour(Measurement precipitationLastHour) {
		this.precipitationLastHour = precipitationLastHour;
	}
	
	public CloudLayer[] getCloudLayers() {
		return cloudLayers;
	}

	public void setCloudLayers(CloudLayer[] cloudLayers) {
		this.cloudLayers = cloudLayers;
	}
}
