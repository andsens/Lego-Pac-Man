package pacman.world.maps;

import java.awt.Component;
import java.awt.Point;

import pacman.behaviours.factories.BehaviourFactory;
import pacman.world.Changeable;
import pacman.world.MovingEntity;
import pacman.world.Pacman;
import pacman.world.World;
import pacman.world.tiles.Tile;

public class MovingEntityMap extends Map<MovingEntity> implements Changeable  {
	
	private static final long serialVersionUID = 1270779768329741235L;
	
	Pacman pacman;
	
	BehaviourFactory behaviours;
	
	public MovingEntityMap(TypeMap map, BehaviourFactory behaviours) {
		super(map);
		this.behaviours = behaviours;
		populate(map);
	}
	
	protected void populateMap(TypeMap map) {
		Type[][] typeMap = map.getTypeMap();
		for(int x = 0; x < typeMap.length; x++) {
			for(int y = 0; y < typeMap[x].length; y++) {
				Coordinate coordinate = new Coordinate(x, y);
				coordinate.scale(Tile.width, Tile.height);
				MovingEntity entity = typeMap[x][y].createEntity(coordinate, behaviours);
				if(Pacman.class.isInstance(entity))
					if(pacman != null)
						throw new RuntimeException("There are two Pacmans on the map, only one is allowed.");
					else
						pacman = (Pacman) entity;
				if(entity == null)
					continue;
				add(entity);
			}
		}
		if(pacman == null)
			throw new RuntimeException("There is no Pacman on the map, there has to be exactly one.");
	}
	
	public void tick(World world) {
		for(Component entity : getComponents())
			((MovingEntity) entity).tick(world);
	}
	
	public Pacman getPacman() {
		return pacman;
	}
	
	public void reset() {
		for(Component entity : getComponents())
			((MovingEntity) entity).reset();
	}
}
