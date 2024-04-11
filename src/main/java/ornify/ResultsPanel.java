package ornify;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ResultsPanel implements ActionListener
{
  public static final int WIDTH = 600;
  public static final int HEIGHT = 600;
  
  public JButton returnButton;
  public JPanel panel;
  public BaseApplication baseApp;
  
  public ResultsPanel(String question, BaseApplication ba)
  {
    this.baseApp = ba;
    this.panel = new JPanel();
    this.panel.setLayout(null);
    this.panel.setBounds(0, 0, WIDTH, HEIGHT);
    
    String html = "<html><body style='width: %1spx'>%1s";
    JLabel label = new JLabel(String.format(html, 300, question));
    label.setHorizontalAlignment(SwingConstants.CENTER);
    label.setFont(new Font("Verdana", Font.BOLD, 30));

    label.setBounds((WIDTH / 2) - 200, 50, 500, 100);
    
    this.returnButton = new JButton("Return");
    this.returnButton.setBounds((WIDTH / 2) - 50, (HEIGHT / 2) + 100, 100, 50);
    this.returnButton.addActionListener(this);
    
    this.panel.add(label);
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
        this.baseApp.dumpResults();
        this.baseApp.handleReturn();
        break;
      default:
        break;
    }
    
  }
}
