package pacman.world.tiles;

import java.awt.Point;

/**
 * This special tile forbids Ghosts from moving upwards.
 * <a href="../../../Pac-Man Dossier/index.html#CH2_Areas_To_Exploit">Read more here</a>.
 * 
 * @author andsens
 * 
 */
public class RedZoneTile extends NavigableTile {
	
	private static final long serialVersionUID = 6977230439833907548L;
	
	public RedZoneTile(Point location) {
		super(location);
	}

}
