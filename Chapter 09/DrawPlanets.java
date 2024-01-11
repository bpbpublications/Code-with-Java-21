package chapter9;

import javax.swing.JFrame;

public class DrawPlanets {

	public static void main(String[] args) {

		JFrame frame = new JFrame();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Planet Orbits");

		SolarSystem panel = new SolarSystem();
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);

		panel.start();
	}
}
