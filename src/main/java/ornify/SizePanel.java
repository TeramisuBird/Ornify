package ornify;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

public class SizePanel extends CustomPanel
{
  
  private String currentOption;
  
  private JButton[] buttons;

  public SizePanel(String question, BaseApplication ba)
  {
    super(question, ba);
    super.image.setIcon(Model.SIZE_IMAGE);
    currentOption = null;
    
    this.buttons = new JButton[Model.SIZE.length];
    for (int i = 0; i < buttons.length; i++)
    {
      buttons[i] = new JButton(Model.SIZE[i]);
      buttons[i].addActionListener(this);
      super.comboPanel.add(buttons[i]);
    }
  }
  
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
        this.currentOption = e.getActionCommand();
        break;
    }
  }
}
