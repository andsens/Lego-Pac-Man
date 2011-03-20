package pacman.world;

import java.awt.Point;

import pacman.world.maps.Coordinate;

public class Marker extends Entity {

	private static final long serialVersionUID = 3469209300512474665L;
	
	public static int width = 4;
	public static int height = 4;
	
	public Marker(Coordinate coordinate) {
		super(coordinate);
		coordinate.translate(4, 4);
		setLocation(coordinate);
		setSize(width, height);
		spriteTile = new Point(9, 3);
	}

}
