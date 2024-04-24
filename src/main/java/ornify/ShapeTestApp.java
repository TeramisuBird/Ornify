package ornify;

import app.JApplication;

public class ShapeTestApp extends JApplication
{
  
  private static final int WIDTH = 600;
  private static final int HEIGHT = 600;
  
  private ShapeTestPanel shapeTestPanel;

  public ShapeTestApp(String[] args)
  {
    super(args, WIDTH, HEIGHT);
  }
  
  @Override
  public void init()
  {
    this.shapeTestPanel = new ShapeTestPanel();
    shapeTestPanel.read("full_bird", true);
    shapeTestPanel.read("foot", false);
    shapeTestPanel.read("crown", false);
    shapeTestPanel.read("beak", false);
    shapeTestPanel.read("supercilium", false);
    shapeTestPanel.read("eyestripe", false);
    shapeTestPanel.read("auriculars", false);
    shapeTestPanel.read("throat", false);
    shapeTestPanel.read("breast", false);
    shapeTestPanel.read("coverts", false);
    shapeTestPanel.read("wing", false);
    shapeTestPanel.read("eyehole", true);
    
    this.getContentPane().add(shapeTestPanel.getView());
  }
  
  public static void main(String[] args)
  {
    JApplication app = new ShapeTestApp(args);
    invokeInEventDispatchThread(app);
  }
}
