package tilemap;

import java.awt.image.*;

public class Tile {
	
	private BufferedImage image;
	private int type;
	
	// Background tiles
	public static final int BACKGROUND = 0;
	
	// Foreground tiles
	public static final int FOREGROUND = 1;
	
	public Tile(BufferedImage image, int type) {
		this.image = image;
		this.type = type;
		
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public int getType () {
		return type;
	}

}
