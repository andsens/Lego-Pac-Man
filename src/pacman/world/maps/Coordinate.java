package pacman.world.maps;

public class Coordinate {
	
	public int x, y;
	
	public Coordinate() {
		x = 0;
		y = 0;
	}
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void translate(int dx, int dy) {
		x += dx;
		y += dy;
	}
	
	public double distance(Coordinate coordinate) {
		int dx = Math.abs(x-coordinate.x);
		int dy = Math.abs(y-coordinate.y);
		return Math.sqrt(dx*dx+dy*dy);
	}
	
	public Coordinate clone() {
		return new Coordinate(x, y);
	}
	
	public boolean equals(Coordinate coordinate) {
		return coordinate.x == x && coordinate.y == y;
	}
}
