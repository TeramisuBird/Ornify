package ornify;

import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
//import java.util.ArrayList;
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
  private HashMap<String,Pair<Polygon, Integer>> colorIndex;

  public ShapeTestPanel()
  {
    this.jarFinder = ResourceFinder.createInstance(new Marker());
    this.reader = new PolygonReader(jarFinder);
    this.shapes = new HashMap<Polygon,String>();
    this.colorIndex = new HashMap<String, Pair<Polygon, Integer>>();
    
    this.getView().setSize(600, 600);
    this.getView().addMouseListener(this);
  }
  
  public void read(String name, boolean bigOutline)
  {
    Content content = new Content();
    Polygon poly = reader.read(name + ".map");
    
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
    
    this.colorIndex.put(name.toUpperCase(), new Pair<Polygon, Integer>(poly,0));
    this.add(content);
  }
  
//  private void redraw()
//  {
//    this.clear();
//    Content con = new Content();
//    String constReg1 = "FULL_BIRD";
//    String constReg2 = "EYEHOLE";
//    Polygon poly = this.colorIndex.get(constReg1).first;
//    
//    con.setShape(poly);
//    con.setColor(new Color(255, 0, 0));
//    con.setPaint(new Color(255, 255, 255));
//    this.add(con);
//    
//    for (String region : this.colorIndex.keySet())
//    {
//      if (!region.equals(constReg1) && !region.equals(constReg2))
//      {
//        con = new Content();
//        poly = this.colorIndex.get(region.toUpperCase()).first;
//        int index = this.colorIndex.get(region.toUpperCase()).second;
//        
//        con.setShape(poly);
//        con.setColor(new Color(0, 255, 0));
//        
//        if (index % 2 == 1)
//        {
//          con.setPaint(new Color(0, 0, 0));
//        }
//        else
//        {
//          con.setPaint(new Color(255, 255, 255));
//        }
//        this.add(con);
//      }
//    }
//    
//    con = new Content();
//    poly = this.colorIndex.get(constReg2).first;
//    con.setShape(poly);
//    con.setColor(new Color(255, 0, 0));
//    con.setPaint(new Color(255, 255, 255));
//    this.add(con);
//  }

  @Override
  public void mouseClicked(MouseEvent e)
  {
    int xPos = e.getX();
    int yPos = e.getY();
    Point mousePosition = new Point(xPos, yPos);
//    System.out.println(mousePosition);
    
    for (Polygon p : this.shapes.keySet())
    {
      if (p.contains(mousePosition))
      {
        String name = this.shapes.get(p);
        int oldIndex = this.colorIndex.get(name.toUpperCase()).second;
        Pair<Polygon, Integer> newPair = new Pair<>(p,oldIndex + 1);
        this.colorIndex.put(name.toUpperCase(), newPair);
//        System.out.println(name);
        
        Content con = new Content();
        con.setShape(p);
        con.setColor(new Color(0, 255, 0));
        if ((oldIndex + 1) % 2 == 1)
        {
          con.setPaint(new Color(0, 0, 0));
        }
        else
        {
          con.setPaint(new Color(255, 255, 255));
        }
        this.add(con);
      }
    }
  }

  @Override
  public void mousePressed(MouseEvent e)
  {
    
  }

  @Override
  public void mouseReleased(MouseEvent e)
  {
    
  }

  @Override
  public void mouseEntered(MouseEvent e)
  {
    
  }

  @Override
  public void mouseExited(MouseEvent e)
  {
    
  }
}
