package pacman.world;

import java.awt.Container;
import java.util.Vector;


/**
 * World is the entire game board and the main class holding everything in the game.
 * <img src="../../../Pac-Man Dossier/files/lvl1.png" style="display:block" />
 * 
 * @author andsens
 * 
 */
public class World {

	private Map map;
	private Pacman pacman;
	private Vector<Ghost> ghosts;

	public World(Map map) {

	}

	public void update() {
		pacman.update();
		for (Ghost ghost : ghosts) {
			ghost.update();
		}
	}

	public void display() {
		Container content = map.getContentPane();
		content.paintComponents(content.getGraphics());
	}
}
