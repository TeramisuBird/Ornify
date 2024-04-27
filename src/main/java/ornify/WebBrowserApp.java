package ornify;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLayeredPane;

import app.JApplication;

/**
 * This is a test to see if the web browser actually works.
 * 
 * @author Joseph Blethen
 * @version 1.0
 *
 */
public class WebBrowserApp extends JApplication
{
  private static final int WIDTH = 800;
  private static final int HEIGHT = 600;

  /**
   * Constructs a web browser
   * 
   * @param args
   *          The command line arguments
   */
  public WebBrowserApp(String[] args)
  {
    super(args, WIDTH, HEIGHT);
  }

  /**
   * Runs this program.
   * 
   * @param args
   *          The command line arguments.
   */
  public static void main(String[] args)
  {
    JApplication app = new WebBrowserApp(args);
    invokeInEventDispatchThread(app);
  }

  @Override
  public void init()
  {
    WebBrowser browser = new WebBrowser();
    JLayeredPane layeredPane = new JLayeredPane();
    layeredPane.setLayout(new BorderLayout());
    layeredPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    layeredPane.add(browser);
    super.setLayeredPane(layeredPane);

  }

}
