package object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import tilemap.Tilemap;

public class Squirrel extends Enemy {
	
	private BufferedImage[] sprites;

	public Squirrel(Tilemap tilemap) {
		super(tilemap);
		
		speed = 0.3;
		topSpeed = 0.9;
		fallSpeed = 0.9;
		topFallSpeed = 10.0;
		width = 32;
		height = 25;
		hitboxWidth = 22;
		hitboxHeight = 15;
		lives = maxLives = 1;
		damage = 1;
		
		try {
			BufferedImage enemySprite = ImageIO.read(getClass().getResourceAsStream("/sprites/enemies/squirrel.gif"));
			sprites = new BufferedImage[3];
			for(int i = 0; i < sprites.length; i++) {
				sprites[i] = enemySprite.getSubimage(i * width, 0, width, height);
				
			}
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
		
		animation = new Animation();
		animation.setFrames(sprites);
		animation.setDelayTime(150);
		
		right = true;
		flip = true;
		
		
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
		
		if(injured) {
			long passed = (System.nanoTime() - injureTimer) / 1000000;
			if(passed > 400) {
				injured = false;
			}
		}
		
		// This checks if the enemy is hitting a wall; if so, change direction
		if(right && x2 == 0) {
			right = false;
			left = true;
			flip = false;
		}
		else if(left && x2 == 0) {
			left = false;
			right = true;
			flip = true;
		}
		
		animation.update();
		
	}
	
	public void draw(Graphics2D graphics) {
		//if(notVisible()) return;
		
		setMapPos();
		super.draw(graphics);
		
	}

}
