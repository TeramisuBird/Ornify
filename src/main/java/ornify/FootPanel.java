package ornify;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

/**
 * Foot Shape question panel class.
 * 
 * This work complies with the JMU Honor Code.
 */
public class FootPanel extends CustomPanel
{
  private String currentOption;
  private JButton[] buttons;

  /**
   * Panel constructor.
   * 
   * @param question for panel's question
   * @param ba for application to add panel to
   */
  public FootPanel(String question, BaseApplication ba)
  {
    super(question, ba);
    super.image.setIcon(Model.FOOT_IMAGE);
    currentOption = null;
    
    this.buttons = new JButton[Model.FOOT_SHAPE.length];
    for (int i = 0; i < buttons.length; i++)
    {
      buttons[i] = new JButton(Model.FOOT_SHAPE[i]);
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
        int index = Model.selectionIndicies.get("foot_shape");
        super.setChoice(buttons, e.getActionCommand());
        this.currentOption = e.getActionCommand();
        Model.picked[index] = true;
        Model.selections[index] = "foot_shape = '" + this.currentOption.toLowerCase() + "'";
        break;
    }
  }
}
