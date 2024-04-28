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
  private final String[] seasons = {"Autumn", "Winter", "Spring", "Summer"};

  /**
   * Panel constructor.
   * 
   * @param question for panel's question
   * @param ba for application to add panel to
   */
  public YearPanel(String question, BaseApplication ba)
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
    
    super.questionPanel.add(stage.getView());
  }
  
  /**
   * getter for user's current selection
   * 
   * @return currentOption
   */
  public String getOption()
  {
    return currentOption;
  }
  
  @Override
  public void actionPerformed(ActionEvent e)
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
        int index = Model.selectionIndicies.get("season");
        if (currentOption != null)
        {
          Model.picked[index] = true;
          String queryText = "(season = 'all-year' or season = '" + currentOption.toLowerCase() + "')";
          Model.selections[index] = queryText;
        }
        break;
    }
  }
  
  /**
   * get corresponding text for the user's selection
   * 
   * @param type for user visible selection
   */
  private void getChoice(String type)
  {
    switch (type)
    {
      case "Spring":
        curImage = Model.RAIN_IMAGE;
        currentOption = "Migration";
        break;
      case "Autumn":
        curImage = Model.LEAF_IMAGE;
        currentOption = "Migration";
        break;
      case "Winter":
        curImage = Model.SNOW_IMAGE;
        currentOption = "Non-Breeding";
        break;
      case "Summer":
        curImage = Model.BUG_IMAGE;
        currentOption = "Breeding";
        break;
      default:
        System.out.println("unreachable");
        curImage = null;
        break;
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
