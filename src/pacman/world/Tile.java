package pacman.world;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.io.IOException;

import javax.swing.JPanel;

import tools.Sprite;
import tools.Sprite.SpriteSize;

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
	private Sprite sprite;
	private Point spriteTile;
	private SpriteSize spriteSize;

	public Tile(int character, int x, int y, Sprite sprite) throws IOException {
		this.sprite = sprite;
		this.x = x;
		this.y = y;
		this.setPreferredSize(new Dimension(12, 12));
		switch (character) {
		case 'o':
			// Out-of-game tile
			this.spriteSize = SpriteSize.BIG;
			this.spriteTile = new Point(0, 4);
			break;
		case 'w':
			// Wall tile
			this.spriteSize = SpriteSize.BIG;
			this.spriteTile = new Point(8, 3);
			// this.navigable = false;
			break;
		case 'd':
			// Tile with a dot
			this.spriteSize = SpriteSize.SMALL;
			this.spriteTile = new Point(16, 0);
			break;
		case 'e':
			// Tile with an energizer
			this.spriteSize = SpriteSize.SMALL;
			this.spriteTile = new Point(18, 0);
			break;
		case 'n':
			// Empty navigable tile
			this.spriteSize = SpriteSize.BIG;
			this.spriteTile = new Point(0, 4);
			break;
		case 'g':
			// Ghost house
			this.spriteSize = SpriteSize.BIG;
			this.spriteTile = new Point(0, 4);
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		sprite.draw(spriteTile.x, spriteTile.y, g, spriteSize);
	}

}
