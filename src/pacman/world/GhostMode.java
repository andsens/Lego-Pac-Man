package pacman.world;

/**
 * Ghosts can be in different modes, normally alternating between scater and chase.
 * Eating an energizer causes the ghosts to go into frightened mode.
 * <a href="../../../Pac-Man Dossier/index.html#CH2_Scatter_Chase_Repeat">More on this</a>.
 * 
 * @author andsens
 * 
 */
public enum GhostMode {
	
	SCATTER(0), CHASE(1), FRIGHTENED(2);
	
	private final int mode;
	
	GhostMode(int mode) {
		this.mode = mode;
	}
}
