package ornify;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

/**
 * Beak Shape question panel class.
 * 
 * This work complies with the JMU Honor Code.
 */
public class BeakPanel extends CustomPanel
{
  private String currentOption;
  private JButton[] buttons;

  /**
   * Panel constructor.
   * 
   * @param question
   *          for panel's question.
   * @param ba
   *          for application to add panel to
   */
  public BeakPanel(final String question, final BaseApplication ba)
  {
    super(question, ba);
    super.questionPanel.remove(super.image);
    this.currentOption = null;

    this.buttons = new JButton[Model.getBeak().length];
    super.comboPanel.setLayout(new FlowLayout());

    for (int i = 0; i < buttons.length; i++)
    {
      buttons[i] = new JButton();
      ImageIcon icon = new ImageIcon(Model.BEAK_IMAGES[i]);
      buttons[i].setIcon(icon);
      buttons[i].setHorizontalTextPosition(SwingConstants.CENTER);
      buttons[i].setVerticalTextPosition(SwingConstants.BOTTOM);
      buttons[i].setText(Model.getBeak()[i]);
      buttons[i].setPreferredSize(new Dimension(210, 240));
      buttons[i].addActionListener(this);
      super.comboPanel.add(buttons[i]);
    }
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
   * Method that checks for an action performed.
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
        int index = Model.getIndicies().get("beak_shape");
        super.setChoice(buttons, e.getActionCommand());
        this.currentOption = e.getActionCommand();
        Model.getPicked()[index] = true;
        Model.getSelections()[index] = "beak_shape = '" + this.currentOption.toLowerCase() + "'";
        break;
    }
  }
}
