package main;

import javax.swing.JFrame;

public class Game {
	
	public static void main(String[] args) {
		
		// Creates the window that everything is displayed in
		JFrame window = new JFrame("Go Home!");
		window.setContentPane(new GameWindow());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
		
	}

}
