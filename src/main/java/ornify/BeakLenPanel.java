package ornify;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BeakLenPanel extends CustomPanel implements ChangeListener
{
  private JSlider slider;
  private JLabel value;
  private String currentOption;
  
  public BeakLenPanel(String question, BaseApplication ba)
  {
    super(question, ba);
    this.slider = new JSlider(0, 100, 50);
    slider.addChangeListener(this);
    
    this.value = new JLabel("The current value is: 5.0 inches");
    value.setPreferredSize(new Dimension(200, 30));
    super.image.setIcon(Model.BEAKLEN_IMAGE);
    this.currentOption = null;
    
    super.comboPanel.add(slider);
    super.comboPanel.add(value);
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

  @Override
  public void stateChanged(ChangeEvent e)
  {
    float val = (float)slider.getValue();
    value.setText("The current value is: " + val / 10 + " inches");
  }
}
