package pacman.world.maps;

import java.awt.Point;

public class Coordinate extends Point {
	
	private static final long serialVersionUID = -3312946614975029286L;
	
	public Coordinate(int x, int y) {
		super(x, y);
	}
	
	public void scale(int horizontal, int vertical){
		setLocation(x*horizontal, y*vertical);
	}
}
