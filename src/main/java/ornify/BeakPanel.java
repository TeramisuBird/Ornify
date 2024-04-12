package ornify;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class BeakPanel extends CustomPanel implements ItemListener
{
  private static final String[] OPTIONS = {"----", "Cone", "Chisel", "Pointy", "Hooked", "Flat", "Probing"};
  private JComboBox<String> combo;
  private String currentOption;
  
  public BeakPanel(String question, BaseApplication ba)
  {
    super(question, ba);
    this.combo = new JComboBox<String>(OPTIONS);
    combo.setBounds((WIDTH / 2) - 50, (HEIGHT / 2), 100, 50);
    combo.addItemListener(this);
    JPanel panel = this.getPanel();
    panel.add(combo);
    
    currentOption = OPTIONS[0];
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
        if (!currentOption.equals(OPTIONS[0]))
        {
          this.baseApp.handleNext();
        }
        break;
      default:
        break;
    }
  }
}
