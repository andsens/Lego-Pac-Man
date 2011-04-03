package pacman.behaviours;

import pacman.world.GhostMode;
import pacman.world.MovingEntity;
import pacman.world.World;
import pacman.world.maps.Coordinate;
import pacman.world.maps.Direction;
import pacman.world.maps.Type;

/**
 * An abstract class for describing the common behaviour of all ghosts and the
 * player.
 * 
 * @author andsens
 * 
 */
public abstract class Behaviour {
	
	protected MovingEntity entity;
	public void setEntity(MovingEntity entity) {
		this.entity = entity;
	}
	
	private World world;
	public void setWorld(World world) {
		this.world = world;
	}
	
	public abstract void think();
	
	protected Direction heading = Direction.NONE;
	public Direction getHeading() {
		return heading;
	}
	
	public void reset() {
		heading = Direction.NONE;
	}
	
	public void capTileLocation(Coordinate tile) {
		tile.x = tile.x > 0 ? Math.min(tile.x, world.getMapWidth()-1) : 0;
		tile.y = tile.y > 0 ? Math.min(tile.y, world.getMapHeight()-1) : 0;
	}
	
	protected MovingEntity getPacman() {
		return world.getMovingEntity(Type.PACMAN);
	}
	
	protected MovingEntity getBlinky() {
		return world.getMovingEntity(Type.BLINKY);
	}
	
	protected MovingEntity getPinky() {
		return world.getMovingEntity(Type.PINKY);
	}
	
	protected MovingEntity getInky() {
		return world.getMovingEntity(Type.INKY);
	}
	
	protected MovingEntity getClyde() {
		return world.getMovingEntity(Type.CLYDE);
	}
	
	protected GhostMode getGhostMode() {
		return world.getGhostMode();
	}
	
	protected boolean isNavigable(Coordinate coordinate) {
		return world.isNavigable(coordinate);
	}
	
	protected boolean isGhostHouse(Coordinate coordinate) {
		return world.isGhostHouse(coordinate);
	}
	
	protected boolean isGhostHouseGate(Coordinate coordinate) {
		return world.isGhostHouseGate(coordinate);
	}
	
	protected boolean isRedZone(Coordinate coordinate) {
		return world.isRedZone(coordinate);
	}
	
	protected Coordinate getSpawnPoint(Type type) {
		return world.getSpawnPoint(type);
	}
	
	protected Coordinate getGhostHouseEntrance() {
		return world.getGhostHouseEntrance();
	}
	
	protected void  markCoordinate(Coordinate coordinate) {
		world.markCoordinate(coordinate);
	}
}
