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
	
	Direction heading = Direction.NONE;
	public Direction getMove(World world) {
		if(nextDirection != null) {
			if(canMove(world, nextDirection)) {
				heading = nextDirection;
				nextDirection = null;
			} else {
				Direction diagonal = nextDirection.turn(false, true);
				if(canMove(world, diagonal)) {
					heading = diagonal;
				} else {
					diagonal = nextDirection.turn(true, true);
					if(canMove(world, diagonal))
						heading = diagonal;
				}
			}
		}
		if(!canMove(world, heading))
			heading = Direction.NONE;
		return heading;
	}
	
	Direction nextDirection;
	public void keyPressed(KeyEvent event) {
		nextDirection = Direction.getKeyTranslation(event);
	}

	public void keyReleased(KeyEvent event) {
		nextDirection = null;
	}

	public void keyTyped(KeyEvent event) {
	}
}
