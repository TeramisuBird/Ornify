package ornify;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class ResultsPanel extends CustomPanel implements ActionListener
{
  public static final boolean IS_ONLINE = true;

  private SQLDatabase db;
  public JTextPane textPane = new JTextPane();
  private JPanel yesNoPanel = new JPanel();
  private JPanel comboGridPanel = new JPanel();
  public JButton restartButton = new JButton("Yes");
  public JButton tryAnotherButton = new JButton("Try another?");
  public QueryBuilder qb = new QueryBuilder();
  public String matchesText;
  private BaseApplication ba;

  private ResultSet set;
  private ResultSetMetaData meta;
  private int columnCount = 3;

  private void configureResults()
  {
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
    this.ba = ba;

  }

  private void populateEndResult()
  {
    try
    {
      meta = set.getMetaData();
      columnCount = meta.getColumnCount();
      for (int i = 1; i <= columnCount; i++) {
        Model.endResult.add(set.getString(i));
        System.out.println(set.getString(i));
      }
        
         
    }
    catch (SQLException e)
    {
      System.out.println("ERROR: Result set and Metadata set column mismatch.");
      e.printStackTrace();
    }
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
        populateEndResult();
        this.restartButton.setText("Start over?");
        set = null;
        new WebDisplayPanel(ba);
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
    String query = qb.buildQuery();
    System.out.println(query);
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
