package level;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import object.Player;
import tilemap.Background;

public class Options extends Level {
	
	private Background background;
	
	private int currentChoice = 0;
	private String[] menuChoices = {"Spot", "Patch"};
	
	private Font titleFont;
	private Font font;
	private Color choiceColour;
	private Color selectColour;

	public Options(LevelController ctrl) {
		this.ctrl = ctrl;
		
		try {
			background = new Background("/backgrounds/splashscreen.gif", 1);
			background.setVector(-0.1, 0);
			choiceColour = new Color(243, 250, 47);
			selectColour = new Color(255, 0, 0);
			titleFont = new Font("Century Gothic", Font.BOLD, 34);
			font = new Font("Century Gothic", Font.PLAIN, 20);
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
	}

	public void init() {
		// TODO Auto-generated method stub

	}

	public void update() {
		background.update();

	}

	public void draw(Graphics2D graphics) {
		background.draw(graphics);
		
		graphics.setColor(choiceColour);
		graphics.setFont(titleFont);
		graphics.drawString("Choose Character", 5, 70);
		
		graphics.setFont(font);
		for(int i = 0; i < menuChoices.length; i++) {
			if(i == currentChoice) {
				graphics.setColor(selectColour);
			}
			else {
				graphics.setColor(choiceColour);
			}
			graphics.drawString(menuChoices[i], 135, 120 + i * 20);
		}

	}

	private void select() {
		if(currentChoice == 0) {
			// "Spot"
			Player.setPlayerCharacter(1);
			ctrl.setLevel(LevelController.SPLASHSCREEN);
		}
		if(currentChoice == 1) {
			// "Patch"
			Player.setPlayerCharacter(2);
			ctrl.setLevel(LevelController.SPLASHSCREEN);
		}
	}

	public void keyPressed(int key) {
		if(key == KeyEvent.VK_ENTER) {
			select();
		}
		if(key == KeyEvent.VK_UP) {
			currentChoice--;
			if(currentChoice == -1) {
				currentChoice = 0;
			}
		}
		if(key == KeyEvent.VK_DOWN) {
			currentChoice++;
			if(currentChoice == 2) {
				currentChoice = 1;
			}
		}
		if(key == KeyEvent.VK_ESCAPE) {
			ctrl.setLevel(LevelController.SPLASHSCREEN);
		}
	}

	public void keyReleased(int key) {
		// TODO Auto-generated method stub

	}

}
