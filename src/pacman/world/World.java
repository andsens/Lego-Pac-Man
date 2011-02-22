package pacman.world;

import javax.swing.JFrame;

/**
 * World is the entire game board and the main class holding everything in the game.
 * <img src="../../../Pac-Man Dossier/files/lvl1.png" style="display:block" />
 * 
 * @author andsens
 * 
 */
public class World extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -929497482909263536L;

	public World(Map map) {
		this.setTitle("Pac-Man control screen");
		this.setSize(8 * map.getHeight(), 8 * map.getWidth());
		this.add(map);
		this.setVisible(true);
	}
}
