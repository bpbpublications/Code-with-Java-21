package javabreakout;

public class Paddle {
	private int paddleX;
	private int paddleY;
	private int paddleHeight;
	private int paddleWidth;
	private int paddleSpeed;
	
	public Paddle(int paddleX, int paddleY, int paddleWidth,
			int paddleHeight, int paddleSpeed) {
		
		this.paddleX = paddleX;
		this.paddleY = paddleY;
		this.paddleHeight = paddleHeight;
		this.paddleWidth = paddleWidth;
		this.paddleSpeed = paddleSpeed;
	}
	
	public void moveLeft() {
		paddleX -= paddleSpeed;
	}
	
	public void moveRight() {
		paddleX += paddleSpeed;
	}
	
	public int getPaddleX() {
		return this.paddleX;
	}
	
	public int getPaddleY() {
		return this.paddleY;
	}
	
	public int getPaddleHeight() {
		return this.paddleHeight;
	}
	
	public int getPaddleWidth() {
		return this.paddleWidth;
	}
	
	public int getPaddleSpeed() {
		return this.paddleSpeed;
	}
}
