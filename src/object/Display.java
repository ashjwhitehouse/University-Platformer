package object;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Display {
	
	private BufferedImage image;
	private Font font;
	
	private Player player;
	
	public Display(Player plr) {
		player = plr;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/hud/hud.gif"));
			font = new Font("Century Gothic", Font.BOLD, 14);
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
	}
	
	public void draw(Graphics2D graphics) {
		graphics.drawImage(image, 0, 5, null);
		graphics.setFont(font);
		graphics.drawString(player.getLives() + "/" + player.getMaxLives(), 22, 19);
		
	}

}
