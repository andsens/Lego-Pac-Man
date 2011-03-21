package pacman.behaviours;


public abstract class PacmanBehaviour extends Behaviour {
	
	protected pacman.world.Pacman entity;
	public void setEntity(pacman.world.Pacman entity) {
		this.entity = entity;
	}
}
