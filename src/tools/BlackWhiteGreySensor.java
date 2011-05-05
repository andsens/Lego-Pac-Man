package tools;

import lejos.nxt.*;

public class BlackWhiteGreySensor {

   private LightSensor ls; 
   private int blackLightValue;
   private int greyLightValue;
   private int whiteLightValue;
   private int blackGreyThreshold;
   private int whiteGreyThreshold;
   private DataTimeLogger data;

   public BlackWhiteGreySensor(SensorPort p)
   {
	   ls = new LightSensor(p); 
	   // Use the light sensor as a reflection sensor
	   ls.setFloodlight(true);
	   data = new DataTimeLogger("lightValues.txt");
   }

   private int read(String color){
	   
	   int lightValue=0;
	   
	   while (Button.ENTER.isPressed());
	   
	   LCD.clear();
	   LCD.drawString("Press ENTER", 0, 0);
	   LCD.drawString("to calibrate", 0, 1);
	   LCD.drawString(color, 0, 2);
	   while( !Button.ENTER.isPressed() ){
	      lightValue = ls.getLightValue();
	      LCD.drawInt(lightValue, 4, 10, 2);
	      LCD.refresh();
	   }
	   return lightValue;
   }
   
   public void calibrate()
   {
	   blackLightValue = read("black");
	   greyLightValue = read("grey");
	   whiteLightValue = read("white");
	   // The threshold is calculated as the median between 
	   // the two readings over the two types of surfaces
	   if ((blackLightValue > greyLightValue) || (greyLightValue > whiteLightValue)) {
		   System.err.println("Color information incoherent. Quitting.");
		   System.exit(1);
	   }
	   blackGreyThreshold = (blackLightValue+greyLightValue)/2;
	   whiteGreyThreshold = (whiteLightValue+greyLightValue)/2;
   }
   
   public boolean black() {
           return (ls.getLightValue() < blackGreyThreshold);
   }
   
   public int getBlackLightValue() {
	   return blackLightValue;
   } 
   
   public boolean grey() {
	   int value = ls.getLightValue();
       return (value > blackGreyThreshold) && (value < whiteGreyThreshold);
   }

   public int getGreyLightValue() {
	   return greyLightValue;
   } 
   
   public boolean white() {
	   return (ls.getLightValue()> whiteGreyThreshold);
   }
   
   public int getWhiteLightValue() {
	   return whiteLightValue;
   }
   
   public int getBlackGreyThreshold() {
	   return blackGreyThreshold;
   }
   
   public int getWhiteGreyThreshold() {
	   return whiteGreyThreshold;
   }
   
   public Color getColor() {
	   int value = ls.getLightValue();
	   data.writeSample(value);
	   if (value < blackGreyThreshold)
		   return Color.BLACK;
	   if (value > whiteGreyThreshold)
		   return Color.WHITE;
	   return Color.GREY;
   }
   
   public int light() {
 	   return ls.getLightValue();
   }
   
   public void close() {
	   data.close();
   }
   
   public void reset() {
	   data.reset();
   }
}