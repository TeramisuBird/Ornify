package ornify;

import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

import io.ResourceFinder;
import resources.Marker;
import visual.Visualization;
import visual.statik.described.Content;

public class ShapeTestPanel extends Visualization implements MouseListener
{
  private PolygonReader reader;
  private ResourceFinder jarFinder;
  private HashMap<Polygon,String> shapes;

  public ShapeTestPanel()
  {
    this.jarFinder = ResourceFinder.createInstance(new Marker());
    this.reader = new PolygonReader(jarFinder);
    this.shapes = new HashMap<Polygon,String>();
    
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
      shapes.put(poly, name);
    }
    
    this.add(content);
  }

  @Override
  public void mouseClicked(MouseEvent e)
  {
    int xPos = e.getX();
    int yPos = e.getY();
    Point mousePosition = new Point(xPos, yPos);
    
    for (Polygon p : this.shapes.keySet())
    {
      if (p.contains(mousePosition))
      {
        System.out.println(this.shapes.get(p));
      }
      else
      {
        System.out.println("not contained");
      }
    }
  }

  @Override
  public void mousePressed(MouseEvent e)
  {
    System.out.println("Pressed");
  }

  @Override
  public void mouseReleased(MouseEvent e)
  {
    System.out.println("Released");
  }

  @Override
  public void mouseEntered(MouseEvent e)
  {
    System.out.println("Entered");
  }

  @Override
  public void mouseExited(MouseEvent e)
  {
    System.out.println("Exited");
  }
  
  
}
