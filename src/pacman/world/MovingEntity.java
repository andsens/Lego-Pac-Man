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
	
	private Coordinate spawnPoint;
	
	public MovingEntity(Point location) {
		super(location);
		setSize(width, height);
		spawnPoint = getCurrentTile();
	}
	
	protected World world;
	public void setWorld(World world) {
		this.world = world;
	}
	
	public final void tick(long ticks) {
		boolean shouldMove = true;
		int speed = getSpeed(world.getLevel());
		if(speed == 0) {
			shouldMove = false;
		} else if(speed < 100) {
			float rate = 100/(100-speed);
			if(ticks % rate < 0.5
			|| (ticks % rate == 0.5 && ticks + 1 % rate < 0.5)) {
				shouldMove = false;
			}
		}
		if(shouldMove) {
			think();
			move();
			act();
			animate();
		}
	}
	
	public Coordinate getCurrentTile() {
		Point currentLocation = getLocation();
		currentLocation.translate(MovingEntity.width/2, MovingEntity.height/2);
		Coordinate currentTile = new Coordinate();
		currentTile.x = (int) Math.floor(currentLocation.x/Tile.width);
		currentTile.y = (int) Math.floor(currentLocation.y/Tile.height);
		return currentTile;
	}
	
	protected abstract Behaviour getBehaviour();
	
	protected abstract void think();
	
	protected void move() {
		Direction heading = getHeading();
		Point location = getLocation();
		heading.translate(location);
		setLocation(location);
	}
	
	protected abstract void act();
	
	protected abstract void animate();
	
	protected abstract int getSpeed(int level);
	
	public void reset() {
		super.reset();
		getBehaviour().reset();
		setVisible(true);
	}
	
	public Direction getHeading() {
		return getBehaviour().getHeading();
	}
	
	public Coordinate getSpawnPoint() {
		return spawnPoint;
	}
}
