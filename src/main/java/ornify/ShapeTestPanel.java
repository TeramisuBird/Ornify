package ornify;

import java.awt.Color;
import java.awt.Polygon;

import io.ResourceFinder;
import resources.Marker;
import visual.Visualization;
import visual.statik.described.Content;

public class ShapeTestPanel extends Visualization
{
  private PolygonReader reader;
  private ResourceFinder jarFinder;

  public ShapeTestPanel()
  {
    this.jarFinder = ResourceFinder.createInstance(new Marker());
    this.reader = new PolygonReader(jarFinder);
    
    this.getView().setSize(600, 600);
  }
  
  public void read(String name, boolean bigOutline)
  {
    Content content = new Content();
    Polygon poly = reader.read(name);
    
    content.setShape(poly);
    if (bigOutline)
    {
      content.setColor(new Color(255, 0, 0));
      content.setPaint(new Color(255, 255, 255));
    }
    else
    {
      content.setColor(new Color(0, 255, 0));
      content.setPaint(new Color(255, 255, 255));
    }
    
    this.add(content);
  }
}
