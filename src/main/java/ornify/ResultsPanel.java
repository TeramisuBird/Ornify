package ornify;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class ResultsPanel implements ActionListener
{
  private static final String STARTING_TEXT = "Possible matches are:\n";
  public static final int WIDTH = 600;
  public static final int HEIGHT = 600;
  
  private SQLDatabase db;
  public JTextPane textPane;
  public JButton returnButton;
  public JButton getResultsButton;
  public JPanel panel;
  public BaseApplication baseApp;
  public String matchesText;
  
  public ResultsPanel(String question, BaseApplication ba)
  {
    this.db = new SQLDatabase();
    this.matchesText = STARTING_TEXT;
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
    
    this.getResultsButton = new JButton("Find Matches");
    this.getResultsButton.setBounds((WIDTH / 2) - 50, (HEIGHT / 2) + 200, 100, 50);
    this.getResultsButton.addActionListener(this);
    
    this.textPane = new JTextPane();
    
    this.panel.setBackground(BaseApplication.background_color);
    this.panel.add(label);
    this.panel.add(this.returnButton);
    this.panel.add(this.getResultsButton);
    this.panel.setVisible(true);
  }
  
  @Override
  public void actionPerformed(ActionEvent e)
  {
    switch (e.getActionCommand())
    {
      case "Return":
        this.panel.remove(textPane);
        this.panel.repaint();
        this.matchesText = STARTING_TEXT;
        this.baseApp.handleReturn();
        break;
      case "Find Matches":
        buildResults();
        break;
      default:
        break;
    }
  }
  
  private void buildResults() {
    String select = "select name, image_url, sound_url ";
    String from = "from bird";
    String where = " where ";
    String conditions = "";
    
    int fieldsEntered = 0;
    
    for (int i = 0; i < 15; i++)
    {
      if (this.baseApp.userChoices[i] != null)
      {
        String text = this.baseApp.userChoices[i].toLowerCase();
        switch (i)
        {
          case(0):
            conditions = conditions + "season='" + text + "'";
            fieldsEntered++;
            break;
          case(1):
            if (fieldsEntered == 0)
            {
              conditions = conditions + "size='" + text + "'";
            }
            else
            {
              conditions = conditions + " and size='" + text + "'";
            }
            break;
          case(2):
            conditions = conditions + " and crown='" + text + "'";
            break;
          case(3):
            conditions = conditions + " and supercilium='" + text + "'";
            break;
          case(4):
            conditions = conditions + " and eyestripe='" + text + "'";
            break;
          case(5):
            conditions = conditions + " and auriculars='" + text + "'";
            break;
          case(6):
            conditions = conditions + " and beak_shape='" + text + "'";
            break;
          case(7):
            conditions = conditions + " and beak_length='" + text + "'";
            break;
          case(8):
            conditions = conditions + " and beak_color='" + text + "'";
            break;
          case(9):
            conditions = conditions + " and throat='" + text + "'";
            break;
          case(10):
            conditions = conditions + " and breast='" + text + "'";
            break;
          case(11):
            conditions = conditions + " and coverts='" + text + "'";
            break;
          case(12):
            conditions = conditions + " and wing='" + text + "'";
            break;
          case(13):
            conditions = conditions + " and foot_shape='" + text + "'";
            break;
          case(14):
            conditions = conditions + " and foot_color='" + text + "'";
            break;
          default:
            System.out.println("error - unreachable switch case found");
            break;
        }
      }
    }
    
    String query = select + from + where + conditions;
//    System.out.println(query);
//    this.db.printColumnFromQuery(0, query);
//    this.baseApp.dumpResults();
    ResultSet matches = this.db.getResultsFromQuery(query);
    
    try
    {
      if (matches != null)
      {
        while (matches.next())
        {
          matchesText = matchesText + matches.getString(1) + "\n";
        }
      }
      else
      {
        matchesText = matchesText + "No results - query error\n";
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    
    this.textPane.setText(matchesText);
    this.textPane.setBounds((WIDTH / 2) - 260, (HEIGHT / 2) - 55, WIDTH - 80, 100);
    this.textPane.setVisible(true);
    this.panel.add(textPane);
    this.panel.repaint();
  }

  public JPanel getPanel()
  {
    return this.panel;
  }
}
