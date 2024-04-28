package ornify;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.JButton;

/**
 * Year question panel.
 * 
 * This work complies with the JMU Honor Code.
 */
public class YearPanel extends CustomPanel
{
  private String currentOption;
  
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
    currentOption = null;
    
    this.buttons = new JButton[seasons.length];
    for (int i = 0; i < buttons.length; i++)
    {
      buttons[i] = new JButton(seasons[i]);
      buttons[i].setPreferredSize(new Dimension(100, 100));
      buttons[i].addActionListener(this);
      super.comboPanel.add(buttons[i]);
    }
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
        currentOption = "Migration";
        break;
      case "Autumn":
        currentOption = "Migration";
        break;
      case "Winter":
        currentOption = "Non-Breeding";
        break;
      case "Summer":
        currentOption = "Breeding";
        break;
      default:
        System.out.println("unreachable");
        break;
    }
  }
}
