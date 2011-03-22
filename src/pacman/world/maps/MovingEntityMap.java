package pacman.world.maps;

import java.awt.Component;
import java.util.HashMap;

import pacman.behaviours.factories.BehaviourFactory;
import pacman.world.Changeable;
import pacman.world.MovingEntity;
import pacman.world.World;
import pacman.world.tiles.Tile;

public class MovingEntityMap extends Map<MovingEntity> implements Changeable  {
	
	private static final long serialVersionUID = 1270779768329741235L;
	
	private HashMap<Type, MovingEntity> entities;
	
	BehaviourFactory behaviours;
	
	public MovingEntityMap(TypeMap map, BehaviourFactory behaviours) {
		super(map);
		this.behaviours = behaviours;
		entities = new HashMap<Type, MovingEntity>();
		populate(map);
	}
	
	protected void populateMap(TypeMap map) {
		Type[][] typeMap = map.getTypeMap();
		for(int x = 0; x < typeMap.length; x++) {
			for(int y = 0; y < typeMap[x].length; y++) {
				if(entities.containsKey(typeMap[x][y]))
					throw new RuntimeException("There are two "+typeMap[x][y]+" on the map, only one is allowed.");
				Coordinate coordinate = new Coordinate(x, y);
				coordinate.scale(Tile.width, Tile.height);
				MovingEntity entity = typeMap[x][y].createEntity(coordinate, behaviours);
				if(entity == null)
					continue;
				entities.put(typeMap[x][y], entity);
				add(entity);
			}
		}
	}
	
	public void tick(World world) {
		for(Component entity : getComponents())
			((MovingEntity) entity).tick(world);
	}
	
	public MovingEntity get(Type type) {
		return entities.get(type);
	}
	
	public void reset() {
		for(Component entity : getComponents())
			((MovingEntity) entity).reset();
	}
}
