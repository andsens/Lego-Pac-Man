package virtual;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Point;

/**
 * 
 * @author Pierre
 * @version 0.1
 *
 * A very simple virtual robot that computes its position according to its speed.
 * 
 */
public class VirtualRobot implements ActionListener {
	
	/** Robot x-axis position, in mpx */
	private int posX;
	/** Robot y-axis position, in mpx */
	private int posY;
	/** Robot x-axis speed, in mpx/s */
	private int speedX;
	/** Robot y-axis speed, in mpx/s */
	private int speedY;
	/** Robot update delay, in ms */
	private int delay;

	/** Creates a new VirtualRobot with the specified delay, and initial position and speed set to 0 */
	public VirtualRobot(int delay) {
		posX = 0;
		posY = 0;
		speedX = 0;
		speedY = 0;
		this.delay = delay;
	}
	
	/** Creates a new VirtualRobot with the specified delay and position, and initial speed set to 0 */
	public VirtualRobot(int delay, int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		this.speedX = 0;
		this.speedY = 0;
		this.delay = delay;
	}
	
	/** 
	 * 
	 * @return the robot's x-axis position, in px
	 */
	public synchronized int getPosX() {
		return posX / 1000;
	}
	
	/** 
	 * 
	 * @return the robot's y-axis position, in px
	 */
	public synchronized int getPosY() {
		return posY / 1000;
	}
	
	/**
	 *  
	 * @return the robot's position, with both components in px
	 */
	public synchronized Point getPosition() {
		return new Point(posX / 1000, posY / 1000);
	}

	/**
	 * 
	 * @param speedX : the new x-axis speed, in px/s
	 */
	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}
	
	/**
	 * 
	 * @param speedY : the new y-axis speed, in px/s
	 */
	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}
	
	/**
	 * 
	 * @param speedX : the new x-axis speed, in px/s
	 * @param speedY : the new y-axis speed, in px/s
	 */
	public void setSpeed(int speedX, int speedY) {
		this.speedX = speedX;
		this.speedY = speedY;
	}
	
	@Override
	public synchronized void actionPerformed(ActionEvent e) {
		posX += speedX * delay;
		posY += speedY * delay;
	}
	
}
