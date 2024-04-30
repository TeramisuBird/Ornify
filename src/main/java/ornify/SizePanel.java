package ornify;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

/**
 * Size question panel.
 * 
 * This work complies with the JMU Honor Code.
 */
public class SizePanel extends CustomPanel
{

  private String currentOption;

  private JButton[] buttons;

  /**
   * Panel constructor.
   * 
   * @param question
   *          for panel's question
   * @param ba
   *          fo application to add panel to
   */
  public SizePanel(final String question, final BaseApplication ba)
  {
    super(question, ba);
    super.questionPanel.remove(super.image);
    currentOption = null;

    this.buttons = new JButton[Model.getSize().length];
    super.comboPanel.setLayout(new FlowLayout());

    for (int i = 0; i < buttons.length; i++)
    {
      buttons[i] = new JButton();
      ImageIcon icon = new ImageIcon(Model.getSizeImages()[i]);
      buttons[i].setIcon(icon);
      buttons[i].setHorizontalTextPosition(SwingConstants.CENTER);
      buttons[i].setVerticalTextPosition(SwingConstants.BOTTOM);
      buttons[i].setText(Model.getSize()[i]);
      buttons[i].setPreferredSize(new Dimension(190, 340));
      buttons[i].addActionListener(this);
      super.comboPanel.add(buttons[i]);
    }
  }

  /**
   * getter for user's selection.
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
        int index = Model.getIndicies().get("size");
        super.setChoice(buttons, e.getActionCommand());
        this.currentOption = e.getActionCommand();
        Model.getPicked()[index] = true;
        Model.getSelections()[index] = "(size = '" + this.currentOption.toLowerCase() + "')";
        break;
    }
  }
}
