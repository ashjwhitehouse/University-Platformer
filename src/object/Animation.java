package object;

import java.awt.image.BufferedImage;

public class Animation {
	
	private BufferedImage[] frames;
	private int currentFrame;
	private long startTime, delayTime;
	private boolean played;
	
	public Animation() {
		played = false;
	}
	
	public void setFrames(BufferedImage[] frames) {
		this.frames = frames;
		played = false;
		currentFrame = 0;
		startTime = System.nanoTime();
	}
	
	public void setDelayTime(long delay) {
		delayTime = delay;
	}
	
	public void setCurrentFrame(int f) {
		currentFrame = f;
	}
	
	public void update() {
		if(delayTime == -1) return;
		
		long passed = (System.nanoTime() - startTime) / 1000000;
		if(passed > delayTime) {
			currentFrame++;
			startTime = System.nanoTime();
		}
		
		// Out of bounds, loop to 0 (1st frame)
		if(currentFrame == frames.length) {
			currentFrame = 0;
			played = true;
		}
	}
	
	public boolean hasPlayed() {
		return played;
	}
	
	public int getCurrentFrame() {
		return currentFrame;
	}
	
	public BufferedImage getImage() {
		return frames[currentFrame];
	}

}
