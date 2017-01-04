package tilemap;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import main.GameWindow;

public class Background {
	
	private BufferedImage image;
	private double x, y, x2, y2, pa;
	
	public Background(String string, double pams) {
		try {
			image = ImageIO.read(getClass().getResourceAsStream(string));
			pa = pams;
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
		
	}
	
	public void setPosition(double x, double y) {
		this.x = (x * pa) % GameWindow.WIDTH;
		this.y = (y * pa) % GameWindow.HEIGHT;
		
	}
	
	public void setVector(double x2, double y2) {
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public void update() {
		x += x2;
		y += y2;
	}
	
	public void draw(Graphics2D graphics) {
		graphics.drawImage(image, (int)x, (int)y, null);
		if(x < 0) {
			graphics.drawImage(image, (int)x + GameWindow.WIDTH, (int)y, null);
		}
		if(x > 0) {
			graphics.drawImage(image, (int)x - GameWindow.WIDTH, (int)y, null);
		}
	}

}
