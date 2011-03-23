package pacman.world.tiles;

import java.awt.Point;

public class NavigableTile extends Tile {

	private static final long serialVersionUID = -398458146473208898L;

	public NavigableTile(Point location) {
		super(location);
		spriteTile = new Point(0, 4);
	}

}
