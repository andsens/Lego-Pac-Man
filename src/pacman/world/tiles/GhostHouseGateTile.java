package pacman.world.tiles;

import java.awt.Point;

public class GhostHouseGateTile extends Tile {

	private static final long serialVersionUID = 2979410766158923458L;
	
	private boolean oob = false;
	
	public GhostHouseGateTile(Point location, boolean outOfBounds) {
		super(location);
		oob = outOfBounds;
		spriteTile = new Point(14, 6);
	}
	
	public boolean isOOB() {
		return oob;
	}

}
