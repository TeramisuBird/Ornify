package ornify;

import java.awt.Polygon;
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
  public Polygon read(final String name)
  {
    Polygon poly = new Polygon();

    int points = 0;
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
        int x = Integer.valueOf(fields[1]);
        int y = Integer.valueOf(fields[2]);
        poly.addPoint(x, y);
        
        points++;
        line = br.readLine();
      }      
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    
    poly.npoints = points;
    return poly;
  }
}
