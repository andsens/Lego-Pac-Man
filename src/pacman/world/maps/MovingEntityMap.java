package pacman.world.maps;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pacman.behaviours.factories.BehaviourFactory;
import pacman.world.Changeable;
import pacman.world.Ghost;
import pacman.world.MovingEntity;
import pacman.world.Pacman;
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
				Point location = new Point(x*Tile.width, y*Tile.height);
				MovingEntity entity = typeMap[x][y].createEntity(location, behaviours);
				if(entity == null)
					continue;
				entities.put(typeMap[x][y], entity);
				add(entity);
			}
		}
	}
	
	public void tick(long count) {
		for(MovingEntity entity : entities.values())
			entity.tick(count);
	}
	
	public void setWorld(World world) {
		for(MovingEntity entity : entities.values())
			entity.setWorld(world);
	}
	
	public MovingEntity get(Type type) {
		return entities.get(type);
	}
	
	public List<Ghost> getGhosts() {
		List<Ghost> ghosts = new ArrayList<Ghost>();
		for(MovingEntity ghost : entities.values())
			if(!Pacman.class.isInstance(ghost))
				ghosts.add((Ghost) ghost);
		return ghosts;
	}
	
	public void frightenGhosts() {
		for(Type entityType : entities.keySet())
			if(entityType != Type.PACMAN)
				((Ghost) entities.get(entityType)).frighten();
	}
	
	public void reset() {
		for(MovingEntity entity : entities.values())
			entity.reset();
	}
}
