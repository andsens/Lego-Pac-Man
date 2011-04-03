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
	
	SCATTER, CHASE, FRIGHTENED, GHOSTHOUSE_ENTER, GHOSTHOUSE_LINGER, GHOSTHOUSE_EXIT;
}
