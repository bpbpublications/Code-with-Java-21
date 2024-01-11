package com.codewithjava21.weatherapp;

public class LatestWeather {

	private String id;
	private Geometry geometry;
	private Properties properties;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public Geometry getGeometry() {
		return geometry;
	}
	
	public void setGeometry(Geometry geometryCoordinates) {
		this.geometry = geometryCoordinates;
	}
	
	public Properties getProperties() {
		return properties;
	}
	
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
}
