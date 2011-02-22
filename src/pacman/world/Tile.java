package pacman.world;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

/**
 * A tile of the world. It can be part of a wall, completely blank
 * or extended by another class to become special tile, like the spawning tile.
 * <a href="../../../Pac-Man Dossier/index.html#CH3_What_Tile_Am_I_In">More about tiles</a>.
 * 
 * @author andsens
 * 
 */
public class Tile extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3377638094038644024L;
	private int x;
	private int y;
	private boolean navigable;

	public Tile(int character, int x, int y) {
		this.x = x;
		this.y = y;
		switch (character) {
		case 'o':
			// Out-game tile
		case 'w':
			// Wall tile
			this.navigable = false;
			break;
		case 'd':
		case 'e':
		case 'n':
			// Navigable tile
			this.navigable = true;
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		if (navigable)
			this.setBackground(Color.BLACK);
	}

}
