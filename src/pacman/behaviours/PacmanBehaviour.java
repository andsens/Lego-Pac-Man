package pacman.behaviours;

import java.awt.Point;

import pacman.behaviours.controllers.Controller;
import pacman.world.MovingEntity;
import pacman.world.maps.Coordinate;
import pacman.world.maps.Direction;
import pacman.world.tiles.Tile;


/**
 * Pac-man is the protagonist of this game and is controlled by the player.
 * This behaviour is supposed to be dependent on user input.
 * This is Pac-man <img src="../../../img/pacman.png" style="display:block" />
 * 
 * @author andsens
 * 
 */
public class PacmanBehaviour extends Behaviour {
	
	private Controller controller;
	
	public PacmanBehaviour(Controller controller) {
		this.controller = controller;
	}
	
	protected pacman.world.Pacman entity;
	public void setEntity(pacman.world.Pacman entity) {
		this.entity = entity;
	}
	
	private Direction nextHeading = Direction.LEFT;
	public void think() {
		Direction next = controller.getNextHeading();
		if(next != Direction.NONE)
			nextHeading = next;
		
		Coordinate currentTile = entity.getCurrentTile();
		
		if(nextHeading != Direction.NONE
		&& isNavigable(nextHeading.getNext(currentTile))) {
			heading = nextHeading;
		} else if(isNavigable(heading.getNext(currentTile))) {
			
		} else {
			Point location = entity.getLocation();
			location.translate(MovingEntity.width/2, MovingEntity.height/2);
			int modX = location.x % Tile.width;
			int modY = location.y % Tile.height;
			switch(heading) {
			case UP:
				if(modY <= Tile.height / 2)
					heading = Direction.NONE;
				break;
			case LEFT:
				if(modX <= Tile.width / 2)
					heading = Direction.NONE;
				break;
			case DOWN:
				if(modY >= Tile.height / 2)
					heading = Direction.NONE;
				break;
			case RIGHT:
				if(modX >= Tile.width / 2)
					heading = Direction.NONE;
				break;
			}
		}
	}
	
	public void reset() {
		nextHeading = Direction.LEFT;
		controller.reset();
	}
}
