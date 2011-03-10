package pacman.world;

import java.awt.Point;

/**
 * A small dot, which Pac-man can eat, yielding ten points.
 * 
 * @author andsens
 * 
 */
public class Dot extends Entity {
	
	private static final long serialVersionUID = -2668630583143146926L;

	public Dot() {
		spriteTile = new Point(16, 0);
	}
}
