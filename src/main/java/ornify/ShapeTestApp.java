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
    shapeTestPanel.read("full_bird.map", true);
    shapeTestPanel.read("foot.map", false);
    shapeTestPanel.read("crown.map", false);
    shapeTestPanel.read("beak.map", false);
    shapeTestPanel.read("supercilium.map", false);
    shapeTestPanel.read("eyestripe.map", false);
//    shapeTestPanel.read("crown.map", false);
//    shapeTestPanel.read("crown.map", false);
//    shapeTestPanel.read("crown.map", false);
//    shapeTestPanel.read("crown.map", false);
//    shapeTestPanel.read("crown.map", false);
    
    this.getContentPane().add(shapeTestPanel.getView());
  }
  
  public static void main(String[] args)
  {
    JApplication app = new ShapeTestApp(args);
    invokeInEventDispatchThread(app);
  }
}
