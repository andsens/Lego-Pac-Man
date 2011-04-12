package pacman.behaviours.controllers;

import pacman.world.World;
import pacman.world.maps.Direction;

public interface Controller {
	
	public Direction getNextHeading();
	
	public void reset();
	
	public void listen(boolean listenToInput);
	
	public void setWorld(World world);
}
