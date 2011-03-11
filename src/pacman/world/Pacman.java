package pacman.world;

import java.awt.Point;

import pacman.behaviours.Behaviour;
import pacman.world.maps.Coordinate;

/**
 * Represents Pac-man in the level.
 * 
 * @author andsens
 * 
 */
public class Pacman extends MovingEntity {

	private static final long serialVersionUID = 4731453043247286964L;

	public Pacman(Coordinate coordinate, Behaviour behaviour) {
		super(coordinate, behaviour);
		coordinate.translate(1, -5);
		setLocation(coordinate);
		spriteTile = new Point(0, 16);
	}

}
