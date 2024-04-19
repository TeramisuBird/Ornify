package ornify;

import java.awt.Shape;
import java.awt.geom.Path2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import io.ResourceFinder;

/**
 * Polygon reader.
 * 
 * This work complies with the JMU Honor Code.
 * 
 * @author Grayson Diehl
 */
public class PolygonReader
{
  private ResourceFinder finder;
  
  /**
   * basic constructor.
   */
  public PolygonReader()
  {
    this.finder = null;
  }
  
  /**
   * full constructor.
   * 
   * @param finder for Resource Finder used (can be null)
   */
  public PolygonReader(final ResourceFinder finder)
  {
    this.finder = finder;
  }
  
  /**
   * read a shape from a stream.
   * 
   * @param name for name of stream
   * @return shape read from stream
   */
  public Shape read(final String name)
  {
    Path2D.Double sh = new Path2D.Double();
    BufferedReader br = null;
    
    if (this.finder == null)
    {
      try
      {
        FileReader fr = new FileReader(name);
        br = new BufferedReader(fr);
      }
      catch (FileNotFoundException e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      InputStream is = this.finder.findInputStream(name);
      br = new BufferedReader(new InputStreamReader(is));
    }
    
    try
    {
      String line = br.readLine();
      while (line != null)
      {
        String[] fields = line.split(",");
        double x = Integer.valueOf(fields[1]);
        double y = Integer.valueOf(fields[2]);
        if (fields[0].equals("4"))
        {
          sh.moveTo(x, y);
        }
        else
        {
          sh.lineTo(x, y);
        }
        
        line = br.readLine();
      }
      
      sh.closePath();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    
    return sh;
  }
}
