package ornify;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;

import app.JApplication;

/**
 * Application Starting Point for Ornify
 * 
 * This work complies with the JMU Honor Code.
 */
public class BaseApplication extends JApplication implements ActionListener
{ 
  public static final int WIDTH = 800;
  public static final int HEIGHT = 600;

  public static final Color BACKGROUND_COLOR = new Color(180, 250, 250);

  protected static final String ABOUT = "About";
  protected static final String LOAD = "Load";
  protected static final String START = "Start";
  protected static final String RETURN = "Return";
  protected static final String NEXT = "Next";
  private static boolean isLastPanel = false;
  private JPanel curPanel;
  private ResultsPanel resultPanel;
  private int index;
  private ArrayList<JPanel> panels;

  /**
   * BaseApplication constructor.
   * 
   * @param args
   *          for command line args
   */
  public BaseApplication(final String[] args)
  {
    super(args, WIDTH, HEIGHT);
    panels = new ArrayList<JPanel>();
    index = 0;
  }

  /**
   * Perform some action based on an user event.
   * 
   * @param evt
   *          for Event to handle
   */
  public void actionPerformed(final ActionEvent evt)
  {
    switch (evt.getActionCommand())
    {
      case LOAD:
        break;
      case ABOUT:
        break;
      case START:
        handleStart();
        break;
      case RETURN:
        handleReturn();
        break;
      case NEXT:
        handleNext();
        break;
      default:
        break;
    }
  }

  /**
   * Method that controls the start button action.
   * 
   * @throws IOException
   */
  protected void handleStart()
  {
    JPanel panel = (JPanel) this.getContentPane();
    panel.remove(curPanel);
    index++;

    curPanel = panels.get(index);
    curPanel.setVisible(true);
    panel.add(curPanel);
    panel.revalidate();
    panel.repaint();
  }

  /**
   * Method that controls the next button action.
   * 
   * @throws IOException
   */
  protected void handleNext()
  {
    JPanel panel = (JPanel) this.getContentPane();
    panel.remove(curPanel);
    if (index < 7)
    {
      index++;
    }
    else
    {
      index = 0;
    }
    isLastPanel = (index == 7);
    curPanel = panels.get(index);
    curPanel.setVisible(true);
    panel.add(curPanel);
    panel.revalidate();
    panel.repaint();
  }

  /**
   * Method that controls the restart button action.
   * 
   * @throws IOException
   */
  public void handleRestart()
  {
    JPanel panel = (JPanel) this.getContentPane();
    panel.remove(curPanel);
    index = 0;
    isLastPanel = false;
    Model.selections[1] = "";
    Model.selections[7] = "";
    Model.endResult = new ArrayList<String>();
    curPanel = panels.get(index);
    curPanel.setVisible(true);
    panel.add(curPanel);
    panel.revalidate();
    panel.repaint();
  }

  /**
   * Method that controls preparing results for the results panel.
   * 
   * @throws IOException
   */
  public void handleResults()
  {
    resultPanel.buildResults();
    handleNext();
  }

  /**
   * Method that controls displaying the browser.
   * 
   * @return JPanel to show
   * @throws IOException
   */
  public JPanel handleBrowserDisplay()
  {
    return this.curPanel;
  }

  /**
   * Method that controls the return button action.
   * 
   * @throws IOException
   */
  public void handleReturn()
  {
    JPanel panel = (JPanel) this.getContentPane();
    panel.remove(curPanel);
    if (index != 0 && !isLastPanel)
    {
      index--;
    }
    if (isLastPanel)
    {
      isLastPanel = false;
    }
    curPanel = panels.get(index);
    curPanel.setVisible(true);
    panel.add(curPanel);
    panel.revalidate();
    panel.repaint();
  }

  /**
   * Method that initializes the application.
   */
  @Override public void init()
  {
    System.out.println("Entering init...");
    // Add the panel to the main window
    JPanel panel = (JPanel) this.getContentPane();
    panel.setLayout(null);

    // make panels via custom panel subclasses
    TitlePanel titlePanel = new TitlePanel("Ornify", this);
    CustomPanel sizePanel = new SizePanel("What is the size of this bird?", this);
    CustomPanel yearPanel = new YearPanel("During what time of the year did you see it?", this);
    CustomPanel footPanel = new FootPanel("What is the foot shape?", this);
    CustomPanel beakPanel = new BeakPanel("What is the beak shape?", this);
    CustomPanel beakLenPanel = new BeakLenPanel("What is the beak length?", this);
    CustomPanel colorPanel = new ColorPanel("What color was this bird?", this);
    resultPanel = new ResultsPanel("Is this your bird?", this);

    // add to panel list
    panels.add(titlePanel.getPanel());
    panels.add(sizePanel.getPanel());
    panels.add(yearPanel.getPanel());
    panels.add(footPanel.getPanel());
    panels.add(beakPanel.getPanel());
    panels.add(beakLenPanel.getPanel());
    panels.add(colorPanel.getPanel());
    panels.add(resultPanel.getPanel());

    // Add everything to the main panel
    this.curPanel = titlePanel.getPanel();
    panel.add(curPanel);
  }

  /**
   * main starting point.
   * 
   * @param args
   *          for command line args
   */
  public static void main(final String[] args)
  {
    JApplication app = new BaseApplication(args);
    invokeInEventDispatchThread(app);
  }

}
