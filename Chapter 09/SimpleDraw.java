package chapter9;

import javax.swing.JFrame;

public class SimpleDraw {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Simple Drawings");

		MyPanel panel = new MyPanel();
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}
}