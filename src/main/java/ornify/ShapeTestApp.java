package ornify;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.util.ArrayList;

import javax.swing.JPanel;

import app.JApplication;
import io.ResourceFinder;
import resources.Marker;

public class ShapeTestApp extends JApplication
{
  
  private static final int WIDTH = 600;
  private static final int HEIGHT = 600;
  
  public JPanel panel;
  public ResourceFinder jarFinder;
  public Shape outline;

  public ShapeTestApp(String[] args)
  {
    super(args, WIDTH, HEIGHT);
    
    this.jarFinder = ResourceFinder.createInstance(new Marker());
  }
  
  @Override
  public void init() {
    PolygonReader pr = new PolygonReader(jarFinder);
    
    this.outline = pr.read("bird.map");
    

    this.panel = new ShapeTestPanel(outline);
    
//    this.panel.paintComponents((Graphics2D) outline);
    
    this.panel.setVisible(true);
  }
  
  public static void main(String[] args)
  {
    JApplication app = new ShapeTestApp(args);
    invokeInEventDispatchThread(app);
  }
}
