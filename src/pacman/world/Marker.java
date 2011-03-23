package pacman.world;

import java.awt.Point;

public class Marker extends Entity {

	private static final long serialVersionUID = 3469209300512474665L;
	
	public static int width = 4;
	public static int height = 4;
	
	public Marker(Point location) {
		super(location);
		location.translate(4, 4);
		setLocation(location);
		setSize(width, height);
		spriteTile = new Point(9, 3);
	}

}
