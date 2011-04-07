package pacman.behaviours.controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import pacman.world.World;
import pacman.world.maps.Direction;

public class KeyboardController implements Controller, KeyListener {
	
	private static final int keyPressTimeout = 200;
	
	private long resetNext = -1;

	private Direction nextHeading = Direction.NONE;
	public Direction getNextHeading() {
		if(System.currentTimeMillis()-resetNext > keyPressTimeout)
			nextHeading = Direction.NONE;
		return nextHeading;
	}

	
	protected boolean listenToInput = false;
	public void listen(boolean listenToInput) {
		this.listenToInput = listenToInput;
	}
	
	public void keyPressed(KeyEvent event) {
		if(!listenToInput)
			return;
		if(Direction.getKeyTranslation(event) != Direction.NONE) {
			resetNext = System.currentTimeMillis();
			nextHeading = Direction.getKeyTranslation(event);
		}
	}

	public void keyReleased(KeyEvent event) {
		if(!listenToInput)
			return;
		if(Direction.getKeyTranslation(event) != Direction.NONE)
			resetNext = System.currentTimeMillis();
	}

	public void keyTyped(KeyEvent event) {
	}
	
	public void reset() {
		nextHeading = Direction.NONE;
	}
	
	public void setWorld(World world) {}
}
