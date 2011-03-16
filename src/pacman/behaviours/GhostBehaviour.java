package pacman.behaviours;

import pacman.world.World;
import pacman.world.maps.Direction;


public abstract class GhostBehaviour extends Behaviour {
	
	Direction heading = Direction.LEFT;
	public Direction getMove(World world) {
		int tries = 0;
		boolean clockwise = Math.random() > 0.5;
		while(!entity.canMove(world, heading) && tries++ < 4)
			heading = heading.turn(clockwise);
		if(!entity.canMove(world, heading))
			heading = Direction.NONE;
		return heading;
	}
	
	public void reset() {
		heading = Direction.LEFT;
	}
}
