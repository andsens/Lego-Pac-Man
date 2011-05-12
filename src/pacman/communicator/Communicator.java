package pacman.communicator;

import javax.bluetooth.BluetoothConnectionException;

import pacman.world.MovingEntity;
import pacman.world.maps.Direction;

public class Communicator implements Runnable {
	
	public static enum Robot {
		BLINKY(1), PINKY(2), INKY(3), CLYDE(4), PACMAN(5);
		public int i;
		private Robot(int i) {
			this.i = i;
		}
		
		public static Robot get(String name) {
			name = name.toUpperCase();
			for(Robot robot : Robot.values())
				if(robot.toString().equals(name))
					return robot;
			return null;
		}
	};
	
	private static String[] robotMacs = {"", "", "", "", ""};
	
	private MovingEntity entity;
	private Robot robot;
	public Communicator(MovingEntity entity) {
		this.entity = entity;
		robot = Robot.get(entity.getName());
	}
	
	public BluetoothConnection connection;
	public void run() {
		try {
			connection = connect(robotMacs[robot.i]);
			connected = true;
		} catch (BluetoothConnectionException e) {
			System.err.println("Could not connect to "+robot+"." + e.getMessage());
			return;
		}
		while(connection.isAlive()) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
			Direction move = entity.getNextMove();
			if(move == null)
				continue;
			connection.sendMove(move);
			if(shutdown) {
				connection.close();
				return;
			}
		}
		connected = false;
		return;
	}
	
	private BluetoothConnection connect(String mac) throws BluetoothConnectionException {
		return null;
	}
	
	private boolean connected = false;
	public boolean isConnected() {
		return connected;
	}
	
	private boolean shutdown = false;
	public void shutdown() {
		shutdown = true;
	}

	public void ping() {
		// TODO Auto-generated method stub
	}
}
