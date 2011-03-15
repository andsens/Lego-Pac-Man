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
	Direction nextDirection = Direction.NONE;
	public Direction getMove(World world) {
		Direction go = Direction.NONE;
		if(nextDirection == Direction.NONE) {
			go = heading;
			if(!canMove(world, go))
				go = heading.nudge();
			if(!canMove(world, go))
				go = heading.nudge(true);
		} else {
			if(canMove(world, nextDirection)) {
				heading = nextDirection;
				go = heading;
			} else {
				Direction diagonal = heading.turn(nextDirection);
				if(diagonal != Direction.NONE
				&& canMove(world, diagonal)) {
					resetNext = -1;
					go = diagonal;
				} else {
					go = heading;
				}
			}
		}
		if(!canMove(world, go))
			go = Direction.NONE;
		if(resetNext != -1
		&& System.currentTimeMillis()-resetNext > 100) {
			nextDirection = Direction.NONE;
			resetNext = -1;
		}
		return go;
	}
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
