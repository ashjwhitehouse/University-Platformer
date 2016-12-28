package state;

import java.awt.*;
import java.awt.event.KeyEvent;
import main.GamePanel;
import tilemap.Map;

public class StateLvl1 extends State {
	
	private Map map;
	
	public StateLvl1(StateManager mgr) {
		this.mgr = mgr;
		init();
	}
	
	public void init() {
		
		map = new Map(30);
		map.prepareTileset("/tilesets/tileset.gif");
		map.prepareMap("/maps/map1.map");
		map.setPos(0, 0);
	}

	public void update() {
	}

	public void draw(Graphics2D g) {
		
		// Clears the screen of anything left over
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, GamePanel.winWidth, GamePanel.winHeight);
		
		map.draw(g);
	}

	public void keyPressed(int k) {
	}

	public void keyReleased(int k) {
	}

}
