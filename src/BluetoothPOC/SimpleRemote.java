package BluetoothPOC;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JFrame;
import lejos.pc.comm.NXTCommException;

/** Test class using the keyboard to control a remote NXT brick.
 * 
 * @author Pierre
 *
 */
public class SimpleRemote extends JFrame implements KeyListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int speed = 0;
	public static boolean rightKey = false;
	public static boolean leftKey = false;
	public static boolean upKey = false;
	public static boolean downKey = false;
	public static boolean stop = false;
	public static InstructionSender sender;
	
	public SimpleRemote() {
		try {
			sender = new InstructionSender("INKY");
		} catch (NXTCommException e) {
			e.printStackTrace();
			System.exit(1);
		}
		this.addKeyListener(this);
		this.setSize(100, 100);
		this.setFocusable(true);
		this.setVisible(true);
		this.requestFocus();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("Pressed "+e.getKeyCode());
		try {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				sender.start();
				break;
			case KeyEvent.VK_RIGHT: 
				sender.turnRight();
				break;
			case KeyEvent.VK_LEFT:
				sender.turnLeft();
				break;
			case KeyEvent.VK_ESCAPE:
				sender.stop();
				sender.close();
				System.exit(0); break;
			default: break;
		}
		} catch (IOException e1) {
			e1.printStackTrace();
			System.exit(1);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}
