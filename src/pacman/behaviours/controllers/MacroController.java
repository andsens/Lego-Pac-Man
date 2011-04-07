package pacman.behaviours.controllers;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import pacman.world.World;
import pacman.world.maps.Direction;

public class MacroController implements Controller {
	
	private int currentLevel;
	private HashMap<Long, Direction> macro;
	private Direction nextHeading;
	public Direction getNextHeading() {
		int level = world.getLevel();
		if(level != currentLevel) {
			try {
				FileReader file = new FileReader(new File("macros/level"+level+".txt"));
				macro = new HashMap<Long, Direction>();
				StringBuilder line = new StringBuilder();
				int lineNumber = 0;
				int charint;
				char character;
				while ((charint = file.read()) != -1) {
					character = (char) charint;
					if(character == '\n') {
						String lineString = line.toString();
						if(!lineString.matches("[0-9]+ [A-Z]+")) {
							System.err.println("Could not match line number "+lineNumber);
							System.out.println(lineString);
							System.exit(1);
						}
						String[] tokens = line.toString().split(" ");
						Direction direction = Direction.parseDirection(tokens[1]);
						if(direction == null) {
							System.err.println("Could not match "+tokens[1]+" on line number "+lineNumber);
							System.exit(1);
						}
						macro.put(Long.parseLong(tokens[0]), direction);
						line.delete(0, line.length());
						lineNumber++;
					} else {
						line.append(character);
					}
				}
				file.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		nextHeading = Direction.NONE;
		long tickCount = world.getTickCount();
		for(Long tick : macro.keySet())
			if(tick.longValue() == tickCount)
				nextHeading = macro.get(tick);
		return nextHeading;
	}
	
	public void reset() {
		
	}
	
	public void listen(boolean listenToInput) {
		
	}
	
	private World world;
	public void setWorld(World world) {
		this.world = world;
	}
}
