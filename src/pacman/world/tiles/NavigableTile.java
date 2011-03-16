package pacman.world.tiles;

import java.awt.Point;

import pacman.world.maps.Coordinate;

public class NavigableTile extends Tile {

	private static final long serialVersionUID = -398458146473208898L;

	public NavigableTile(Coordinate coordinate) {
		super(coordinate);
		spriteTile = new Point(0, 4);
	}

}
