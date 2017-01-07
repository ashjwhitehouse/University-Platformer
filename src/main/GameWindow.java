package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.swing.JPanel;
import level.LevelController;

@SuppressWarnings("serial")
public class GameWindow extends JPanel implements Runnable, KeyListener {
	
	// Window dimensions
	public static final int WIDTH = 320;
	public static final int HEIGHT = 240;
	public static final int SCALE = 2;
	
	// Thread
	private Thread thread;
	private boolean running;
	private int frames = 30;
	private long target = 500 / frames;
	
	// Image
	private BufferedImage image;
	private Graphics2D graphics;
	
	private LevelController ctrl;
	
	public GameWindow() {
		super();
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setFocusable(true);
		requestFocus();
	}
	
	public void addNotify() {
		super.addNotify();
		if(thread == null) {
			thread = new Thread(this);
			addKeyListener(this);
			thread.start();
		}
	}
	
	private void init() {
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		graphics = (Graphics2D) image.getGraphics();
		running = true;
		
		ctrl = new LevelController();
	}
	
	public void run() {
		init();
		
		long start;
		long passed;
		long pause;
		
		while(running) {
			start = System.nanoTime();
			
			update();
			draw();
			display();
			
			passed = System.nanoTime() - start;
			
			// Target in milliseconds, passed in nanoseconds 
			pause = target - passed / 1000000;
			if(pause < 0) pause = 8;
			
			try {
				Thread.sleep(pause);
			}
			catch(Exception exception) {
				exception.printStackTrace();
			}
		}
	}
	
	private void update() {
		ctrl.update();
	}
	
	private void draw() {
		ctrl.draw(graphics);
	}
	
	private void display() {
		Graphics graphics2 = getGraphics();
		graphics2.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		graphics2.dispose();
	}
	
	public void keyTyped(KeyEvent key) {
		
	}

	public void keyPressed(KeyEvent key) {
		ctrl.keyPressed(key.getKeyCode());
	}

	public void keyReleased(KeyEvent key) {
		ctrl.keyReleased(key.getKeyCode());
	}


}
