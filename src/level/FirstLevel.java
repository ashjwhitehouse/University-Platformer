package level;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GameWindow;
import tilemap.Tilemap;

public class FirstLevel extends Level {
	
	private Tilemap tilemap;
	private Color clearScreen;
	
	public FirstLevel(LevelController ctrl) {
		this.ctrl = ctrl;
		init();
	}

	public void init() {
		tilemap = new Tilemap(30);
		tilemap.loadTiles("/tilesets/tileset1.gif");
		tilemap.loadMap("/maps/level1.map");
		tilemap.setPosition(0, 0);
	}

	public void update() {
		// TODO Auto-generated method stub

	}

	public void draw(Graphics2D graphics) {
		clearScreen = new Color(255, 255, 255);
		graphics.setColor(clearScreen);
		graphics.fillRect(0, 0, GameWindow.WIDTH, GameWindow.HEIGHT);
		tilemap.draw(graphics);

	}

	public void keyPressed(int key) {
		// TODO Auto-generated method stub

	}

	public void keyReleased(int key) {
		// TODO Auto-generated method stub

	}

}
