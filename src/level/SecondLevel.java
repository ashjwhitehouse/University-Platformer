package level;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import main.GameWindow;
import object.Player;
import object.Squirrel;
import object.Display;
import object.Enemy;
import tilemap.Background;
import tilemap.Tilemap;

@SuppressWarnings("unused")
public class SecondLevel extends Level {
	
	private Tilemap tilemap;
	private Color clearScreen;
	private Background background;
	private Display display;
	private Player player;
	private ArrayList<Enemy> enemies;
	
	public SecondLevel(LevelController ctrl) {
		this.ctrl = ctrl;
		init();
	}

	public void init() {
		background = new Background("/backgrounds/world1.gif", 0.1);
		
		tilemap = new Tilemap(30);
		tilemap.prepareTiles("/tilesets/tileset1.gif");
		tilemap.prepareMap("/maps/level2.map");
		tilemap.setPos(0, 0);
		tilemap.setTransitionSpeed(1);
		
		player = new Player(tilemap);
		player.setPos(226, 560);
		player.setPlayerLevel(2);
		//player.fillLives();
		
		populate();
		
		display = new Display(player);
		
	}
	
	private void populate() {
		enemies = new ArrayList<Enemy>();
		
		Squirrel squirrel;
		Point[] points = new Point[] {
				new Point(580, 530), new Point(103, 383), new Point(910, 345), new Point(1070, 345), new Point(1357, 375)
		};
		for(int i = 0; i < points.length; i++) {
			squirrel = new Squirrel(tilemap);
			squirrel.setPos(points[i].x, points[i].y);
			enemies.add(squirrel);
		}
	}

	public void update() {
		player.update();
		//flag.update();
		tilemap.setPos(GameWindow.WIDTH / 2 - player.getx(), GameWindow.HEIGHT / 2 - player.gety());
		background.setPosition(tilemap.getx(), tilemap.gety());
		
		player.checkAttack(enemies);
		
		if(player.getx() > 1690) {
			ctrl.setLevel(LevelController.VICTORY);
		}
		
		if(player.gety() > 660) {
			player.setDead();
		}
		
		if(player.isDead()) {
			ctrl.setLevel(LevelController.GAMEOVER);
		}
		
		for(int i = 0; i < enemies.size(); i++) {
			enemies.get(i).update();
		}
		
	}

	public void draw(Graphics2D graphics) {
		//clearScreen = new Color(255, 255, 255);
		//graphics.setColor(clearScreen);
		//graphics.fillRect(0, 0, GameWindow.WIDTH, GameWindow.HEIGHT);
		
		background.draw(graphics);
		
		tilemap.draw(graphics);
		
		player.draw(graphics);
		
		//flag.draw(graphics);
		
		for(int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw(graphics);
		}

		display.draw(graphics);

	}

	public void keyPressed(int key) {
		if(key == KeyEvent.VK_LEFT) player.setLeft(true);
		if(key == KeyEvent.VK_RIGHT) player.setRight(true);
		if(key == KeyEvent.VK_UP) player.setJumping(true);
	}

	public void keyReleased(int key) {
		if(key == KeyEvent.VK_LEFT) player.setLeft(false);
		if(key == KeyEvent.VK_RIGHT) player.setRight(false);
		if(key == KeyEvent.VK_UP) player.setJumping(false);

	}

}
