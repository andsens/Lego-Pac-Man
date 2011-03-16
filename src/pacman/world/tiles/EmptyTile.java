package pacman.world.tiles;

import java.awt.Point;

import pacman.world.maps.Coordinate;

public class EmptyTile extends Tile {

	private static final long serialVersionUID = 1373723500911476461L;

	public EmptyTile(Coordinate coordinate) {
		super(coordinate);
		spriteTile = new Point(0, 4);
	}

}
