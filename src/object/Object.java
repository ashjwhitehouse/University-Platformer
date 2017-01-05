package object;

import java.awt.Rectangle;

import main.GameWindow;
import tilemap.Tile;
import tilemap.Tilemap;

public abstract class Object {
	
	// Because this is an abstract class, everything has to be protected so other classes can see the variables
	protected Tilemap tilemap;
	protected int width, height, tileSize;
	protected double x, x2, mapx, y, y2, mapy;
	
	protected int hitboxWidth, hitboxHeight;
	
	protected int coordRow, coordCol;
	protected double destx, tempx, desty, tempy;
	protected boolean north, east, south, west, up, right, down, left, jumping, falling; 
	
	protected Animation animation;
	protected int currentAction, previousAction;
	protected boolean flip;
	
	protected double speed, topSpeed, stopSpeed, fallSpeed, topFallSpeed, jumpStartSpeed, jumpStopSpeed;
	
	public Object(Tilemap tm) {
		tilemap = tm;
		tileSize = tm.getTileSize();
	}
	
	public void getCorners(double x, double y) {
		int topTile = (int)(y - hitboxHeight / 2) / tileSize;
	    int leftTile = (int)(x - hitboxWidth / 2) / tileSize;
	    int rightTile = (int)(x + hitboxWidth / 2 - 1) / tileSize;
	    int bottomTile = (int)(y + hitboxHeight / 2 - 1) / tileSize;
	    
        if(topTile < 0 || bottomTile >= tilemap.getNumRows() ||
                leftTile < 0 || rightTile >= tilemap.getNumCols()) {
                north = east = south = west = false;
                return;
        }
	    
	    int n = tilemap.getType(topTile, leftTile);
	    north = n == Tile.FOREGROUND;
	    
	    int e = tilemap.getType(topTile, rightTile);
	    east = e == Tile.FOREGROUND;
	    
	    int s = tilemap.getType(bottomTile, rightTile);
	    south = s == Tile.FOREGROUND;
	    
	    int w = tilemap.getType(bottomTile, leftTile);
	    west = w == Tile.FOREGROUND;
	    
	    
	    
	    
	}
	
	public void checkMapCollision() {
		coordCol = (int)x / tileSize;
		coordRow = (int)y / tileSize;
		
		destx = x + x2;
		desty = y + y2;
		
		tempx = x;
		tempy = y;
		
		getCorners(x, desty);
		
		if(y2 < 0) {
			if(north || east) {
				y2 = 0;
				tempy = coordRow * tileSize + hitboxHeight / 2;
			}
			else {
				tempy += y2;
			}
		}
		if(y2 > 0) {
			if(west || south) {
				y2 = 0;
				falling = false;
				tempy = (coordRow + 1) * tileSize - hitboxHeight / 2;
			}
			else {
				tempy += y2;
			}
		}
		
		getCorners(destx, y);
		
		if(x2 < 0) {
			if(north || west) {
				x2 = 0;
				tempx = coordCol * tileSize + hitboxWidth / 2;
			}
			else {
				tempx += x2;
			}
		}
		
		if(x2 > 0) {
			if(east || south) {
				x2 = 0;
				tempx = (coordCol + 1) * tileSize - hitboxWidth / 2;
			}
			else {
				tempx += x2;
			}
		}
		
		if(!falling) {
			getCorners(x, desty + 1);
			
			if(!west && !south) {
				falling = true;
			}
		}
	}
	
	public boolean intersects(Object other) {
		Rectangle r1 = getRectangle();
		Rectangle r2 = other.getRectangle();
		return r1.intersects(r2);
	}
	
	public Rectangle getRectangle() {
		return new Rectangle((int)x - hitboxWidth, (int)y - hitboxHeight, hitboxWidth, hitboxHeight); 
	}
	
	public void setPos(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void setVector(double x2, double y2) {
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public void setMapPos() {
		mapx = tilemap.getx();
		mapy = tilemap.gety();
	}
	
	public void setUp(boolean b) {
		up = b;
	}
	
	public void setLeft(boolean b) {
		left = b;
	}
	
	public void setDown(boolean b) {
		down = b;
	}
	
	public void setRight(boolean b) {
		right = b;
	}
	
	public void setJumping(boolean b) {
		jumping = b;
	}
	
	public int getx() {
		return (int)x;
	}
	
	public int gety() {
		return (int)y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getHitboxWidth() {
		return hitboxWidth;
	}
	
	public int getHitboxHeight() {
		return hitboxHeight;
	}
	
	public boolean notVisible() {
		return x + mapx + width < 0 || x + mapx - width > GameWindow.WIDTH || y + mapy + height < 0 || y + mapy - height > GameWindow.WIDTH;
	}

}
