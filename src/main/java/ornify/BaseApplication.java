package ornify;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

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

  private JPanel curPanel;
  private int index;
  private ArrayList<JPanel> panels;

  public BaseApplication(String[] args)
  {
    super(args, WIDTH, HEIGHT);
    panels = new ArrayList<JPanel>();
    index = 0;
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
  protected void handleReturn()
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
    
    // Create question panel
    JPanel sizePanel = new JPanel();
    sizePanel.setLayout(null);
    sizePanel.setBounds(0, 0, WIDTH, HEIGHT);
    
    // Add to the title panel
    JLabel size = new JLabel("Choose size of bird");
    size.setFont(new Font("Verdana", Font.BOLD, 30));

    size.setBounds((WIDTH / 2) - 200, 50, 500, 100);
    
    JButton nextButton = new JButton(NEXT);
    nextButton.setBounds((WIDTH / 2) - 50, (HEIGHT / 2) + 200, 100, 50);
    nextButton.addActionListener(this);
    JButton returnButton = new JButton(RETURN);
    returnButton.setBounds((WIDTH / 2) - 50, (HEIGHT / 2) + 100, 100, 50);
    returnButton.addActionListener(this);
    
    sizePanel.add(size);
    sizePanel.add(nextButton);
    sizePanel.add(returnButton);
    sizePanel.setVisible(true);
    
    panels.add(sizePanel);
    
    // Create question panel
    JPanel colorPanel = new JPanel();
    colorPanel.setLayout(null);
    colorPanel.setBounds(0, 0, WIDTH, HEIGHT);
    
    // Add to the title panel
    JLabel color = new JLabel("Choose color of bird");
    color.setFont(new Font("Verdana", Font.BOLD, 30));

    color.setBounds((WIDTH / 2) - 200, 50, 500, 100);
    
    JButton nextButton1 = new JButton(NEXT);
    nextButton1.setBounds((WIDTH / 2) - 50, (HEIGHT / 2) + 200, 100, 50);
    nextButton1.addActionListener(this);
    JButton returnButton1 = new JButton(RETURN);
    returnButton1.setBounds((WIDTH / 2) - 50, (HEIGHT / 2) + 100, 100, 50);
    returnButton1.addActionListener(this);
    
    colorPanel.add(color);
    colorPanel.add(nextButton1);
    colorPanel.add(returnButton1);
    colorPanel.setVisible(true);
    
    panels.add(colorPanel);
    
    // Create question panel
    JPanel yearPanel = new JPanel();
    yearPanel.setLayout(null);
    yearPanel.setBounds(0, 0, WIDTH, HEIGHT);
    
    // Add to the title panel
    JLabel year = new JLabel("When is the bird found?");
    year.setFont(new Font("Verdana", Font.BOLD, 30));

    year.setBounds((WIDTH / 2) - 200, 50, 500, 100);
    
    JButton nextButton2 = new JButton(NEXT);
    nextButton2.setBounds((WIDTH / 2) - 50, (HEIGHT / 2) + 200, 100, 50);
    nextButton2.addActionListener(this);
    JButton returnButton2 = new JButton(RETURN);
    returnButton2.setBounds((WIDTH / 2) - 50, (HEIGHT / 2) + 100, 100, 50);
    returnButton2.addActionListener(this);
    
    yearPanel.add(year);
    yearPanel.add(nextButton2);
    yearPanel.add(returnButton2);
    yearPanel.setVisible(true);
    
    panels.add(yearPanel);

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
