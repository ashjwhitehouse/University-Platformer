package main;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import state.StateManager;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, KeyListener {
	
	// Image settings
	private BufferedImage image;
	private Graphics2D g;
	
	// Dimensions of the game's window in pixels
	// 640*480 were common dimensions used by early games and as my platformer seeks to emulate them, they are the
	// dimensions I will be using
	public static final int winWidth = 320;
	public static final int winHeight = 240;
	public static final int winScale = 2;
	
	// Gets the StateManager package
	// This discerns the user's 'stage' where a stage is where they currently are in the game (i.e: menu, level1, 
	// level2, etc etc)
	private StateManager mgr;
	
	// Game window constructor
	public GamePanel() {
		super();
		setPreferredSize(new Dimension(winWidth * winScale, winHeight * winScale));
		setFocusable(true);
		requestFocus();
	}
	
	// Creating my game's thread
	private Thread thread;
	private boolean running;
	private int FPS = 30;
	private long objective = 500 / FPS;
	
	// Function that checks if the game has started running and if it has, create the above thread
	public void addNotify() {
		super.addNotify();
		if(thread == null) {
			thread = new Thread(this);
			addKeyListener(this);
			thread.start();
		}
	}
	
	// Initialise everything
	private void init() {
		
		image = new BufferedImage(winWidth, winHeight, BufferedImage.TYPE_INT_RGB);
		
		g = (Graphics2D) image.getGraphics();
		
		running = true;
		
		mgr = new StateManager();
		
	}
	
	// This function handles everything that happens whilst the game is running
	public void run() {
		
		init();
		
		long start;
		long passed;
		long pause;
		
		// Game loop
		while(running) {
			
			start = System.nanoTime();
			
			update();
			draw();
			screenDraw();
			
			passed = System.nanoTime() - start;
			
			// The division is required since 'pause' is in milliseconds and 'passed' is in nanoseconds
			pause = objective - passed / 1000000;
			if(pause < 0) pause = 5;
			
			try {
				Thread.sleep(pause);
			}
			catch(Exception exception) {
				exception.printStackTrace();
			}
			
		}
		
	}
	
	private void update() {
		
		mgr.update();
	
	}
	
	private void draw() {
		
		mgr.draw(g);
		
	}
	
	private void screenDraw() {
		
		Graphics canvas = getGraphics();
		canvas.drawImage(image, 0, 0, winWidth * winScale, winHeight * winScale, null);
		canvas.dispose();
		
	}
	
	// KeyListener methods https://docs.oracle.com/javase/8/docs/api/java/awt/event/KeyListener.html
	
	public void keyPressed(KeyEvent key) {
		
		mgr.keyPressed(key.getKeyCode());
		
	}
	
	public void keyReleased(KeyEvent key) {
		
		mgr.keyReleased(key.getKeyCode());
		
	}
	
	public void keyTyped(KeyEvent key) {
		
	}
	
}
