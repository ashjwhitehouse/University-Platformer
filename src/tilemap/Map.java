package tilemap;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class Map {
	
	private double x;
	private int xmin;
	private int xmax;
	
	private double y;
	private int ymin;
	private int ymax;
	
	// Tileset stuff
	private BufferedImage tileset;
	private Tile[][] tiles;
	private int noTiles;
	
	// Map stuff
	private int[][] map;
	private int tileSize;
	private int mapWidth;
	private int mapHeight;
	private int mapRows;
	private int mapColumns;
	
	// Stuff to draw the tiles; I only want to draw the tiles that are required, rather than all the tiles at once,
	// as constantly drawing tens of thousands of tiles 30 times per second is ridiculous
	private int rowOffset;
	private int rowsToDraw;
	private int columnOffset;
	private int columnsToDraw;
	
	public Map(int tileSize) {
		this.tileSize = tileSize;
		rowsToDraw = 9; // (winHeight / tileSize) + 1 additional row for seamless transitioning
		columnsToDraw = 12; // (winWidth / tileSize), rounded up, + 1 additional row for seamless transitioning
	}
	
	public void prepareTileset (String stream) {
		
		try {
			
			tileset = ImageIO.read(getClass().getResourceAsStream(stream));
			noTiles = tileset.getWidth() / tileSize;
			tiles = new Tile[2][noTiles];
			
			BufferedImage subImage;
			for(int col = 0; col < noTiles; col++) {
				// First row of tiles, all tiles with no collision
				subImage = tileset.getSubimage(col * tileSize, 0, tileSize, tileSize);
				tiles[0][col] = new Tile(subImage, Tile.BACKGROUND);
				
				// Second row of tiles, all tiles with player collision
				subImage = tileset.getSubimage(col * tileSize, tileSize, tileSize, tileSize);
				tiles[1][col] = new Tile(subImage, Tile.FOREGROUND);
			}
			
			
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
		
	}
	
	public void prepareMap (String stream) {
		
		try {
			
			InputStream in = getClass().getResourceAsStream(stream);
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			
			mapRows = Integer.parseInt(reader.readLine());
			mapColumns = Integer.parseInt(reader.readLine());
			map = new int[mapRows][mapColumns];
			mapWidth = mapColumns * tileSize;
			mapHeight = mapRows * tileSize;
			
			// Regex expression for blank space
			String tokenDelimiter = "\\s+";
			
			for(int row = 0; row < mapRows; row++) {
				String line = reader.readLine();
				String[] tokens = line.split(tokenDelimiter);
				
				for(int col = 0; col < mapColumns; col++) {
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
		return mapWidth;
	}
	
	public int getHeight() {
		return mapHeight;
	}
	
	public int getType(int row, int col) {
		int coord = map[row][col];
		int r = coord / noTiles;
		int c = coord % noTiles;
		return tiles[r][c].getType();
	}
	
	private void boundForcer() {
		if(x < xmin) x = xmin;
		if(x > xmax) x = xmax;
		if(y < ymin) y = ymin;
		if(y > ymax) y = ymax;
	}
	
	public void setPos(double x, double y) {
		this.x = x;
		this.y = y;
		
		boundForcer();
		
		columnOffset = (int)-this.x / tileSize;
		rowOffset = (int)-this.y / tileSize;
		
	}
	
	public void draw(Graphics2D g) {
		for(int row = rowOffset; row < rowOffset + rowsToDraw; row++) {
			
			if(row >= rowsToDraw) break;
			
			for(int col = columnOffset; col < columnOffset + columnsToDraw; col++) {
				
				if(col >= columnsToDraw) break;
				
				if(map[row][col] == 0) continue;
				
				int coord = map[row][col];
				int r = coord / noTiles;
				int c = coord % noTiles;
				
				g.drawImage(tiles[r][c].getImage(), (int)x + col * tileSize, (int)y + row * tileSize, null);
				
			}
			
		}
		
	}

	

}
