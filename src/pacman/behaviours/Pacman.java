package pacman.behaviours;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import pacman.world.World;
import pacman.world.maps.Direction;

/**
 * Pac-man is the protagonist of this game and is controlled by the player.
 * This behaviour is supposed to be dependent on user input.
 * This is Pac-man <img src="../../../img/pacman.png" style="display:block" />
 * 
 * @author andsens
 * 
 */
public class Pacman extends Behaviour implements KeyListener {
	
	long resetNext = -1;
	Direction heading = Direction.NONE;
	public Direction getMove(World world) {
		Direction go = null;
		if(resetNext > 500)
			nextDirection = null;
		if(nextDirection != null
		&& canMove(world, nextDirection)) {
			heading = nextDirection;
			nextDirection = null;
		} else {
			Direction diagonal = heading.turn(nextDirection);
			if(diagonal != Direction.NONE
			&& canMove(world, diagonal)) {
				resetNext = -1;
				go = diagonal;
			}
		}
		if(go == null)
			go = heading;
		if(!canMove(world, go)) {
			go = Direction.NONE;
			heading = go;
		}
		return go;
	}
	
	Direction nextDirection;
	public void keyPressed(KeyEvent event) {
		if(Direction.getKeyTranslation(event) != null) {
			resetNext = -1;
			nextDirection = Direction.getKeyTranslation(event);
		}
	}

	public void keyReleased(KeyEvent event) {
		if(Direction.getKeyTranslation(event) != null)
			resetNext = System.currentTimeMillis();
	}

	public void keyTyped(KeyEvent event) {
	}

	public void reset() {
		heading = Direction.NONE;
	}
}
