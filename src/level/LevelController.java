package level;

import java.util.ArrayList;

public class LevelController {
	
	private ArrayList<Level> levels;
	private int currentLevel;
	public static final int SPLASHSCREEN = 0;
	public static final int FIRSTLEVEL = 1;
	
	public LevelController() {
		currentLevel = SPLASHSCREEN;
		
		levels = new ArrayList<Level>();
		levels.add(new Splash(this));
		levels.add(new FirstLevel(this));
	}
	
	public void setLevel(int level) {
		currentLevel = level;
		levels.get(currentLevel).init();
	}
	
	public void update() {
		levels.get(currentLevel).update();
	}
	
	public void draw(java.awt.Graphics2D graphics) {
		levels.get(currentLevel).draw(graphics);
	}
	
	public void keyPressed(int key) {
		levels.get(currentLevel).keyPressed(key);
	}
	
	public void keyReleased(int key) {
		levels.get(currentLevel).keyReleased(key);
	}

}
