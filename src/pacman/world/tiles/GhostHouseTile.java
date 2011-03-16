package pacman.world.tiles;

import java.awt.Point;

import pacman.world.maps.Coordinate;

public class GhostHouseTile extends Tile {

	private static final long serialVersionUID = 2979410766158923458L;

	public GhostHouseTile(Coordinate coordinate, boolean isBarrier) {
		super(coordinate);
		spriteTile = isBarrier?new Point(14, 6):new Point(0, 4);
	}

}
