package tools;

import lejos.nxt.*;
import java.io.*;
/**
 * A simple data logger to sample a sequence
 * of integer values in a flash file. The
 * file is in text format with
 * comma's between sample values and 
 * new lines inserted to obtain a fixed number
 * of sample values per line.
 * 
 * After use the flash file can be transfered to a
 * PC by means of the tool nxjbrowse.
 * 
 * @author  Ole Caprani
 * @version 2.09.08
 */
public class DataLogger 
{
    private File f;
    private FileOutputStream fos;
    private int itemsOnThisLine = 0; 
    private int itemsPerLine = 20;
    
    public DataLogger (String fileName)
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
    }
	
    public void writeSample( int sample )
    {
		
        Integer sampleInt = new Integer(sample);
        String sampleString = sampleInt.toString();

        try
        {
            for(int i=0; i<sampleString.length(); i++)
            {
                fos.write((byte) sampleString.charAt(i));
            }
            // Separate items with comma
            fos.write((byte)(','));
	    
	        itemsOnThisLine = itemsOnThisLine + 1;
	        if ( itemsOnThisLine  == itemsPerLine )
	        {
                // New line
                fos.write((byte)('\r'));
                fos.write((byte)('\n'));
				
                itemsOnThisLine = 0;
            }
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
}