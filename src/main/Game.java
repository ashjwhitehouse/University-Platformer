// http://fivedots.coe.psu.ac.th/~ad/jg/
// http://fivedots.coe.psu.ac.th/~ad/jg/ch02/ch2.pdf

package main;

import javax.swing.JFrame;

public class Game {
	
	public static void main(String[] args) {
		
		JFrame window = new JFrame("Platformer Game");
		window.setContentPane(new GamePanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setResizable(false);
	    window.pack();
	    window.setVisible(true);
		
	}

}
