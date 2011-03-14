package pacman.world;

import java.awt.Point;

import pacman.world.graphics.Graphic;
import pacman.world.maps.Coordinate;

/**
 * An object in the level. This can be a simple dot or Pac-man.
 * 
 * @author andsens
 * 
 */
public abstract class Entity extends Graphic {
	
	private static final long serialVersionUID = 735990140079476872L;
	
	Point originalLocation;
	
	public Entity(Coordinate coordinate) {
		super(coordinate);
		originalLocation = coordinate;
	}
	
	public void reset() {
		setLocation(originalLocation);
	}
}
