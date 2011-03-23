package pacman.behaviours;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import pacman.world.MovingEntity;
import pacman.world.World;
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
public class Pacman extends PacmanBehaviour implements KeyListener {
	
	int keyPressTimeout = 200;
	
	long resetNext = -1;
	Direction nextHeading = Direction.NONE;
	public void think(World world) {
		Coordinate currentTile = entity.getCurrentTile();
		
		if(nextHeading != Direction.NONE
		&& valid(world, nextHeading.getNext(currentTile))) {
			heading = nextHeading;
		} else if(valid(world, heading.getNext(currentTile))) {
			
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
		if(System.currentTimeMillis()-resetNext > keyPressTimeout)
			nextHeading = Direction.NONE;
	}
	
	protected boolean valid(World world, Coordinate location) {
		return world.isValidPacmanTile(location);
	}
	
	public void keyPressed(KeyEvent event) {
		if(Direction.getKeyTranslation(event) != Direction.NONE) {
			resetNext = System.currentTimeMillis();
			nextHeading = Direction.getKeyTranslation(event);
		}
	}

	public void keyReleased(KeyEvent event) {
		if(Direction.getKeyTranslation(event) != Direction.NONE)
			resetNext = System.currentTimeMillis();
	}

	public void keyTyped(KeyEvent event) {
	}

	public void reset() {
		nextHeading = Direction.NONE;
	}
}
