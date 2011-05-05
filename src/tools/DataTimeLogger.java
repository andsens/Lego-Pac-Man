package tools;

import lejos.nxt.*;
import java.io.*;
/**
 * 
 * @author  Pierre Chatelain
 * 
 */
public class DataTimeLogger 
{
    private File f;
    private FileOutputStream fos;
    private long startTime;
    
    public DataTimeLogger (String fileName)
    {
        try
        {	        
            f = new File(fileName);
            if( ! f.exists() )
            {
                f.createNewFile();
            }
            else
            {
                f.delete();
                f.createNewFile();
            }
             
            fos = new  FileOutputStream(f);
        }
        catch(IOException e)
        {
            LCD.drawString(e.getMessage(),0,0);
            System.exit(0);
        }
        startTime = System.currentTimeMillis();
    }
	
    public void writeSample( int sample )
    {
		int time = (int) (System.currentTimeMillis() - startTime);
        Integer sampleInt = new Integer(time);
        String sampleString = sampleInt.toString();
        
        try
        {
            for(int i = 0; i < sampleString.length(); i++)
            {
                fos.write((byte) sampleString.charAt(i));
            }
            // Separate items with space
            fos.write((byte)(' '));
            
            sampleInt = new Integer(sample);
            sampleString = sampleInt.toString();
            
            for(int i = 0; i < sampleString.length(); i++)
            {
                fos.write((byte) sampleString.charAt(i));
            }
            // New line
            fos.write((byte)('\r'));
            fos.write((byte)('\n'));
            
        }
        catch(IOException e)
        {
            LCD.drawString(e.getMessage(),0,0);
            System.exit(0);
        }
    }
	
    public void close()
    {
        try
        {
            fos.close();
        }
        catch(IOException e)
        {
            LCD.drawString(e.getMessage(),0,0);
            System.exit(0);
        }		 
    }
    
    public void reset() {
    	startTime = System.currentTimeMillis();
    }
}