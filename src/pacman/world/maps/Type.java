package pacman.world.maps;

import pacman.behaviours.factories.BehaviourFactory;
import pacman.world.Dot;
import pacman.world.Energizer;
import pacman.world.Ghost;
import pacman.world.MovingEntity;
import pacman.world.Pacman;
import pacman.world.tiles.EmptyTile;
import pacman.world.tiles.GhostHouseTile;
import pacman.world.tiles.NavigableTile;
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
	OOB('o'), EMPTY('e'), GHOSTHOUSE('g'), BARRIER('G'),
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
	
	public Tile createTile(Coordinate coordinate) {
		switch(this) {
		case OOB:
			return new EmptyTile(coordinate);
		case EMPTY:
		case DOT:
		case ENERGIZER:
		case PACMAN:
		case BLINKY:
			return new NavigableTile(coordinate);
		case BARRIER:
			return new GhostHouseTile(coordinate, true);
		case GHOSTHOUSE:
		case INKY:
		case PINKY:
		case CLYDE:
			return new GhostHouseTile(coordinate, false);
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
			return new WallTile(coordinate, this);
		}
		return null;
	}
	
	public Dot createDot(Coordinate coordinate) {
		switch(this) {
		case DOT:
			return new Dot(coordinate);
		case ENERGIZER:
			return new Energizer(coordinate);
		}
		return null;
	}
	
	public MovingEntity createEntity(Coordinate coordinate, BehaviourFactory behaviours) {
		switch(this) {
		case PACMAN:
			return new Pacman(coordinate, behaviours.getPacmanBehaviour());
		case BLINKY:
			return new Ghost(coordinate, behaviours.getBlinkyBehaviour(), this);
		case PINKY:
			return new Ghost(coordinate, behaviours.getPinkyBehaviour(), this);
		case INKY:
			return new Ghost(coordinate, behaviours.getInkyBehaviour(), this);
		case CLYDE:
			return new Ghost(coordinate, behaviours.getClydeBehaviour(), this);
		}
		return null;
	}
};
