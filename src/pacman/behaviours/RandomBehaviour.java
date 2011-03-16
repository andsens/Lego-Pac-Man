package pacman.behaviours;

import pacman.world.World;
import pacman.world.maps.Direction;

public class RandomBehaviour extends Behaviour {

	Direction heading = Direction.LEFT;
	public Direction getMove(World world) {
		Direction newHeading = heading;
		if(!canMove(world, heading) || Math.random() > 0.7) {
			boolean clockwise = Math.random() > 0.5;
			newHeading = heading.turn(clockwise);
			if(!canMove(world, newHeading))
				newHeading = heading.turn(!clockwise);
		}
		if(canMove(world, newHeading))
			heading = newHeading;
		if(!canMove(world, heading))
			heading =  Direction.NONE;
		return heading;
	}
	
	public void reset() {
		heading = Direction.LEFT;
	}
	
}
