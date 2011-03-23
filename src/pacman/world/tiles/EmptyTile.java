package pacman.world.tiles;

import java.awt.Point;

public class EmptyTile extends Tile {

	private static final long serialVersionUID = 1373723500911476461L;

	public EmptyTile(Point location) {
		super(location);
		spriteTile = new Point(0, 4);
	}

}
