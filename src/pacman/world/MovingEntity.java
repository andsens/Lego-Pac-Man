package pacman.world;

import java.awt.Point;

import pacman.behaviours.Behaviour;
import pacman.world.maps.Coordinate;
import pacman.world.maps.Direction;
import pacman.world.tiles.Tile;


/**
 * A moving entity in the level. This is either Pac-man or one of the ghosts.
 * This entity type is controlled by a behvaiour.
 * 
 * @author andsens
 * 
 */
public abstract class MovingEntity extends Entity implements Changeable  {

	private static final long serialVersionUID = -5115554255945990447L;

	public static int width = 22;
	public static int height = 22;
	
	public MovingEntity(Coordinate coordinate) {
		super(coordinate);
		setSize(width, height);
	}
	
	public final void tick(World world) {
		boolean shouldMove = true;
		int speed = getSpeed();
		if(speed == 0) {
			shouldMove = false;
		} else if(speed < 100) {
			float rate = 100/(100-speed);
			int tickCount = world.getTickCount();
			if(tickCount % rate < 0.5
			|| (tickCount % rate == 0.5 && tickCount + 1 % rate < 0.5)) {
				shouldMove = false;
			}
		}
		if(shouldMove) {
			think(world);
			move();
			act(world);
			animate();
		}
	}
	
	protected Point getEdge(Direction side) {
		switch(side) {
		case UP:
			return new Point(11, 6);
		case LEFT:
			return new Point(6, 11);
		case DOWN:
			return new Point(11, 16);
		case RIGHT:
			return new Point(16, 11);
		case NONE:
		default:
			return new Point(11, 11);
		}
	}
	
	public Point getCurrentTile() {
		Point currentTile = getLocation();
		currentTile.translate(MovingEntity.width/2, MovingEntity.height/2);
		currentTile.x = (int) Math.floor(currentTile.x/Tile.width);
		currentTile.y = (int) Math.floor(currentTile.y/Tile.height);
		return currentTile;
	}
	
	protected abstract Behaviour getBehaviour();
	
	protected abstract void think(World world);
	
	protected void move() {
		Direction heading = getBehaviour().getHeading();
		Point location = getLocation();
		heading.translate(location);
		setLocation(location);
	}
	
	protected abstract void act(World world);
	
	protected abstract void animate();
	
	protected abstract int getSpeed();
	
	public Direction getHeading() {
		return getBehaviour().getHeading();
	}
}
