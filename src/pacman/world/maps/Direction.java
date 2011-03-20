package pacman.world.maps;

import java.awt.Point;
import java.awt.event.KeyEvent;

public enum Direction {
						UP(0, -1),
			UPLEFT(-1, -1),			UPRIGHT(1, -1),
	LEFT(-1, 0), 		NONE(0, 0),			RIGHT(1, 0),
			DOWNLEFT(-1, 1),		DOWNRIGHT(1, 1),
						DOWN(0, 1);
	
	private int x, y;
	
	private Direction(int x, int y) {
		this.x = x;
		this.y = y;
		
	}
	
	public void translate(Point location) {
		translate(location, 1, 1);
	}
	
	public void translate(Point location, int factor) {
		translate(location, factor, factor);
	}
	
	public void translate(Point location, int xFactor, int yFactor) {
		location.translate(x*xFactor, y*yFactor);
	}
	
	public void apply(Point location) {
		location.x *= x;
		location.y *= y;
	}
	
	public Direction turn() {
		return turn(false);
	}
	
	public Direction turn(boolean clockwise) {
		switch(this) {
		case UP:
			return clockwise?RIGHT:LEFT;
		case LEFT:
			return clockwise?UP:DOWN;
		case DOWN:
			return clockwise?LEFT:RIGHT;
		case RIGHT:
			return clockwise?DOWN:UP;
		case NONE:
		default:
			return NONE;
		}
	}
	
	public Direction nudge() {
		return nudge(false);
	}
	
	public Direction turn(Direction direction) {
		switch(this) {
		case UP:
			if(direction == LEFT)
				return UPLEFT;
			if(direction == RIGHT)
				return UPRIGHT;
			return NONE;
		case LEFT:
			if(direction == UP)
				return UPLEFT;
			if(direction == DOWN)
				return DOWNLEFT;
			return NONE;
		case DOWN:
			if(direction == LEFT)
				return DOWNLEFT;
			if(direction == RIGHT)
				return DOWNRIGHT;
			return NONE;
		case RIGHT:
			if(direction == UP)
				return UPRIGHT;
			if(direction == DOWN)
				return DOWNRIGHT;
			return NONE;
		case NONE:
		default:
			return NONE;
		}
	}
	
	public Direction nudge(boolean clockwise) {
		switch(this) {
		case UP:
			return clockwise?UPRIGHT:UPLEFT;
		case UPLEFT:
			return clockwise?UP:LEFT;
		case LEFT:
			return clockwise?UPLEFT:DOWNLEFT;
		case DOWNLEFT:
			return clockwise?LEFT:DOWN;
		case DOWN:
			return clockwise?DOWNLEFT:DOWNRIGHT;
		case DOWNRIGHT:
			return clockwise?DOWN:RIGHT;
		case RIGHT:
			return clockwise?DOWNRIGHT:UPRIGHT;
		case UPRIGHT:
			return clockwise?RIGHT:UP;
		case NONE:
		default:
			return NONE;
		}
	}
	
	public boolean isDiagonal() {
		return x != 0 && y != 0;
	}
	
	public static Direction getKeyTranslation(KeyEvent event) {
		switch(event.getKeyCode()) {
		case KeyEvent.VK_UP:
			return UP;
		case KeyEvent.VK_LEFT:
			return LEFT;
		case KeyEvent.VK_DOWN:
			return DOWN;
		case KeyEvent.VK_RIGHT:
			return RIGHT;
		default:
			return NONE;
		}
	}
}
