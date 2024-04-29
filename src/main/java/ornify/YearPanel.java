package ornify;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.JButton;

import visual.dynamic.described.Stage;
import visual.statik.sampled.Content;

/**
 * Year question panel.
 * 
 * This work complies with the JMU Honor Code.
 */
public class YearPanel extends CustomPanel
{
  private String currentOption;
  private Image curImage;
  private Stage stage;
  
  private FallingSprite[] items;
  
  private JButton[] buttons;
  
  private String autumn = "Autumn";
  private String winter = "Winter";
  private String spring = "Spring";
  private String summer = "Summer";
  private final String[] seasons = {autumn, winter, spring, summer};

  /**
   * Panel constructor.
   * 
   * @param question for panel's question
   * @param ba for application to add panel to
   */
  public YearPanel(final String question, final BaseApplication ba)
  {
    super(question, ba);
    super.questionPanel.remove(super.image);
    
    this.stage = new Stage(20);
    stage.setBackground(new Color(180, 250, 250));
    stage.getView().setPreferredSize(new Dimension(300, 300));
    this.curImage = null;
    
    this.items = new FallingSprite[10];
    for (int i = 0; i < items.length; i++)
    {
      Content con = new Content();
      items[i] = new FallingSprite(con);
      stage.add(items[i]);
    }
    
    currentOption = null;
    
    this.buttons = new JButton[seasons.length];
    for (int i = 0; i < buttons.length; i++)
    {
      buttons[i] = new JButton(seasons[i]);
      buttons[i].setPreferredSize(new Dimension(100, 100));
      buttons[i].addActionListener(this);
      super.comboPanel.add(buttons[i]);
    }
    buttons[0].setBackground(Color.RED);
    buttons[0].setForeground(new Color(250, 160, 160));
    buttons[1].setBackground(Color.BLUE);
    buttons[1].setForeground(new Color(64, 181, 173));
    buttons[2].setBackground(Color.GREEN);
    buttons[2].setForeground(new Color(46, 139, 87));
    buttons[3].setBackground(Color.YELLOW);
    buttons[3].setForeground(new Color(228, 208, 10));
    
    super.questionPanel.add(stage.getView());
  }
  
  /**
   * getter for user's current selection.
   * 
   * @return currentOption
   */
  public String getOption()
  {
    return currentOption;
  }
  
  /**
   * Method that checks for action performed.
   * @param e the action
   */
  @Override public void actionPerformed(final ActionEvent e)
  {
    switch (e.getActionCommand())
    {
      case "Return":
        this.baseApp.handleReturn();
        break;
      case "Next":
        this.baseApp.handleNext();
        break;
      default:
        super.setChoice(buttons, e.getActionCommand());
        getChoice(e.getActionCommand());
        int index = Model.getIndicies().get("season");
        if (currentOption != null)
        {
          Model.getPicked()[index] = true;
          String queryText = "(season = 'all-year' or season = '" 
              + currentOption.toLowerCase() + "')";
          Model.getSelections()[index] = queryText;
        }
        break;
    }
  }
  
  /**
   * get corresponding text for the user's selection.
   * 
   * @param type for user visible selection
   */
  private void getChoice(final String type)
  {
    String mig = "Migration";
    if (type.equals(autumn))
    {
      curImage = Model.LEAF_IMAGE;
      currentOption = mig;
    }
    else if (type.equals(winter))
    {
      curImage = Model.SNOW_IMAGE;
      currentOption = "Non-Breeding";
    }
    else if (type.equals(spring))
    {
      curImage = Model.RAIN_IMAGE;
      currentOption = mig;
    }
    else if (type.equals(summer))
    {
      curImage = Model.BUG_IMAGE;
      currentOption = "Breeding";
    }
    
    if (curImage != null)
    {
      for (int i = 0; i < items.length; i++)
      {
        items[i].setSprite((BufferedImage) curImage);
      }
    }
    
    stage.start();
    stage.repaint();
  }
}
