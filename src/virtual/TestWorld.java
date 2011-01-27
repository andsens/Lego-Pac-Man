package virtual;

import java.awt.Dimension;
import javax.swing.*;
import javax.swing.Timer;

public class TestWorld {
	
	public static void main(String[] args) throws InterruptedException {
		
		int delay = 10; // 0.01s
		int displayDelay = 40; // 25img/s
		
		VirtualRobot robot = new VirtualRobot(delay, 0, 0);
		Display display = new Display(robot);
		
		/* timer initialization */
		Timer timer = new Timer(delay, robot);
		Timer displayTimer = new Timer(displayDelay, display);
		
		/* display initialization */
		JFrame frame = new JFrame("Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(500, 500));
	    frame.setLocation(100,500);
	    frame.setContentPane(display);
	    
	    /* start */
	    timer.start();
	    displayTimer.start();
	    frame.setVisible(true);
	    Thread.sleep(1000);
	    robot.setSpeed(10, 20);
	    Thread.sleep(2000);
	    robot.setSpeed(0, 0);
	    System.out.println(robot.getPosX() + " " + robot.getPosY());
	    timer.stop();
	    displayTimer.stop();
	}
	
}
