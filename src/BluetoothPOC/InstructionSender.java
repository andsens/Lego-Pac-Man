package BluetoothPOC;

import java.io.IOException;
import java.io.OutputStream;

import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTCommException;
import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTInfo;

/** Class implementing a bluetooth remote, that can send instructions to a specific NXT brick.
 * 
 * @author Pierre
 *
 */
public class InstructionSender {
	
	private OutputStream out;
	
	public InstructionSender() {
	}
	
	public InstructionSender(OutputStream out) {
		this.out = out;
	}
	
	/** Creates a new InstructionSender, searches for an NXT brick with the specified name, and opens an output stream towards this NXT.
	 *  
	 * @param name : the name of the NXT brick
	 * @throws NXTCommException
	 */
	public InstructionSender(String name) throws NXTCommException {
		NXTComm comm = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);
		NXTInfo[] info = comm.search(name, NXTCommFactory.BLUETOOTH);
		comm.open(info[0]);
		this.out = comm.getOutputStream();
	}
	
	public void setOutputStream(OutputStream out) {
		this.out = out;
	}
	
	public OutputStream getOutputStream() {
		return out;
	}
	
	public void setReceiver(String name) throws IOException, NXTCommException {
		if (out != null)
			out.close();
		NXTComm comm = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);
		NXTInfo[] info = comm.search(name, NXTCommFactory.BLUETOOTH);
		comm.open(info[0]);
		this.out = comm.getOutputStream();
	}
	
	public void setSpeed(int speed) throws IOException {
		if (speed > 100) {
			System.err.println("Warning: speed value out of bounds - set to 100");
			out.write(100);
		} else if (speed < 0) {
			System.err.println("Warning: speed value out of bounds - set to 0");
			out.write(100);
		} else {
			out.write(speed);
		}
		out.flush();
	}
	
	public void start() throws IOException {
		out.write(Instructions.START);
		out.flush();
	}
	
	public void stop() throws IOException {
		out.write(Instructions.STOP);
		out.flush();
	}
	
	public void turnRight() throws IOException {
		out.write(Instructions.RIGHT);
		out.flush();
	}
	
	public void turnLeft() throws IOException {
		out.write(Instructions.LEFT);
		out.flush();
	}
	
	public void notifyDot() throws IOException {
		out.write(Instructions.DOT);
		out.flush();
	}
	
	public void makeUTurn() throws IOException {
		out.write(Instructions.U_TURN);
		out.flush();
	}
	
	public void close() throws IOException {
		out.close();
	}
}
