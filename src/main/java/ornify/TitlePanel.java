package ornify;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TitlePanel implements ActionListener
{
  private static final String HTML = "<html><body style='width: %1spx'>%1s";
  public JButton startButton = new JButton("Start");
  public JLabel titleText = new JLabel(" ");
  public JLabel image = new JLabel(" ");
  public JPanel panel = new JPanel();
  public JPanel controlPanel = new JPanel();
  public BaseApplication baseApp;
  
  public TitlePanel(String title, BaseApplication ba)
  {
    this.baseApp = ba;
    this.panel.setLayout(new BorderLayout());
    this.panel.setBounds(0, 0, BaseApplication.WIDTH, BaseApplication.HEIGHT);
    this.panel.setBackground(BaseApplication.background_color);
    
    this.titleText = new JLabel(String.format(HTML, 300, title));
    this.titleText.setHorizontalAlignment(SwingConstants.CENTER);
    this.titleText.setFont(new Font("Verdana", Font.BOLD, 120));
    
    this.startButton.setPreferredSize(new Dimension(130, 40));
    this.startButton.addActionListener(this);
    
    this.image.setHorizontalAlignment(SwingConstants.CENTER);
    this.image = new JLabel(Model.TITLE_IMAGE);
    controlPanel.setLayout(new FlowLayout());
    this.controlPanel.setBackground(BaseApplication.background_color);
    controlPanel.add(startButton);
    this.panel.add(titleText, BorderLayout.NORTH);
    this.panel.add(image, BorderLayout.CENTER);
    this.panel.add(controlPanel, BorderLayout.SOUTH);
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

