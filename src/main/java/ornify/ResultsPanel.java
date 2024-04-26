package ornify;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class ResultsPanel extends CustomPanel implements ActionListener
{
  // private static final String STARTING_TEXT = "Possible matches are:\n";
  public static final int WIDTH = 600;
  public static final int HEIGHT = 600;
  public static final boolean IS_ONLINE = true;

  private SQLDatabase db;
  public JTextPane textPane = new JTextPane();
  private JPanel yesNoPanel = new JPanel();
  private JPanel comboGridPanel = new JPanel();
  public JButton restartButton = new JButton("Yes");
  public JButton tryAnotherButton = new JButton("Try another?");
  public QueryBuilder qb = new QueryBuilder();
  public String matchesText;

  private ResultSet set;
  
  private void configureResults() {
    textPane.setPreferredSize(new Dimension(200, 50));
    restartButton.setPreferredSize(new Dimension(100, 30));
    yesNoPanel.setLayout(new FlowLayout());
    super.nextButton.setText("No");
    yesNoPanel.add(super.nextButton);
    yesNoPanel.add(this.restartButton);
    comboGridPanel.setLayout(new BoxLayout(comboGridPanel, BoxLayout.Y_AXIS));
    comboGridPanel.add(textPane);
    comboGridPanel.add(yesNoPanel);
    super.comboPanel.add(comboGridPanel);
    super.controlPanel.remove(nextButton);
  }

  public ResultsPanel(String question, BaseApplication ba)
  {
    super(question, ba);
    
    this.tryAnotherButton.addActionListener(this);
    this.returnButton.addActionListener(this);
    this.nextButton.addActionListener(this);
    this.restartButton.addActionListener(this);
    
    configureResults();
    
    this.db = new SQLDatabase(IS_ONLINE);

  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    switch (e.getActionCommand())
    {
      case "Return":
        this.baseApp.handleReturn();
        break;
      case "No":
        this.handleNext();
        break;
      case "Yes":
        this.restartButton.setText("Start over?");
        break;
      case "Try another?":
        super.controlPanel.add(super.returnButton);
        super.controlPanel.add(super.nextButton);
        super.controlPanel.add(restartButton);
        super.controlPanel.remove(tryAnotherButton);
        super.text.setFont(new Font("Verdana", Font.BOLD, 30));
        super.text.setText("Is this your bird?");
      case "Start over?":
        set = null;
        this.restartButton.setText("Yes");
        this.baseApp.handleRestart();
        break;
      default:
        break;
    }
  }

  protected void buildResults()
  {
//    String select = qb.buildQuery();
//    String from = "from bird";
//    String where = "";
//    String conditions = "";
//
//    int fieldsEntered = 0;
//    for (int i = 0; i < 15; i++)
//    {
//      if (this.baseApp.userChoices[i] != null)
//      {
//        if (this.baseApp.userChoices[i] != "----")
//        {
//          String text = this.baseApp.userChoices[i].toLowerCase();
//          if (fieldsEntered > 0)
//          {
//            conditions = conditions + " and ";
//          }
//          switch (i)
//          {
//            case (0):
//              conditions = conditions + "season='" + text + "'";
//              fieldsEntered++;
//              break;
//            case (1):
//              conditions = conditions + "size='" + text + "'";
//              fieldsEntered++;
//              break;
//            case (2):
//              conditions = conditions + "crown='" + text + "'";
//              fieldsEntered++;
//              break;
//            case (3):
//              conditions = conditions + "supercilium='" + text + "'";
//              fieldsEntered++;
//              break;
//            case (4):
//              conditions = conditions + "eyestripe='" + text + "'";
//              fieldsEntered++;
//              break;
//            case (5):
//              conditions = conditions + "auriculars='" + text + "'";
//              fieldsEntered++;
//              break;
//            case (6):
//              conditions = conditions + "beak_shape='" + text + "'";
//              break;
//            case (7):
//              conditions = conditions + "beak_length='" + text + "'";
//              fieldsEntered++;
//              break;
//            case (8):
//              conditions = conditions + "beak_color='" + text + "'";
//              fieldsEntered++;
//              break;
//            case (9):
//              conditions = conditions + "throat='" + text + "'";
//              fieldsEntered++;
//              break;
//            case (10):
//              conditions = conditions + "breast='" + text + "'";
//              fieldsEntered++;
//              break;
//            case (11):
//              conditions = conditions + "coverts='" + text + "'";
//              fieldsEntered++;
//              break;
//            case (12):
//              conditions = conditions + "wing='" + text + "'";
//              fieldsEntered++;
//              break;
//            case (13):
//              conditions = conditions + "foot_shape='" + text + "'";
//              fieldsEntered++;
//              break;
//            case (14):
//              conditions = conditions + "foot_color='" + text + "'";
//              fieldsEntered++;
//              break;
//            default:
//              System.out.println("error - unreachable switch case found");
//              break;
//          }
//        }
//      }
//    }
    
//    if (fieldsEntered != 0)
//    {
//      where = " where ";
//    }
//
    String query = qb.buildQuery();
//    System.out.println(query);
    ResultSet matches = this.db.getResultsFromQuery(query);
    this.set = matches;
    textPane.setText("");
    configureResults();
    try
    {
      if (!set.isBeforeFirst())
      {
        super.text.setFont(new Font("Verdana", Font.BOLD, 20));
        super.text.setText("Oops! That bird doesn't seem to exist.");
        this.textPane.setText("Doesn't exist");
        super.image.setIcon(ImageReader.downloadImage("https://i.redd.it/thlztdwby2ub1.jpg"));

        super.controlPanel.remove(restartButton);
        super.controlPanel.remove(super.nextButton);
        super.controlPanel.remove(super.returnButton);
        super.comboPanel.remove(comboGridPanel);
        super.controlPanel.add(tryAnotherButton);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }

    handleNext();
  }

  public JPanel getPanel()
  {
    return this.panel;
  }

  protected void handleNext()
  {
    if (set != null)
    {
      try
      {
        if (set.next())
        {
          this.textPane.setText(set.getString(1));
          super.image.setIcon(ImageReader.downloadImage(set.getString(2)));
        }
        else
        {
          if (set.first())
          {
            this.textPane.setText(set.getString(1));
            super.image.setIcon(ImageReader.downloadImage(set.getString(2)));
          }
        }
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      System.out.println("ResultSet is null. Something must be wrong.");
    }
  }
}
