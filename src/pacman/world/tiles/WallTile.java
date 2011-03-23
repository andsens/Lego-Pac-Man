package pacman.world.tiles;

import java.awt.Point;

import pacman.world.maps.Type;

public class WallTile extends Tile {
	
	private static final long serialVersionUID = 7555949431966646298L;
	
	public WallTile(Point location, Type type) {
		super(location);
		switch (type) {
		case UP:
			spriteTile = new Point(30, 6);
			break;
		case LEFT:
			spriteTile = new Point(9, 7);
			break;
		case RIGHT:
			spriteTile = new Point(8, 7);
			break;
		case UPLFT:
			spriteTile = new Point(7, 7);
			break;
		case DWNLFT:
			spriteTile = new Point(25, 7);
			break;
		case DWNRGT:
			spriteTile = new Point(24, 7);
			break;
		case UPRGT:
			spriteTile = new Point(22, 7);
			break;
		case DOWN:
			spriteTile = new Point(4, 7);
			break;
		case UP_DBL:
			spriteTile = new Point(28, 6);
			break;
		case DWN_DBL:
			spriteTile = new Point(26, 6);
			break;
		case LFT_DBL:
			spriteTile = new Point(18, 6);
			break;
		case RGT_DBL:
			spriteTile = new Point(19, 6);
			break;
		case UPLFT_DBL:
			spriteTile = new Point(17, 6);
			break;
		case DWNLFT_DBL:
			spriteTile = new Point(21, 6);
			break;
		case UPRGT_DBL:
			spriteTile = new Point(16, 6);
			break;
		case DWNRGT_DBL:
			spriteTile = new Point(20, 6);
			break;
		case UPLFT_DBL_R:
			spriteTile = new Point(13, 7);
			break;
		case DWNLFT_DBL_R:
			spriteTile = new Point(15, 7);
			break;
		case UPRGT_DBL_R:
			spriteTile = new Point(12, 7);
			break;
		case DWNRGT_DBL_R:
			spriteTile = new Point(14, 7);
			break;
		case UP_STOP_RGT:
			spriteTile = new Point(17, 7);
			break;
		case UP_STOP_LFT:
			spriteTile = new Point(16, 7);
			break;
		case SPLT_DWN_RGT:
			spriteTile = new Point(26, 7);
			break;
		case SPLT_DWN_LFT:
			spriteTile = new Point(27, 7);
			break;
		case SPLT_LFT_UP:
			spriteTile = new Point(22, 6);
			break;
		case SPLT_LFT_DWN:
			spriteTile = new Point(24, 6);
			break;
		case SPLT_RGT_UP:
			spriteTile = new Point(23, 6);
			break;
		case SPLT_RGT_DWN:
			spriteTile = new Point(25, 6);
			break;
		default:
			spriteTile = new Point(8, 3);
			break;
		}
	}

}
