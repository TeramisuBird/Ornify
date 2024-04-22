package ornify;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

public class BeakPanel extends CustomPanel implements ItemListener
{
  private JComboBox<String> combo = new JComboBox<String>(Model.BEAK_SHAPE);
  private String currentOption = Model.BEAK_SHAPE[0];
  
  public BeakPanel(String question, BaseApplication ba)
  {
    super(question, ba);
    combo.setPreferredSize(new Dimension(200, 30));
    combo.addItemListener(this);
    super.image.setIcon(Model.BEAK_IMAGE);
    super.comboPanel.add(combo);
  }
  
  @Override
  public void itemStateChanged(ItemEvent e)
  {
    // if the state combobox is changed
    if (e.getSource() == combo) {
      currentOption = (String) combo.getSelectedItem();
      this.baseApp.addChoice(currentOption, 6);
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
        break;
    }
  }
}
