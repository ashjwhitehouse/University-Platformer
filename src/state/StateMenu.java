// http://docs.oracle.com/javase/tutorial/2d/text/fonts.html
// http://stackoverflow.com/questions/5652344/how-can-i-use-a-custom-font-in-java
// http://stackoverflow.com/questions/5902229/custom-fonts-in-java

package state;

import java.awt.*;
import java.awt.event.KeyEvent;
import tilemap.Background;

public class StateMenu extends State {
	
	private Background bg;
	
	private int currentSelection = 0;
	private String[] options = {
			"Start", "Options", "Quit"
	};
	
	private Color titleColour;
	private Color selectColour;
	private Font titleFont;
	private Font font;
	
	public StateMenu(StateManager mgr) {
		
		this.mgr = mgr;
		
		try {
			
			bg = new Background("/backgrounds/menu.gif", 1);
			
			titleColour = new Color(239,255,232);
			selectColour = new Color(0,236,255);
			titleFont = new Font("Lucida Console", Font.PLAIN, 32);
			font = new Font("Lucida Console", Font.PLAIN, 14);
			
			
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
		
	}

	public void init() {
	}

	public void update() {
		bg.update();
	}

	public void draw(Graphics2D g) {
		// Background
		bg.draw(g);
		
		// Title
		g.setColor(titleColour);
		g.setFont(titleFont);
		g.drawString("GO HOME", 105, 70);
		
		//Menu
		g.setFont(font);
		for(int i = 0; i < options.length; i++) {
			if(i == currentSelection) {
				g.setColor(selectColour);
			}
			else {
				g.setColor(titleColour);
			}
			g.drawString(options[i], 145, 120 + i * 20);
			
		}
	}
	
	public void selectChoice() {
		
		// User selects start
		if(currentSelection == 0) {
			mgr.stateSet(StateManager.stateLvl1);
		}
		// User selects help
		if(currentSelection == 1) {
			
		}
		// User quits
		if(currentSelection == 2) {
			System.exit(0);
		}
	}

	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER) {
			selectChoice();
		}
		if(k == KeyEvent.VK_UP || k == KeyEvent.VK_W || k == KeyEvent.VK_8) {
			currentSelection--;
			if(currentSelection == -1) {
				currentSelection = 2;
			}
		}
		if(k == KeyEvent.VK_DOWN || k == KeyEvent.VK_S || k == KeyEvent.VK_2) {
			currentSelection++;
			if(currentSelection == 3) {
				currentSelection = 0;
			}
		}
	}

	public void keyReleased(int k) {
	}

}
