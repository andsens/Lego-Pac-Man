package pacman.world;

import java.awt.Point;

import pacman.world.maps.Coordinate;

/**
 * A small dot, which Pac-man can eat, yielding ten points.
 * 
 * @author andsens
 * 
 */
public class Dot extends Entity {
	
	private static final long serialVersionUID = -2668630583143146926L;
	
	public static int width = 12;
	public static int height = 12;
	
	public Dot(Coordinate coordinate) {
		super(coordinate);
		setSize(width, height);
		spriteTile = new Point(16, 0);
	}
}
