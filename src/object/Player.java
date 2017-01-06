package object;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import tilemap.Tilemap;

import java.util.ArrayList;

public class Player extends Object {

	// Player's life
	private int lives, maxLives;
	@SuppressWarnings("unused")
	private boolean injured, dead;
	private long injureTimer;
	
	// Player's attack
	private boolean attacking;
	@SuppressWarnings("unused")
	private int damage, reach;

	// Player animations
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = {
			1, 2, 1, 2
	};
	
	private static final int IDLE = 0;
	private static final int WALKING = 1;
	private static final int FALLING = 2;
	private static final int JUMPING = 3;
	private static final int ATTACKING = 4;
	
	public Player(Tilemap tm) {
		super(tm);
		
		flip = true;
		width = 52;
		height = 34;
		hitboxWidth = 42;
		hitboxHeight = 24;
		speed = 0.3;
		topSpeed = 1.6;
		stopSpeed = 0.4;
		fallSpeed = 0.15;
		topFallSpeed = 4.0;
		jumpStartSpeed = -4.8;
		jumpStopSpeed = 0.3;
		lives = maxLives = 3;
		damage = 10;
		reach = 40;
		
		try {
			BufferedImage playerSprite = ImageIO.read(getClass().getResourceAsStream("/sprites/players/playertemplate.gif"));
			
			sprites = new ArrayList<BufferedImage[]>();
			for(int i = 0; i < 4; i++) {
				BufferedImage[] spriteImage = new BufferedImage[numFrames[i]];
				for(int j = 0; j < numFrames[i]; j++) {
					
					if(i != 4) {
						spriteImage[j] = playerSprite.getSubimage(j * width, i * height, width, height);
					}
					else {
						spriteImage[j] = playerSprite.getSubimage(j * width, i * 45, width, height);
					}
				}
				
				sprites.add(spriteImage);
				
			}
			
			
			
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
		
		animation = new Animation();
		currentAction = IDLE;
		animation.setFrames(sprites.get(IDLE));
		animation.setDelayTime(-1);
		
				
	}
	
	public int getLives() {
		return lives;
	}
	
	public int getMaxLives() {
		return maxLives;
	}
	
	public void setAttacking() {
		attacking = true;
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
		else {
			if(x2 > 0) {
				x2 -= stopSpeed;
				if(x2 < 0) {
					x2 = 0;
				}
			}
			else if(x2 < 0) {
				x2 += stopSpeed;
				if(x2 > 0) {
					x2 = 0;
				}
			}
		}
		
		if(jumping && !falling) {
			y2 = jumpStartSpeed;
			falling = true;
		}
		
		if(falling) {
			y2 += fallSpeed;
			
			if(y2 > 0) jumping = false;
			if(y2 < 0 && !jumping) y2 += jumpStopSpeed;
			if(y2 > topFallSpeed) y2 = topFallSpeed;
		}
		
	}
	
	public void update() {
		
		// 52 * 34
		// 52 * 45
		
		// Update the position
		getNextPos();
		checkMapCollision();
		setPos(tempx, tempy);
		
		// Set player's animation
		if(attacking) {
			if(currentAction != ATTACKING) {
				currentAction = ATTACKING;
				animation.setFrames(sprites.get(ATTACKING));
				animation.setDelayTime(50);
				width = 52;
				height = 45;
				
			}
		}
		else if(y2 > 0) {
			if(currentAction != FALLING) {
				currentAction = FALLING;
				animation.setFrames(sprites.get(FALLING));
				animation.setDelayTime(-1);
				width = 52;
				height = 34;
			}
		}
		else if(y2 < 0) {
			if(currentAction != JUMPING) {
				currentAction = JUMPING;
				animation.setFrames(sprites.get(JUMPING));
				animation.setDelayTime(50);
				width = 52;
				height = 34;
			}
		}
		else if(left || right) {
			if(currentAction != WALKING) {
				currentAction = WALKING;
				animation.setFrames(sprites.get(WALKING));
				animation.setDelayTime(80);
				width = 52;
				height = 34;
			}
		}
		else {
			if(currentAction != IDLE) {
				currentAction = IDLE;
				animation.setFrames(sprites.get(IDLE));
				animation.setDelayTime(-1);
				width = 52;
				height = 34;
			}
		}
		
		animation.update();
		
		// Set player direction
		if(currentAction != ATTACKING) { // Fixes the player's direction when attacking
			if(right) flip = true;
			if(left) flip = false;
		}
		
	}
	
	public void draw(Graphics2D graphics) {
		setMapPos();
		
		// Draw the player sprite
		if(injured) {
			long passed = (System.nanoTime() - injureTimer / 1000000);
			if(passed / 100 % 2 == 0) {
				return;
			}
			
		}
		
		super.draw(graphics);
	}

}
