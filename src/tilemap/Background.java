package tilemap;

import main.GamePanel;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;

public class Background {
	
	private BufferedImage image;
	
	private double x;
	private double y;
	private double xx;
	private double yy;
	
	private double moveScale;
	
	public Background(String s, double ms) {
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(s));
			moveScale = ms;
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
		
	}
	
	public void setPosition(double x, double y) {
		this.x = (x * moveScale) % GamePanel.winWidth;
		this.y = (y * moveScale) % GamePanel.winHeight;
	}
	
	public void setVector(double xx, double yy) {
		this.xx = xx;
		this.yy = yy;
	}
	
	public void update() {
		x += xx;
		y += yy;
	}
	
	public void draw(Graphics2D g) {
		
		g.drawImage(image, (int)x, (int)y, null);
		
		if(x < 0) {
			g.drawImage(image, (int)x + GamePanel.WIDTH, (int)y, null);
		}
		if(x > 0) {
			g.drawImage(image, (int)x - GamePanel.WIDTH, (int)y, null);
		}
	}
	
}