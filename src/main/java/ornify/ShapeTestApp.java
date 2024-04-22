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
    shapeTestPanel.read("bird.map");
//    shapeTestPanel.read("bird2.map");
    
    this.getContentPane().add(shapeTestPanel.getView());
  }
  
  public static void main(String[] args)
  {
    JApplication app = new ShapeTestApp(args);
    invokeInEventDispatchThread(app);
  }
}
