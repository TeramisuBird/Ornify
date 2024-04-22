package ornify;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import visual.Visualization;

public class TitlePanel extends Visualization implements ActionListener
{
  public static final int WIDTH = 600;
  public static final int HEIGHT = 600;
  
  public JButton startButton;
  public JPanel panel;
  public BaseApplication baseApp;
  
  public TitlePanel(String title, BaseApplication ba)
  {
    super();
    this.getView().setSize(WIDTH, HEIGHT);
    
    this.baseApp = ba;
    this.panel = new JPanel();
    this.panel.setLayout(null);
    this.panel.setBounds(0, 0, WIDTH, HEIGHT);
    
    JLabel label = new JLabel(title);
    label.setFont(new Font("Verdana", Font.BOLD, 30));

    label.setBounds((WIDTH / 2) - 50, 50, 500, 100);
    
    this.startButton = new JButton("Start");
    this.startButton.setBounds((WIDTH / 2) - 50, (HEIGHT / 2) + 200, 100, 50);
    this.startButton.addActionListener(this);
    
    this.panel.setBackground(BaseApplication.background_color);
    JLabel picLabel = new JLabel(Model.TITLE_IMAGE);
    picLabel.setBounds((WIDTH/2) - 100, (HEIGHT/2) - 100, 200, 200);
    this.panel.add(picLabel);
    this.panel.add(label);
    this.panel.add(this.startButton);
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
      case "Start":
        this.baseApp.handleStart();
        break;
      default:
        break;
    }
  }
}

