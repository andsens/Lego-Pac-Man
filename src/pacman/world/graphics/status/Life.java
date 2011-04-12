package pacman.world.graphics.status;

import java.awt.Point;

import pacman.world.graphics.Graphic;

public class Life extends Graphic {
	
	private static final long serialVersionUID = -6071947868937256996L;
	
	public static final int width = 22;
	public static final int height = 22;
	
	public Life(Point location) {
		super(location);
		spriteTile = new Point(4, 22);
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
}
