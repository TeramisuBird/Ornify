package ornify;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

public class BeakPanel extends CustomPanel
{
  private String currentOption;
  private JButton[] buttons;
  
  public BeakPanel(String question, BaseApplication ba)
  {
    super(question, ba);
    super.image.setIcon(Model.BEAK_IMAGE);
    this.currentOption = null;
    
    this.buttons = new JButton[Model.BEAK_SHAPE.length];
    for (int i = 0; i < buttons.length; i++)
    {
      buttons[i] = new JButton(Model.BEAK_SHAPE[i]);
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
        int index = Model.selectionIndicies.get("beak_shape");
        super.setChoice(buttons, e.getActionCommand());
        this.currentOption = e.getActionCommand();
        Model.picked[index] = true;
        Model.selections[index] = this.currentOption.toLowerCase();
        break;
    }
  }
}
