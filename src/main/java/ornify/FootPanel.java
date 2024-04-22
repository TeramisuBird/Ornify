package ornify;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

public class FootPanel extends CustomPanel implements ItemListener
{
  private JComboBox<String> combo;
  private String currentOption;

  public FootPanel(String question, BaseApplication ba)
  {
    super(question, ba);
    this.combo = new JComboBox<String>(Model.FOOT_SHAPE);
    combo.setPreferredSize(new Dimension(200, 30));
    combo.addItemListener(this);
    super.image.setIcon(Model.FOOT_IMAGE);
    super.comboPanel.add(combo);
    currentOption = Model.FOOT_SHAPE[0];
  }
  
  @Override
  public void itemStateChanged(ItemEvent e)
  {
    // if the state combobox is changed
    if (e.getSource() == combo) {
      currentOption = (String) combo.getSelectedItem();
      this.baseApp.addChoice(currentOption, 13);
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
