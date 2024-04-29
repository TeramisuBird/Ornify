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

/**
 * Parent class for Custom Panels.
 * 
 * This work complies with the JMU Honor Code.
 */
public class CustomPanel implements ActionListener
{
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

  /**
   * Super-class constructor for panels.
   * 
   * @param question
   *          for panel's question
   * @param ba
   *          for application to add panel to
   */
  public CustomPanel(final String question, final BaseApplication ba)
  {
    this.baseApp = ba;
    this.panel.setLayout(new BorderLayout());
    this.panel.setBounds(0, 0, BaseApplication.WIDTH, BaseApplication.HEIGHT);
    text = new JLabel(String.format(HTML, 300, question));
    text.setHorizontalAlignment(SwingConstants.CENTER);
    text.setFont(new Font("Verdana", Font.BOLD, 30));

    this.nextButton.setPreferredSize(new Dimension(100, 30));
    this.nextButton.addActionListener(this);
    this.returnButton.setPreferredSize(new Dimension(100, 30));
    this.returnButton.addActionListener(this);

    this.questionPanel.setBackground(BaseApplication.BACKGROUND_COLOR);
    this.comboPanel.setBackground(BaseApplication.BACKGROUND_COLOR);
    this.controlPanel.setBackground(BaseApplication.BACKGROUND_COLOR);
    this.panel.setBackground(BaseApplication.BACKGROUND_COLOR);

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

  /**
   * getter for an instance's panel.
   * 
   * @return panel
   */
  public JPanel getPanel()
  {
    return this.panel;
  }

  @Override
  public void actionPerformed(final ActionEvent e)
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

  /**
   * disable/enable buttons on a panel after a selection.
   * 
   * @param buttons
   *          for panel's buttons
   * @param size
   *          for string to disable
   */
  protected void setChoice(final JButton[] buttons, final String size)
  {
    for (int i = 0; i < buttons.length; i++)
    {
      if (buttons[i].getText() == size)
      {
        buttons[i].setEnabled(false);
      }
      else
      {
        buttons[i].setEnabled(true);
      }
    }
  }
}
