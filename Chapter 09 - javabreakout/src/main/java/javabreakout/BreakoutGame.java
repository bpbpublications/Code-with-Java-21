package javabreakout;

import javax.swing.JFrame;

public class BreakoutGame {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Java Breakout");

		BreakoutPanel panel = new BreakoutPanel();
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);

		panel.start();
	}

}
