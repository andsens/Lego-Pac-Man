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
	private Vector<Entity> entities;

	public World(Map map) {

	}

	public void update() {
		for (Entity entity : entities) {
			entity.update();
		}
	}

	public void display() {
		Container content = map.getContentPane();
		content.paintComponents(content.getGraphics());
	}
}
