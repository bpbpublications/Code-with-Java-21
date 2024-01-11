package chapter9;

import java.awt.Color;

public class Planet {

	private int coordX;
	private int coordY;
	private int degree;
	private int diameter;
	private int distance;
	private int speed;
	
	private Color color;
	
	public Planet(Color color, int diameter, int orbit, int speed) {
		
		this.color = color;
		this.diameter = diameter;
		this.speed = speed;
		this.distance = orbit * 100;
		this.degree = 0;		
	}
	
	private void computeNewXY() {

		double radians = degree * Math.PI / 180;
		coordX = (int) (distance * Math.cos(radians));
		coordY = (int) (distance * Math.sin(radians));
	}

	private int getRadius() {
		return diameter / 2;
	}

	public void update() {
		degree -= speed;
		
		if (degree < 0) {
			degree += 360;
		}
		
		computeNewXY();
	}

	public int getCoordX() {
		return coordX - getRadius();
	}

	public int getCoordY() {
		return coordY - getRadius();
	}

	public int getDegree() {
		return degree;
	}

	public int getDiameter() {
		return diameter;
	}
	
	public int getDistance() {
		return distance;
	}

	public int getSpeed() {
		return speed;
	}

	public Color getColor() {
		return color;
	}

}
