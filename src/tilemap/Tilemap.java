package tilemap;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

import main.GameWindow;

public class Tilemap {
	
	private double x, y, transitionSpeed;
	private int xmin, xmax, ymin, ymax;
	
	private int[][] map;
	private int tileSize, numRows, numCols, width, height;
	
	private BufferedImage tileset;
	private int tilesetWidth;
	private Tile[][] tiles;
	
	private int rowOrigin, rowsToDraw, colOrigin, colsToDraw;
	
	public Tilemap(int tileSize) {
		this.tileSize = tileSize;
		rowsToDraw = GameWindow.HEIGHT / tileSize + 2;
		colsToDraw = GameWindow.WIDTH / tileSize + 2;
		transitionSpeed = 0.1;
	}
	
	public void prepareTiles(String string) {
		
		try {
			tileset = ImageIO.read(getClass().getResourceAsStream(string));
			tilesetWidth = tileset.getWidth() / tileSize;
			tiles = new Tile[2][tilesetWidth];
			
			BufferedImage subimage;
			for(int col = 0; col < tilesetWidth; col++) {
				subimage = tileset.getSubimage(col * tileSize, 0, tileSize, tileSize);
				tiles[0][col] = new Tile(subimage, Tile.BACKGROUND);
				
				subimage = tileset.getSubimage(col * tileSize, tileSize, tileSize, tileSize);
				tiles[1][col] = new Tile(subimage, Tile.FOREGROUND);
			}
			
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
		
	}
	
	public void prepareMap(String string) {
		
		try {
			
			InputStream in = getClass().getResourceAsStream(string);
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			
			numCols = Integer.parseInt(reader.readLine());
			numRows = Integer.parseInt(reader.readLine());
			map = new int[numRows][numCols];
			width = numCols * tileSize;
			height = numRows * tileSize;
			
			String regex = "\\s+";
			for(int row = 0; row < numRows; row++) {
				String line = reader.readLine();
				String[] tokens = line.split(regex);
				for(int col = 0; col < numCols; col++) {
					map[row][col] = Integer.parseInt(tokens[col]);
				}
			}
			
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
		
	}
	
	public int getTileSize() { 
		return tileSize;
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
	
	public int getNumRows() {
		return numRows;
	}
	
	public int getNumCols() { 
		return numCols; 
	}
	
	public int getType(int row, int col) {
		int rc = map[row][col];
		int r = rc / tilesetWidth;
		int c = rc % tilesetWidth;
		return tiles[r][c].getType();
	}
	
	public void setPos(double x, double y) {
		
		this.x += (x - this.x) * transitionSpeed;
		this.y += (y - this.y) * transitionSpeed;
		
		forceBounds();
		
		colOrigin = (int)-this.x / tileSize;
		rowOrigin = (int)-this.y / tileSize;
		
	}
	
	private void forceBounds() {
		if(x < xmin) x = xmin;
		if(x > xmax) x = xmax;
		if(y < ymin) y = ymin;
		if(y > ymax) y = ymax;
	}
	
	public void draw(Graphics2D graphics) {
		
		for(int row = rowOrigin; row < rowOrigin + rowsToDraw; row++) {
			
			if(row >= numRows) break;
			
			for(int col = colOrigin; col < colOrigin + colsToDraw; col++) {
				
				if(col >= numCols) break;
				
				if(map[row][col] == 0) continue;
				
				int rc = map[row][col];
				int r = rc / tilesetWidth;
				int c = rc % tilesetWidth;
				
				graphics.drawImage(tiles[r][c].getImage(), (int)x + col * tileSize, (int)y + row * tileSize, null);
				
			}
			
		}
		
	}
	
	
	
}