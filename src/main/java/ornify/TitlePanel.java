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
 * Starting Panel.
 * 
 * This work complies with the JMU Honor Code.
 */
public class TitlePanel implements ActionListener
{
  private static final String HTML = "<html><body style='width: %1spx'>%1s";
  
  private String start = "Start";
  private JButton startButton = new JButton(start);
  private JLabel titleText = new JLabel(" ");
  private JLabel image = new JLabel("");
  private JPanel panel = new JPanel();
  private JPanel controlPanel = new JPanel();
  private BaseApplication baseApp;

  /**
   * Panel Constructor.
   * 
   * @param title
   *          for title screen text
   * @param ba
   *          for application to add panel to
   */
  public TitlePanel(final String title, final BaseApplication ba)
  {
    this.baseApp = ba;
    this.panel.setLayout(new BorderLayout());
    this.panel.setBounds(0, 0, BaseApplication.WIDTH, BaseApplication.HEIGHT);
    this.panel.setBackground(BaseApplication.BACKGROUND_COLOR);

    this.titleText = new JLabel(String.format(HTML, 300, title));
    this.titleText.setHorizontalAlignment(SwingConstants.CENTER);
    this.titleText.setFont(new Font("Verdana", Font.BOLD, 120));

    this.startButton.setPreferredSize(new Dimension(130, 40));
    this.startButton.addActionListener(this);

    this.image.setHorizontalAlignment(SwingConstants.CENTER);
    this.image = new JLabel(ba.model.TITLE_IMAGE);
    controlPanel.setLayout(new FlowLayout());
    this.controlPanel.setBackground(BaseApplication.BACKGROUND_COLOR);
    controlPanel.add(startButton);
    this.panel.add(titleText, BorderLayout.NORTH);
    this.panel.add(image, BorderLayout.CENTER);
    this.panel.add(controlPanel, BorderLayout.SOUTH);
    this.panel.setVisible(true);
  }

  /**
   * panel getter.
   * 
   * @return for panel to add to application
   */
  public JPanel getPanel()
  {
    return this.panel;
  }

  /**
   * Method that checks for action performed.
   * @param e the action
   */
  @Override public void actionPerformed(final ActionEvent e)
  {
    if (e.getActionCommand().equals(start))
    {
      this.baseApp.handleStart();
    }
  }
}
