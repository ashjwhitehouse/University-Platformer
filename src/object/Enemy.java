package object;

import tilemap.Tilemap;

public class Enemy extends Object {
	
	protected int lives, maxLives, damage;
	protected boolean injured, dead;
	protected long injureTimer;
	
	public Enemy(Tilemap tilemap) {
		super(tilemap);
	}
	
	public void hit(int damage) {
		if(injured || dead) return;
		lives -= damage;
		if(lives < 0) lives = 0;
		if(lives == 0) dead = true;
		injured = true;
		injureTimer = System.nanoTime();
	}
	
	public boolean isDead() {
		return dead;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public void update() {
	}

}
