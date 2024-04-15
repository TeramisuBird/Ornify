package ornify;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

public class FootPanel extends CustomPanel implements ItemListener
{
  private static final String[] OPTIONS = {"----", "Clawed", "Climbing", "Perching", "Wading", "Webbed"};
  private JComboBox<String> combo;
  private String currentOption;

  public FootPanel(String question, BaseApplication ba)
  {
    super(question, ba);
    this.combo = new JComboBox<String>(OPTIONS);
    //combo.setBounds((WIDTH / 2) - 50, (HEIGHT / 2), 100, 50);
    combo.setPreferredSize(new Dimension(200, 30));
    combo.addItemListener(this);
    //JPanel panel = this.getPanel();
    //JLabel pic = new JLabel();
    //pic.setBounds((WIDTH / 2) - 50, 50, 400, 400);
    super.image.setIcon(ImageReader.readImage("feet_types.png", 400, 450));
    super.comboPanel.add(combo);
    currentOption = OPTIONS[0];
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
