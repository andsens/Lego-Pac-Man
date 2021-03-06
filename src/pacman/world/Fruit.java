package pacman.world;

import java.awt.Point;

/**
 * Fruits are bonus symbols. They appear twice per level and appear after 70 dots have been eaten.
 * They are worth between 100 and 5.000 points.
 * The <a href="../../../Pac-Man Dossier/index.html#LvlSpecs">level specs table</a> explains, which
 * fruit appears in which level.
 * 
 * @author andsens
 * 
 */
public class Fruit extends Entity {

	private static final long serialVersionUID = 6910154307184023631L;

	public Fruit(Point location) {
		super(location);
	}

}
