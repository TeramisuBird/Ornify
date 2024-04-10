package ornify;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CustomPanel implements ActionListener
{
  public static final int WIDTH = 600;
  public static final int HEIGHT = 600;
  
  public JButton nextButton;
  public JButton returnButton;
  public JPanel panel;
  public BaseApplication baseApp;
  
  public CustomPanel(String question, BaseApplication ba)
  {
    this.baseApp = ba;
    this.panel = new JPanel();
    this.panel.setLayout(null);
    this.panel.setBounds(0, 0, WIDTH, HEIGHT);
    
    JLabel label = new JLabel(question);
    label.setFont(new Font("Verdana", Font.BOLD, 30));

    label.setBounds((WIDTH / 2) - 200, 50, 500, 100);
    
    this.nextButton = new JButton("Next");
    this.nextButton.setBounds((WIDTH / 2) - 50, (HEIGHT / 2) + 200, 100, 50);
    this.nextButton.addActionListener(this);
    this.returnButton = new JButton("Return");
    this.returnButton.setBounds((WIDTH / 2) - 50, (HEIGHT / 2) + 100, 100, 50);
    this.returnButton.addActionListener(this);
    
    this.panel.add(label);
    this.panel.add(this.nextButton);
    this.panel.add(this.returnButton);
    this.panel.setVisible(true);
  }
  
  public JPanel getPanel()
  {
    return this.panel;
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
