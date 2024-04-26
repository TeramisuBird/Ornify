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
    content.setColor(new Color(180, 167, 214));
    content.setPaint(new Color(172,91,91));
    if (!bigOutline)
    {
      shapes.put(poly, name);
    }
    
    colorIndex.put(name, new Pair<Polygon, Integer>(poly,0));
    this.add(content);
  }
  
  private void redraw()
  {
    this.clear();
    Content con = new Content();
    String constReg1 = "full_bird";
    String constReg2 = "eyehole";
    Polygon poly = this.colorIndex.get(constReg1).first;
    
    con.setShape(poly);
    con.setColor(new Color(180, 167, 214));
    con.setPaint(new Color(172,91,91));
    this.add(con);
    
    con = new Content();
    poly = this.colorIndex.get(constReg2).first;
    con.setShape(poly);
    con.setColor(new Color(180, 167, 214));
    con.setPaint(new Color(172,91,91));
    this.add(con);
    
    for (String region : this.colorIndex.keySet())
    {
      if (!region.equals(constReg1) && !region.equals(constReg2))
      {
        con = new Content();
        poly = this.colorIndex.get(region).first;
        int index = this.colorIndex.get(region).second;
        
        con.setShape(poly);
        con.setColor(new Color(180, 167, 214));
        Color newPaint = null;
        
        switch (region)
        {
          case "crown":
            if (index >= Model.crownColor.size())
            {
              index = 0;
              colorIndex.put(region, new Pair<Polygon, Integer>(poly, index));
            }
            
            newPaint = Model.crownColor.get(index).second;
            break;
          case "supercilium":
            if (index >= Model.superColor.size())
            {
              index = 0;
              colorIndex.put(region, new Pair<Polygon, Integer>(poly, index));
            }
            
            newPaint = Model.superColor.get(index).second;
            break;
          case "eyestripe":
            if (index >= Model.eyestripeColor.size())
            {
              index = 0;
              colorIndex.put(region, new Pair<Polygon, Integer>(poly, index));
            }
            
            newPaint = Model.eyestripeColor.get(index).second;
            break;
          case "auriculars":
            if (index >= Model.auricColor.size())
            {
              index = 0;
              colorIndex.put(region, new Pair<Polygon, Integer>(poly, index));
            }
            
            newPaint = Model.auricColor.get(index).second;
            break;
          case "beak":
            if (index >= Model.beakColor.size())
            {
              index = 0;
              colorIndex.put(region, new Pair<Polygon, Integer>(poly, index));
            }
            
            newPaint = Model.beakColor.get(index).second;
            break;
          case "throat":
            if (index >= Model.throatColor.size())
            {
              index = 0;
              colorIndex.put(region, new Pair<Polygon, Integer>(poly, index));
            }
            
            newPaint = Model.throatColor.get(index).second;
            break;
          case "breast":
            if (index >= Model.breastColor.size())
            {
              index = 0;
              colorIndex.put(region, new Pair<Polygon, Integer>(poly, index));
            }
            
            newPaint = Model.breastColor.get(index).second;
            break;
          case "coverts":
            if (index >= Model.covertColor.size())
            {
              index = 0;
              colorIndex.put(region, new Pair<Polygon, Integer>(poly, index));
            }
            
            newPaint = Model.covertColor.get(index).second;
            break;
          case "wing":
            if (index >= Model.wingColor.size())
            {
              index = 0;
              colorIndex.put(region, new Pair<Polygon, Integer>(poly, index));
            }
            
            newPaint = Model.wingColor.get(index).second;
            break;
          case "foot":
            if (index >= Model.footColor.size())
            {
              index = 0;
              colorIndex.put(region, new Pair<Polygon, Integer>(poly, index));
            }
            
            newPaint = Model.footColor.get(index).second;
            break;
          default:
            System.out.println("Unknown Bird Region - SHOULD NOT REACH");
            break;
        }
        
        if (newPaint == null)
        {
          newPaint = new Color(172,91,91);
        }
        con.setPaint(newPaint);
        this.add(con);
      }
    }
  }

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
        int oldIndex = this.colorIndex.get(name).second;
        Pair<Polygon, Integer> newPair = new Pair<>(p,oldIndex + 1);
        this.colorIndex.put(name, newPair);

        redraw();
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
