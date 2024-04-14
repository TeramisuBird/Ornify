package ornify;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;

import app.JApplication;

public class BaseApplication extends JApplication implements ActionListener
{
  public static final int WIDTH = 600;
  public static final int HEIGHT = 600;
  
  public static final Color background_color = new Color(180, 250, 250);

  protected static final String ABOUT = "About";
  protected static final String LOAD = "Load";
  protected static final String START = "Start";
  protected static final String RETURN = "Return";
  protected static final String NEXT = "Next";

  public String[] userChoices;
  private JPanel curPanel;
  private int index;
  private ArrayList<JPanel> panels;
  
//  public String[] selections = {
//    "YEAR",
//    "SIZE",
//    "CROWN",
//    "SUPERCILIUM",
//    "EYESTRIPE",
//    "AURICULARS",
//    "BEAK_SHAPE",
//    "BEAK_LENGTH",
//    "BEAK_COLOR",
//    "THROAT",
//    "BREAST",
//    "COVERTS",
//    "WING",
//    "FOOT_SHAPE",
//    "FOOT_COLOR"
//  };

  public BaseApplication(String[] args)
  {
    super(args, WIDTH, HEIGHT);
    panels = new ArrayList<JPanel>();
    index = 0;
    userChoices = new String[15];
  }

  public void actionPerformed(ActionEvent evt)
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
  
  public void addChoice(final String choice, final int index)
  {
    userChoices[index] = choice;
  }
  
  public void dumpResults()
  {
   for (int i = 0; i < userChoices.length; i++)
   {
     System.out.printf("%d: %s", i, userChoices[i]);
   }
   System.out.println();
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
//    userChoices = new String[15];
  }
  
  /**
   * Method that controls the next button action.
   * 
   * @throws IOException
   */
  protected void handleNext()
  {
    if (index + 1 < panels.size())
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
    index = 0;
    
    curPanel = panels.get(index);
    curPanel.setVisible(true);
    panel.add(curPanel);
    panel.revalidate();
    panel.repaint();
  }

  @Override
  public void init()
  {
    // Add the panel to the main window
    JPanel panel = (JPanel) this.getContentPane();
    panel.setLayout(null);
    
    //  make panels via custom panel subclasses
    TitlePanel titlePanel = new TitlePanel("Ornify", this);
    CustomPanel sizePanel = new SizePanel("What is the size of this bird?", this);
    CustomPanel yearPanel = new YearPanel("During what time of the year did you see it?", this);
    CustomPanel footPanel = new FootPanel("What is the foot shape?", this);   
    CustomPanel beakPanel = new BeakPanel("What is the beak shape?", this);
    CustomPanel beakLenPanel = new BeakLenPanel("What is the beak length?", this);  
    CustomPanel colorPanel = new ColorPanel("What color was this bird?", this);
    ResultsPanel resultPanel = new ResultsPanel("Is this your bird?", this);
    
    //    add to panel list
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

  public static void main(String[] args)
  {
    JApplication app = new BaseApplication(args);
    invokeInEventDispatchThread(app);
  }

}
