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

/**
 * Class that creates a results panel.
 */
public class ResultsPanel extends CustomPanel implements ActionListener
{
  public static final boolean IS_ONLINE = true;

  protected String verdana = "Verdana";
  protected String no = "No";
  protected String yes = "Yes";
  protected String another = "Try another?";

  protected JButton restartButton = new JButton(yes);
  protected JButton tryAnotherButton = new JButton(another);
  protected QueryBuilder qb = new QueryBuilder();
  protected String matchesText;
  protected JTextPane textPane = new JTextPane();

  private SQLDatabase db;
  private JPanel yesNoPanel = new JPanel();
  private JPanel comboGridPanel = new JPanel();
  private BaseApplication ba;
  private WebDisplayPanel browserOverlay;

  private ResultSet set;
  private ResultSetMetaData meta;
  private int columnCount = 3;

  /**
   * A parameterized constructor.
   * 
   * @param question The question to display to the screen.
   * @param ba The base application.
   */
  public ResultsPanel(final String question, final BaseApplication ba)
  {
    super(question, ba);
    this.ba = ba;
    this.tryAnotherButton.addActionListener(this);
    this.returnButton.addActionListener(this);
    this.nextButton.addActionListener(this);
    this.restartButton.addActionListener(this);

    configureResults();

    this.db = new SQLDatabase(IS_ONLINE);
  }

  /**
   * Method that configures the results.
   */
  private void configureResults()
  {
    textPane.setPreferredSize(new Dimension(200, 50));
    restartButton.setPreferredSize(new Dimension(100, 30));
    yesNoPanel.setLayout(new FlowLayout());
    super.nextButton.setText(no);
    yesNoPanel.add(super.nextButton);
    yesNoPanel.add(this.restartButton);
    comboGridPanel.setLayout(new BoxLayout(comboGridPanel, BoxLayout.Y_AXIS));
    comboGridPanel.add(textPane);
    comboGridPanel.add(yesNoPanel);
    super.comboPanel.add(comboGridPanel);
    super.controlPanel.remove(nextButton);
  }

  /**
   * Method that populates the results.
   */
  private void populateEndResult()
  {
    try
    {
      meta = set.getMetaData();
      columnCount = meta.getColumnCount();
      for (int i = 1; i <= columnCount; i++)
      {
        ba.getModel().getEndResult().add(set.getString(i));
        System.out.println(set.getString(i));
      }

    }
    catch (SQLException e)
    {
      System.out.println("ERROR: Result set and Metadata set column mismatch.");
      e.printStackTrace();
    }
  }

  /**
   * Method that checks for action performed.
   * 
   * @param e
   *          the action
   */
  @Override
  public void actionPerformed(final ActionEvent e)
  {
    String action = e.getActionCommand();

    if (action.equals("Return"))
    {
      this.baseApp.handleReturn();
    }
    else if (action.equals(no))
    {
      this.handleNext();
    }
    else if (action.equals(yes))
    {
      populateEndResult();
      this.restartButton.setText("Loading please wait...");
      set = null;
      if (browserOverlay == null)
      {
        browserOverlay = new WebDisplayPanel(ba);
        System.out.println("Created a new browser!");
      }
      else
      {
        browserOverlay.refresh();
        System.out.println("Refreshed browser!");
      }
      this.restartButton.setText(yes);
    }
    else if (action.equals(another))
    {
      super.controlPanel.add(super.returnButton);
      super.controlPanel.add(super.nextButton);
      super.controlPanel.add(restartButton);
      super.controlPanel.remove(tryAnotherButton);
      super.text.setFont(new Font(verdana, Font.BOLD, 30));
      super.text.setText("Is this your bird?");

      restart();
    }
    else if (action.equals("Start over?"))
    {
      restart();
    }
  }

  /**
   * Method that restarts the application.
   */
  private void restart()
  {
    set = null;
    this.restartButton.setText(yes);
    this.baseApp.handleRestart();
  }

  /**
   * Method that builds the results.
   */
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
        super.text.setFont(new Font(verdana, Font.BOLD, 20));
        super.text.setText("Oops! That bird doesn't seem to exist.");
        this.textPane.setText("Doesn't exist");
        super.image.setIcon(
            ba.getModel().getReader().downloadImage("https://i.redd.it/thlztdwby2ub1.jpg"));
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

  /**
   * Method that gets the panel.
   * 
   * @return the panel
   */
  public JPanel getPanel()
  {
    return this.panel;
  }

  /**
   * Method that handles the next transition.
   */
  protected void handleNext()
  {
    if (set != null)
    {
      try
      {
        if (set.next())
        {
          this.textPane.setText(set.getString(1));
          super.image.setIcon(ba.getModel().getReader().downloadImage(set.getString(2)));
        }
        else
        {
          if (set.first())
          {
            this.textPane.setText(set.getString(1));
            super.image.setIcon(ba.getModel().getReader().downloadImage(set.getString(2)));
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
