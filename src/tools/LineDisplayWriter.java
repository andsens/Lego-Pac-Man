package tools;
import lejos.nxt.LCD;


public class LineDisplayWriter {
	
	public static int lineLength = 17;
	
	/**
	 * Write one line to the display and refreshes it.
	 * @param string
	 * @param line
	 */
	public static void writeLine(String string, int line) {
		addLine(string, line);
		refresh();
	}
	
	/**
	 * You will need to call refresh in order
	 * for the line to show up on the display.
	 * @param string
	 * @param line The line to write to. Between 1 and 7.
	 */
	public static void addLine(String string, int line) {
		if(string.length() > lineLength)
			string = string.substring(0, lineLength);
		else if(string.length() < lineLength)
			string = string + repeatString(" ", lineLength - string.length());
		LCD.drawString(string, 0, line);
	}
	
	private static String repeatString(String string, int times) {
		String repeatedString = "";
		for(int i = 1; i <= times; i++)
			repeatedString = repeatedString + string;
		return repeatedString;
	}
	
	public static void refresh() {
		LCD.refresh();
	}
}
