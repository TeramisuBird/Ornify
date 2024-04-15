package ornify;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class ResultsPanel implements ActionListener
{
  // private static final long serialVersionUID = -167346073358446237L;
  private static final String STARTING_TEXT = "Possible matches are:\n";
  public static final int WIDTH = 600;
  public static final int HEIGHT = 600;
  
  private SQLDatabase db;
  public JTextPane textPane;
  private JPanel resultDisplay;
  private JLabel pictureDisplay;
  private JLabel questionDisplay;
  
  public JButton returnButton;
  public JButton nextButton;
  public JButton restartButton;
  public JButton tryAnotherButton;
  public JPanel panel;
  public BaseApplication baseApp;
  public String matchesText;
  
  private ResultSet set;
  
  public ResultsPanel(String question, BaseApplication ba)
  {
    this.db = new SQLDatabase(true);
    this.matchesText = STARTING_TEXT;
    this.baseApp = ba;
    this.panel = new JPanel();
    this.panel.setLayout(null);
    this.panel.setBounds(0, 0, WIDTH, HEIGHT);
    
    String html = "<html><body style='width: %1spx'>%1s";
    
    questionDisplay = new JLabel(String.format(html, 300, question));
    questionDisplay.setHorizontalAlignment(SwingConstants.CENTER);
    questionDisplay.setFont(new Font("Verdana", Font.BOLD, 30));
    questionDisplay.setBounds((WIDTH / 2) - 200, 50, 500, 100);
    
    this.tryAnotherButton = new JButton("Try another?");
    this.tryAnotherButton.setBounds((WIDTH / 2) - 50, (HEIGHT / 2) + 200, 100, 50);
    this.tryAnotherButton.addActionListener(this);
    
    this.returnButton = new JButton("Previous");
    this.returnButton.setBounds((WIDTH / 2) + 100, (HEIGHT / 2) + 100, 100, 50);
    this.returnButton.addActionListener(this);
    
    this.nextButton = new JButton("No");
    this.nextButton.setBounds((WIDTH / 2) - 100, (HEIGHT / 2) + 100, 100, 50);
    this.nextButton.setVisible(false);
    this.nextButton.addActionListener(this);
    
    this.restartButton = new JButton("Yes");
    this.restartButton.setBounds((WIDTH / 2) - 50, (HEIGHT / 2) + 200, 100, 50);
    this.restartButton.addActionListener(this);
    
    
    this.textPane = new JTextPane();
    this.pictureDisplay = new JLabel();
    this.resultDisplay = new JPanel();
    resultDisplay.setBackground(new Color(255, 255, 255));
    this.resultDisplay.add(textPane);
    this.resultDisplay.add(pictureDisplay);
    
    this.panel.setBackground(BaseApplication.background_color);
    this.panel.add(questionDisplay);
    this.panel.add(this.returnButton);
    this.panel.add(this.restartButton);
    this.panel.add(nextButton);
    this.panel.setVisible(true);
  }
  
  @Override
  public void actionPerformed(ActionEvent e)
  {
    switch (e.getActionCommand())
    {
      case "Previous":
        this.panel.remove(resultDisplay);
        this.nextButton.setVisible(false);
        this.panel.repaint();
        this.matchesText = STARTING_TEXT;
        this.baseApp.handleReturn();
        break;
      case "No":
        this.handleNext();
        break;
      case "Yes":
        this.restartButton.setText("Start over?");
        break;
      case "Try another?":
        this.panel.add(returnButton);
        this.panel.add(nextButton);
        this.panel.add(restartButton);
        this.panel.remove(tryAnotherButton);
        this.questionDisplay.setFont(new Font("Verdana", Font.BOLD, 30));
        this.questionDisplay.setText("Is this your bird?");
      case "Start over?":
        this.panel.remove(resultDisplay);
        this.nextButton.setVisible(false);
        this.panel.repaint();
        this.matchesText = STARTING_TEXT;
        this.baseApp.handleRestart();
        break;
      default:
        break;
    }
  }
  
  protected void buildResults() {
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

    ResultSet matches = this.db.getResultsFromQuery(query);
    this.set = matches;
    
    this.textPane.setBounds((WIDTH / 2) - 260, (HEIGHT / 2) - 55, 100, 100);
    this.textPane.setVisible(true);
    
    this.pictureDisplay.setBounds((WIDTH / 2), (HEIGHT / 2) - 55, 300, 400);
    pictureDisplay.setIcon(null);
    textPane.setText("");
    try
    {
      if (!set.isBeforeFirst()) {
        this.questionDisplay.setFont(new Font("Verdana", Font.BOLD, 20));
        this.questionDisplay.setText("Oops! That bird doesn't seem to exist.");
        this.textPane.setText("Doesn't exist");
        URL url = new URL("https://i.redd.it/thlztdwby2ub1.jpg");
        BufferedImage img = ImageIO.read(url);
        Image tmp = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        ImageIcon icon = new ImageIcon(dimg);
        this.pictureDisplay.setIcon(icon);
        this.panel.remove(nextButton);
        this.panel.remove(returnButton);
        this.panel.remove(restartButton);
        this.panel.add(tryAnotherButton);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    catch (MalformedURLException e)
    {
      e.printStackTrace();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    handleNext();
    
    this.nextButton.setVisible(true);
    this.resultDisplay.setBounds((WIDTH / 2) - 260, (HEIGHT / 2) - 55, WIDTH - 80, 100);
    this.panel.add(resultDisplay);
    this.panel.repaint();
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
          URL url = new URL(set.getString(2));
          BufferedImage img = ImageIO.read(url);
          Image tmp = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
          BufferedImage dimg = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
          Graphics2D g2d = dimg.createGraphics();
          g2d.drawImage(tmp, 0, 0, null);
          g2d.dispose();
          
          ImageIcon icon = new ImageIcon(dimg);
          this.pictureDisplay.setIcon(icon);
        }
        else
        {
          if (set.first())
          {
            this.textPane.setText(set.getString(1));
            URL url = new URL(set.getString(2));
            BufferedImage img = ImageIO.read(url);
            Image tmp = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            BufferedImage dimg = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = dimg.createGraphics();
            g2d.drawImage(tmp, 0, 0, null);
            g2d.dispose();
            
            ImageIcon icon = new ImageIcon(dimg);
            this.pictureDisplay.setIcon(icon);
          }
        }
      }
      catch (MalformedURLException e)
      {
        e.printStackTrace();
      }
      catch (IOException e)
      {
        e.printStackTrace();
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
