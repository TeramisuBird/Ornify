package ornify;

import java.awt.Component;
import java.awt.Dimension;
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
  public SizePanel(String question, BaseApplication ba)
  {
    super(question, ba);
    super.questionPanel.remove(super.image);
    currentOption = null;

    this.buttons = new JButton[Model.SIZE.length];

    for (int i = 0; i < buttons.length; i++)
    {
      buttons[i] = new JButton();
      ImageIcon icon = new ImageIcon(Model.SIZE_IMAGES[i]);
      buttons[i].setIcon(icon);
      buttons[i].setIconTextGap(10); // Set gap between icon and text
      buttons[i].setHorizontalTextPosition(SwingConstants.RIGHT);
      buttons[i].setText(Model.SIZE[i]);
      buttons[i].setAlignmentX(Component.CENTER_ALIGNMENT);
      buttons[i].setPreferredSize(new Dimension(600, 115));
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
        int index = Model.selectionIndicies.get("size");
        super.setChoice(buttons, e.getActionCommand());
        this.currentOption = e.getActionCommand();
        Model.picked[index] = true;
        Model.selections[index] = "(size = '" + this.currentOption.toLowerCase() + "')";
        break;
    }
  }
}
