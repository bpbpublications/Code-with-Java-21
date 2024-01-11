package chapter9;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.JPanel;

public class MyPanel extends JPanel {

	private static final long serialVersionUID = 5433149762760327082L;

	private BufferedImage logo;
	
	public MyPanel() {
		this.setPreferredSize(new Dimension(800, 600));
		this.setBackground(Color.black);
		this.setFocusable(true);
		
		logo = loadImage("data/bpb.png", 100, 100);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		// cyan line
		g2.setColor(Color.CYAN);
		g2.drawLine(100, 100, 700, 500);
		
		// magenta line
		g2.setColor(Color.MAGENTA);
		g2.drawLine(100, 200, 700, 600);
		
		// green square
		g2.setColor(Color.GREEN);
		// g2.drawRect(600, 100, 100, 100);
		g2.fillRect(600, 100, 100, 100);
		
		// blue square with round corners
		g2.setColor(Color.BLUE);
		g2.fillRoundRect(400, 100, 100, 100, 50, 50);
		
		// purple circle
		g2.setColor(new Color(128,0,192));
		// g2.drawOval(100, 400, 100, 100);
		g2.fillOval(100,  400,  100, 100);
		
		// image w/ BPB logo
		g2.drawImage(logo, 250, 400, null);
	}
	
	private BufferedImage loadImage(String imagePath, int width, int height) {
		BufferedImage scaledImage = null;
		
		try {
			// load image
			File imgFile = new File(imagePath);
			BufferedImage image = ImageIO.read(imgFile);
			// scale image
			scaledImage = new BufferedImage(width, height, image.getType());
			Graphics2D g2 = scaledImage.createGraphics();
			g2.drawImage(image, 0, 0, width, height, null);
			g2.dispose();
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return scaledImage;
	}
}
