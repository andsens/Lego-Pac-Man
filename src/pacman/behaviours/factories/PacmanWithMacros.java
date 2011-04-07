package pacman.behaviours.factories;

import pacman.behaviours.PacmanBehaviour;
import pacman.behaviours.controllers.Controller;
import pacman.behaviours.controllers.MacroController;
import pacman.behaviours.ghosts.Blinky;
import pacman.behaviours.ghosts.Clyde;
import pacman.behaviours.ghosts.GhostBehaviour;
import pacman.behaviours.ghosts.Inky;
import pacman.behaviours.ghosts.Pinky;

public class PacmanWithMacros extends BehaviourFactory {
	
	protected PacmanBehaviour getPacman() {
		return new PacmanBehaviour(getController());
	}
	
	protected Controller getPacmanController() {
		return new MacroController();
	}

	protected GhostBehaviour getBlinky() {
		return new Blinky();
	}

	protected GhostBehaviour getPinky() {
		return new Pinky();
	}

	protected GhostBehaviour getInky() {
		return new Inky();
	}

	protected GhostBehaviour getClyde() {
		return new Clyde();
	}
}
