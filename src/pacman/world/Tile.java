package pacman.world;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * A tile of the world. It can be part of a wall, completely blank
 * or extended by another class to become special tile, like the spawning tile.
 * <a href="../../../Pac-Man Dossier/index.html#CH3_What_Tile_Am_I_In">More about tiles</a>.
 * 
 * @author andsens
 * 
 */
public class Tile extends JPanel {

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
		this.setPreferredSize(new Dimension(16, 16));
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
		case 'g':
			// Navigable tile
			this.navigable = true;
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		if (navigable)
			g.setColor(Color.GREEN);
		else
			g.setColor(Color.BLACK);
		g.fillRect(0, 0, 16, 16);
	}

}
