import java.io.DataInputStream;
import java.io.IOException;

import lejos.nxt.LCD;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;
import tools.Car;
import tools.Instructions;

public class Robot {
	
	public static int speed;
	
	public static void main(String[] args) {
		LCD.drawString("Waiting for BT", 0, 0);
		NXTConnection connection = Bluetooth.waitForConnection();
		
		// Enter reading loop
		DataInputStream in = connection.openDataInputStream();
		int read;
		int line = 0;
		LCD.clear();
		try {
			while ((read = in.read()) != -1) {
				
				// Display received value
				if (line == 0)
					LCD.clear();
				LCD.drawInt(read, 0, line);
				line = (line + 1) % 7;
				switch (read) {
		    	case Instructions.DOT: break; // TODO
		    	case Instructions.LEFT: Car.forward(0, speed); break;
		    	case Instructions.RIGHT: Car.forward(speed, 0); break;
		    	case Instructions.START: Car.forward(speed, speed); break;
		    	case Instructions.STOP: Car.stop(); break;
		    	case Instructions.U_TURN: break; // TODO
		    	default: if (read <= 100 && read >= 0) speed = read; Car.forward(speed, speed);
		    	}
			}
		} catch (IOException e) {
			LCD.clearDisplay();
			LCD.drawString("I/O Error!", 0, 0);
			LCD.drawString(e.getMessage(), 0, 1);
			System.exit(1);
		}
		
		// Close connection
		try {
			in.close(); 
		} catch (IOException e) {
			// Ignore
		}
		connection.close();
	}
}
