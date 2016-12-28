// http://www.java-gaming.org/index.php?topic=30912.0
// https://github.com/DanielKoren/Game-State-Manager
// https://www.youtube.com/watch?v=OCcZUO4Zf6o

package state;

import java.util.ArrayList;

public class StateManager {
	
	private int currentState;
	
	private ArrayList<State> states;
	
	public static final int stateMenu = 0;
	public static final int stateLvl1 = 1;
	
	public StateManager() {
		
		states = new ArrayList<State>();
		
		currentState = 0;
		states.add(new StateMenu(this));
		states.add(new StateLvl1(this));
		
	}
	
	public void stateSet(int state) {
		currentState = state;
		states.get(currentState).init();
	}
	
	public void update() {
		states.get(currentState).update();
	}
	
	public void draw(java.awt.Graphics2D g) {
		states.get(currentState).draw(g);
	}
	
	public void keyPressed(int k) {
		states.get(currentState).keyPressed(k);
	}
	
	public void keyReleased(int k) {
		states.get(currentState).keyReleased(k);
	}

}
