package level;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import main.GameWindow;
import object.Player;
import tilemap.Background;
import tilemap.Tilemap;

@SuppressWarnings("unused")
public class FirstLevel extends Level {
	
	private Tilemap tilemap;
	private Color clearScreen;
	private Background background;
	private Player player;
	
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
		tilemap.setTransitionSpeed(1);
		
		player = new Player(tilemap);
		player.setPos(100, 100);
	}

	public void update() {
		player.update();
		tilemap.setPos(GameWindow.WIDTH / 2 - player.getx(), GameWindow.HEIGHT / 2 - player.gety());

	}

	public void draw(Graphics2D graphics) {
		//clearScreen = new Color(255, 255, 255);
		//graphics.setColor(clearScreen);
		//graphics.fillRect(0, 0, GameWindow.WIDTH, GameWindow.HEIGHT);
		
		background.draw(graphics);
		
		tilemap.draw(graphics);
		
		player.draw(graphics);

	}

	public void keyPressed(int key) {
		if(key == KeyEvent.VK_A) player.setLeft(true);
		if(key == KeyEvent.VK_D) player.setRight(true);
		if(key == KeyEvent.VK_W) player.setUp(true);
		if(key == KeyEvent.VK_S) player.setDown(true);
		if(key == KeyEvent.VK_SPACE) player.setJumping(true);
	}

	public void keyReleased(int key) {
		if(key == KeyEvent.VK_A) player.setLeft(false);
		if(key == KeyEvent.VK_D) player.setRight(false);
		if(key == KeyEvent.VK_W) player.setUp(false);
		if(key == KeyEvent.VK_S) player.setDown(false);
		if(key == KeyEvent.VK_SPACE) player.setJumping(false);

	}

}
