package pacman.world;

import java.awt.Container;


/**
 * World is the entire game board and the main class holding everything in the game.
 * <img src="../../../Pac-Man Dossier/files/lvl1.png" style="display:block" />
 * 
 * @author andsens
 * 
 */
public class World {

	private Map map;

	public World(Map map) {

	}

	public void actualize() {
		Container content = map.getContentPane();
		content.paintComponents(content.getGraphics());
	}
}
