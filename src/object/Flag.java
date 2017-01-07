package object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import tilemap.Tilemap;

public class Flag extends Object {
	
	private BufferedImage image;
	
	public Flag(Tilemap tilemap) {
		super(tilemap);
		
		speed = 0;
		topSpeed = 0;
		fallSpeed = 0.9;
		topFallSpeed = 10.0;
		width = 30;
		height = 40;
		hitboxWidth = 20;
		hitboxHeight = 40;
		flip = true;
		
		try {
			BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/misc/flag.gif"));
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
	}
	
	private void getNextPos() {
		if(left) {
			x2 -= speed;
			if(x2 < -topSpeed) {
				x2 = -topSpeed;
			}
		}
		else if(right) {
			x2 += speed;
			if(x2 > topSpeed) {
				x2 = topSpeed;
			}
		}
		if(falling) {
			y2 += fallSpeed;
		}
		
	}
	
	public void update() {
		getNextPos();
		checkMapCollision();
		setPos(tempx, tempy);
	}
	
	public void draw(Graphics2D graphics) {
		//if(notVisible()) return;
		
		setMapPos();
		super.draw(graphics);
		
	}

}
