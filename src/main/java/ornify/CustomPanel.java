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

public class CustomPanel implements ActionListener
{
  public static final int WIDTH = 600;
  public static final int HEIGHT = 600;
  private static final String HTML = "<html><body style='width: %1spx'>%1s";
  
  public JButton nextButton = new JButton("Next");
  public JButton returnButton = new JButton("Return");
  public JLabel text = new JLabel(" ");
  public JLabel image = new JLabel(" ");
  public JPanel panel = new JPanel();
  public JPanel questionPanel = new JPanel();
  public JPanel comboPanel = new JPanel();
  public JPanel controlPanel = new JPanel();
  public BaseApplication baseApp;
  
  public CustomPanel(String question, BaseApplication ba)
  {
    this.baseApp = ba;
    this.panel.setLayout(new BorderLayout());
    this.panel.setBounds(0, 0, WIDTH, HEIGHT);
    text = new JLabel(String.format(HTML, 300, question));
    text.setHorizontalAlignment(SwingConstants.CENTER);
    text.setFont(new Font("Verdana", Font.BOLD, 30));
    
    this.nextButton.setPreferredSize(new Dimension(100, 30));
    this.nextButton.addActionListener(this);
    this.returnButton.setPreferredSize (new Dimension(100, 30));
    this.returnButton.addActionListener(this);
    
    this.questionPanel.setBackground(BaseApplication.background_color);
    this.comboPanel.setBackground(BaseApplication.background_color);
    this.controlPanel.setBackground(BaseApplication.background_color);
    this.panel.setBackground(BaseApplication.background_color);
    
    this.questionPanel.setLayout(new BorderLayout());
    this.comboPanel.setLayout(new FlowLayout());
    
    this.controlPanel.setLayout(new FlowLayout());
    this.controlPanel.add(this.returnButton);
    this.controlPanel.add(this.nextButton);
    
    this.image.setHorizontalAlignment(SwingConstants.CENTER);
    questionPanel.add(text, BorderLayout.NORTH);
    questionPanel.add(image, BorderLayout.CENTER);
    
    this.panel.add(questionPanel, BorderLayout.NORTH);
    this.panel.add(comboPanel, BorderLayout.CENTER);
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
