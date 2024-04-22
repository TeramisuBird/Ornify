package ornify;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

public class SizePanel extends CustomPanel implements ItemListener
{
  
  private static final String[] OPTIONS = {"----", "Tiny", "Small", "Medium", "Large"};
  private JComboBox<String> combo;
  private String currentOption;

  public SizePanel(String question, BaseApplication ba)
  {
    super(question, ba);
    this.combo = new JComboBox<String>(OPTIONS);
    //combo.setBounds((WIDTH / 2) - 50, (HEIGHT / 2), 100, 50);
    combo.setPreferredSize(new Dimension(200, 30));
    combo.addItemListener(this);
    super.image.setIcon(ImageReader.readImage("bird_sizes.png", 500, 300));
    super.comboPanel.add(combo);
    currentOption = OPTIONS[0];
  }

  @Override
  public void itemStateChanged(ItemEvent e)
  {
    // if the state combobox is changed
    if (e.getSource() == combo) {
      currentOption = (String) combo.getSelectedItem();
      this.baseApp.addChoice(currentOption, 1);
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
