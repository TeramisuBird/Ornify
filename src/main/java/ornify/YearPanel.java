package ornify;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

public class YearPanel extends CustomPanel implements ItemListener
{
  private static final String[] OPTIONS = {"----", "Migration", "Breeding", "Non-breeding", "All-year"};
  private JComboBox<String> combo;
  private String currentOption;

  public YearPanel(String question, BaseApplication ba)
  {
    super(question, ba);
    this.combo = new JComboBox<String>(OPTIONS);
    combo.setPreferredSize(new Dimension(200, 30));
    //combo.setBounds((WIDTH / 2) - 50, (HEIGHT / 2), 100, 50);
    combo.addItemListener(this);
    //JPanel panel = this.getPanel();
    //panel.add(combo);
    super.image.setIcon(ImageReader.readImage("cardinal_in_snow.jpg", 600, 400));
    super.comboPanel.add(combo);
    currentOption = OPTIONS[0];
  }
  
  @Override
  public void itemStateChanged(ItemEvent e)
  {
    // if the state combobox is changed
    if (e.getSource() == combo) {
      currentOption = (String) combo.getSelectedItem();
      this.baseApp.addChoice(currentOption, 0);
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
