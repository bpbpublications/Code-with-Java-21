package javabreakout;

import java.awt.Color;

public class Brick {

	private int brickX;
	private int brickY;
	private int brickMaxX;
	private int brickMaxY;
	
	private Color color;
	private boolean broken;
	
	public Brick(int brickX, int brickY, int width, int height, Color color) {
		
		this.color = color;
		this.brickX = brickX;
		this.brickY = brickY;
		this.brickMaxX = brickX + width;
		this.brickMaxY = brickY + height;
		this.broken = false;
	}

	public int getBrickX() {
		return this.brickX;
	}

	public int getBrickY() {
		return this.brickY;
	}

	public int getBrickMaxX() {
		return this.brickMaxX;
	}

	public int getBrickMaxY() {
		return this.brickMaxY;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isBroken() {
		return this.broken;
	}

	public void setBroken(boolean broken) {
		this.broken = broken;
	}
}
