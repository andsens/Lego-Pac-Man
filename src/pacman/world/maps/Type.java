package pacman.world.maps;

import java.awt.Point;

import pacman.behaviours.factories.BehaviourFactory;
import pacman.world.Dot;
import pacman.world.Energizer;
import pacman.world.Ghost;
import pacman.world.MovingEntity;
import pacman.world.Pacman;
import pacman.world.tiles.EmptyTile;
import pacman.world.tiles.GhostHouseGateTile;
import pacman.world.tiles.GhostHouseTile;
import pacman.world.tiles.NavigableTile;
import pacman.world.tiles.RedZoneTile;
import pacman.world.tiles.Tile;
import pacman.world.tiles.WallTile;

public enum Type {
	UP_DBL('H'), DWN_DBL('h'), LFT_DBL('V'), RGT_DBL('v'),
	UPLFT_DBL('y'), DWNLFT_DBL('z'), UPRGT_DBL('w'), DWNRGT_DBL('t'),
	UPLFT_DBL_R('j'), DWNLFT_DBL_R('q'), UPRGT_DBL_R('k'), DWNRGT_DBL_R('s'),
	UP_STOP_RGT('m'), UP_STOP_LFT('n'),
	SPLT_DWN_RGT('f'), SPLT_DWN_LFT('a'),
	SPLT_LFT_UP('A'), SPLT_LFT_DWN('b'),
	SPLT_RGT_UP('i'), SPLT_RGT_DWN('c'),
	UP('u'), LEFT('l'), DOWN('d'), RIGHT('r'),
	UPLFT('U'), DWNLFT('L'), DWNRGT('R'), UPRGT('x'),
	OOB('o'), EMPTY('e'), GHOSTHOUSE('g'), GHOSTHOUSEGATE('G'), GHOSTHOUSEGATE_OOB('F'), REDZONE('Z'),
	DOT('D'), ENERGIZER('E'),
	PACMAN('P'), BLINKY('B'), PINKY('p'), INKY('I'), CLYDE('C');
	
	private final int character;
	
	Type(int type) {
		this.character = type;
	}
	
	public static Type get(int character) {
		for(Type type : Type.values()) {
			if(type.character == character)
				return type;
		}
		return null;
	}
	
	public Tile createTile(Point location) {
		switch(this) {
		case OOB:
			return new EmptyTile(location);
		case EMPTY:
		case DOT:
		case ENERGIZER:
		case PACMAN:
		case BLINKY:
			return new NavigableTile(location);
		case REDZONE:
			return new RedZoneTile(location);
		case GHOSTHOUSEGATE:
			return new GhostHouseGateTile(location, false);
		case GHOSTHOUSEGATE_OOB:
			return new GhostHouseGateTile(location, true);
		case GHOSTHOUSE:
		case INKY:
		case PINKY:
		case CLYDE:
			return new GhostHouseTile(location);
		case UP:
		case LEFT:
		case DOWN:
		case RIGHT:
		case UPLFT:
		case DWNLFT:
		case DWNRGT:
		case UPRGT:
		case UP_DBL:
		case DWN_DBL:
		case LFT_DBL:
		case RGT_DBL:
		case UPLFT_DBL:
		case DWNLFT_DBL:
		case UPRGT_DBL:
		case DWNRGT_DBL:
		case UPLFT_DBL_R:
		case DWNLFT_DBL_R:
		case UPRGT_DBL_R:
		case DWNRGT_DBL_R:
		case UP_STOP_RGT:
		case UP_STOP_LFT:
		case SPLT_DWN_RGT:
		case SPLT_DWN_LFT:
		case SPLT_LFT_UP:
		case SPLT_LFT_DWN:
		case SPLT_RGT_UP:
		case SPLT_RGT_DWN:
			return new WallTile(location, this);
		}
		return null;
	}
	
	public Dot createDot(Point location) {
		switch(this) {
		case DOT:
			return new Dot(location);
		case ENERGIZER:
			return new Energizer(location);
		}
		return null;
	}
	
	public MovingEntity createEntity(Point location, BehaviourFactory behaviours) {
		switch(this) {
		case PACMAN:
			return new Pacman(location, behaviours.getPacmanBehaviour());
		case BLINKY:
			return new Ghost(location, behaviours.getBlinkyBehaviour(), this);
		case PINKY:
			return new Ghost(location, behaviours.getPinkyBehaviour(), this);
		case INKY:
			return new Ghost(location, behaviours.getInkyBehaviour(), this);
		case CLYDE:
			return new Ghost(location, behaviours.getClydeBehaviour(), this);
		}
		return null;
	}
};
