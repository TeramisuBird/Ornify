package ornify;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.JApplication;

public class BaseApplication extends JApplication implements ActionListener
{
  public static final int WIDTH = 600;
  public static final int HEIGHT = 600;

  protected static final String ABOUT = "About";
  protected static final String LOAD = "Load";
  protected static final String START = "Start";
  protected static final String RETURN = "Return";
  protected static final String NEXT = "Next";

  public HashMap<String, String> userChoices;
  private JPanel curPanel;
  private int index;
  private ArrayList<JPanel> panels;

  public BaseApplication(String[] args)
  {
    super(args, WIDTH, HEIGHT);
    panels = new ArrayList<JPanel>();
    index = 0;
    userChoices = new HashMap<String, String>();
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
  
  public void addChoice(final String question, final String choice)
  {
    userChoices.put(question, choice);
    for(String key : userChoices.keySet())
    {
      System.out.println(key + " " + userChoices.get(key));
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
    
    System.out.println("Next");
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

    JLabel title = new JLabel("Ornify");
    title.setFont(new Font("Verdana", Font.BOLD, 30));

    title.setBounds((WIDTH / 2) - 50, 50, 200, 100);

    JPanel titlePanel = new JPanel();
    titlePanel.setLayout(null);
    titlePanel.setBounds(0, 0, WIDTH, HEIGHT);

    JButton startButton = new JButton(START);
    startButton.setBounds((WIDTH / 2) - 50, (HEIGHT / 2) + 200, 100, 50);
    startButton.addActionListener(this);

    // Add to the title panel
    titlePanel.add(title);
    titlePanel.add(startButton);
    titlePanel.setVisible(true);
    
    panels.add(titlePanel);
    
//  make panels via custom panel subclasses
    CustomPanel sizePanel = new SizePanel("What is the size of this bird?", this);
    CustomPanel yearPanel = new YearPanel("During what time of the year did you see it?", this);
    CustomPanel footPanel = new FootPanel("What is the foot shape?", this);   
    CustomPanel beakPanel = new BeakPanel("What is the beak shape?", this);  
    CustomPanel colorPanel = new ColorPanel("What color was this bird?", this);
    ResultsPanel resultPanel = new ResultsPanel("Is this your bird?", this);
    
//    add to panel list
    panels.add(sizePanel.getPanel());
    panels.add(yearPanel.getPanel());
    panels.add(footPanel.getPanel());
    panels.add(beakPanel.getPanel());
    panels.add(colorPanel.getPanel());
    panels.add(resultPanel.getPanel());

    // Add everything to the main panel
    this.curPanel = titlePanel;
    panel.add(curPanel);
  }

  public static void main(String[] args)
  {
    JApplication app = new BaseApplication(args);
    invokeInEventDispatchThread(app);
  }
}
