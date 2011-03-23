package pacman.world;

import java.awt.Point;

/**
 * There are four giant dots per level, called energizers.
 * They yield 50 points each and shift the ghosts into <a href="../../../Pac-Man Dossier/index.html#CH2_Frightening_Behavior">frightened mode</a>.
 * 
 * @author andsens
 * 
 */
public class Energizer extends Dot {
	
	private static final long serialVersionUID = -7216198487647130829L;
	
	public Energizer(Point location) {
		super(location);
		setSize(width, height);
		spriteTile = new Point(20, 0);
	}
}
