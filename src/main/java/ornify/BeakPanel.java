package ornify;

import java.awt.Component;
import java.awt.Dimension;
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
  public BeakPanel(String question, BaseApplication ba)
  {
    super(question, ba);
    super.questionPanel.remove(super.image);
    this.currentOption = null;

    this.buttons = new JButton[Model.BEAK_SHAPE.length];

    for (int i = 0; i < buttons.length; i++)
    {
      buttons[i] = new JButton();
      ImageIcon icon = new ImageIcon(Model.BEAK_IMAGES[i]);
      buttons[i].setIcon(icon);
      buttons[i].setIconTextGap(10); // Set gap between icon and text
      buttons[i].setHorizontalTextPosition(SwingConstants.RIGHT);
      buttons[i].setText(Model.BEAK_SHAPE[i]);
      buttons[i].setAlignmentX(Component.CENTER_ALIGNMENT);
      buttons[i].setPreferredSize(new Dimension(600, 75));
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
        int index = Model.selectionIndicies.get("beak_shape");
        super.setChoice(buttons, e.getActionCommand());
        this.currentOption = e.getActionCommand();
        Model.picked[index] = true;
        Model.selections[index] = "beak_shape = '" + this.currentOption.toLowerCase() + "'";
        break;
    }
  }
}
