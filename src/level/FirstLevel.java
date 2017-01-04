package level;

import java.awt.Color;
import java.awt.Graphics2D;
import main.GameWindow;
import tilemap.Background;
import tilemap.Tilemap;

@SuppressWarnings("unused")
public class FirstLevel extends Level {
	
	private Tilemap tilemap;
	private Color clearScreen;
	private Background background;
	
	public FirstLevel(LevelController ctrl) {
		this.ctrl = ctrl;
		init();
	}

	public void init() {
		background = new Background("/backgrounds/world1.gif", 0.1);
		tilemap = new Tilemap(30);
		tilemap.prepareTiles("/tilesets/tileset1.gif");
		tilemap.prepareMap("/maps/level1.map");
		tilemap.setPos(0, 0);
	}

	public void update() {
		// TODO Auto-generated method stub

	}

	public void draw(Graphics2D graphics) {
		//clearScreen = new Color(255, 255, 255);
		//graphics.setColor(clearScreen);
		//graphics.fillRect(0, 0, GameWindow.WIDTH, GameWindow.HEIGHT);
		
		background.draw(graphics);
		
		tilemap.draw(graphics);

	}

	public void keyPressed(int key) {
		// TODO Auto-generated method stub

	}

	public void keyReleased(int key) {
		// TODO Auto-generated method stub

	}

}
