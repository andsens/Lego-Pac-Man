package pacman.world.maps;

import java.util.HashMap;
import java.util.List;

import pacman.behaviours.factories.BehaviourFactory;
import pacman.communicator.Communicator;
import pacman.world.Changeable;
import pacman.world.MovingEntity;
import pacman.world.World;

public class RobotMap extends Map<MovingEntity> implements Changeable  {
	
	private static final long serialVersionUID = 1270779768329741235L;
	
	private HashMap<MovingEntity, Communicator> robots;
	private List<MovingEntity> entities;
	
	BehaviourFactory behaviours;
	
	public RobotMap(TypeMap map, List<MovingEntity> entities) {
		super(map);
		this.entities = entities;
		robots = new HashMap<MovingEntity, Communicator>();
		populate(map);
	}
	
	protected void populateMap(TypeMap map) {
		for(MovingEntity entity : entities) {
			Communicator robot = new Communicator(entity);
			robot.run();
			robots.put(entity, robot);
		}
	}
	
	public void tick(long count) {
		for(Communicator robot : robots.values())
			robot.ping();
	}
	
	public void setWorld(World world) {
	}
	
	public void reset() {
	}
}
