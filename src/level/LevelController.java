package level;

import java.util.ArrayList;

public class LevelController {
	
	private ArrayList<Level> levels;
	private int currentLevel;
	public static final int SPLASHSCREEN = 0;
	public static final int FIRSTLEVEL = 1;
	public static final int SECONDLEVEL = 2;
	public static final int THIRDLEVEL = 3;
	public static final int GAMEOVER = 4;
	public static final int VICTORY = 5;
	public static final int OPTIONS = 6;
	
	public LevelController() {
		currentLevel = SPLASHSCREEN;
		
		levels = new ArrayList<Level>();
		levels.add(new Splash(this));
		levels.add(new FirstLevel(this));
		levels.add(new SecondLevel(this));
		levels.add(new ThirdLevel(this));
		levels.add(new GameOver(this));
		levels.add(new Victory(this));
		levels.add(new Options(this));
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
