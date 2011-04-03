package pacman.world.tiles;

import java.awt.Point;

public class GhostHouseTile extends Tile {

	private static final long serialVersionUID = 2979410766158923458L;

	public GhostHouseTile(Point location) {
		super(location);
		spriteTile = new Point(0, 4);
	}

}
