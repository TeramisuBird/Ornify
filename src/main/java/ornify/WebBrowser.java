package ornify;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.StringTokenizer;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * A fully functioning web browser to be integrated into the JPanel.
 * <p>
 * Uses JavaFX.
 * <p>
 * WARNING: DO NOT CLOSE BEFORE FULLY LOADING.
 * 
 * @author Joseph Blethen
 * @version 21.1.0
 */
public class WebBrowser extends JPanel
{
  public static final String INFO_MESSAGE = "Get more info";
  private static final String WEBSITE_DOMAIN = "https://www.allaboutbirds.org/guide/";
  private static final String WEBSITE_PAGE = "/overview";
  private static int web_WIDTH;
  private static int web_HEIGHT;
  private static final long serialVersionUID = 5223405231958560665L;
  private JButton linkButton = new JButton(INFO_MESSAGE);
  private JFXPanel jfxPanel;
  private JPanel controlPanel;
  private Stage stage;
  private WebView browser;
  private WebEngine engine;
  private BaseApplication ba;
  private boolean isFirstPage = true;
  private String infoURL;
  private String soundURL = "http://www.google.com";
  

  /**
   * The default constructor of this web browser.
   * 
   * @param ba
   *          The BaseApplication object that this program runs on.
   */
  public WebBrowser(final BaseApplication ba)
  {
    this.ba = ba;
    handleInit();
    jfxPanel = new JFXPanel();
    Platform.setImplicitExit(false);
    ba.getModel().setThread(new Thread(() -> Platform.runLater(() -> 
    {
      stage = new Stage();
      stage.setResizable(true);
      Group root = new Group();
      Scene scene = new Scene(root, 80, 20);
      stage.setScene(scene);
      browser = new WebView();
      engine = browser.getEngine();
      engine.load(soundURL);
      ObservableList<Node> children = root.getChildren();
      children.add(browser);
      jfxPanel.setScene(scene);
    })));
    ba.getModel().getThread().start();
    this.setLayout(new BorderLayout());
    this.add(jfxPanel, BorderLayout.CENTER);
    controlPanel = new JPanel();
    controlPanel.setLayout(new FlowLayout());
    controlPanel = initButtons(controlPanel);
    this.add(controlPanel, BorderLayout.SOUTH);
  }
  
  /**
   * Initializes this browser.
   */
  private void handleInit()
  {
    Model model = ba.getModel();
    String info = WEBSITE_DOMAIN + model.getEndResult().get(0) + WEBSITE_PAGE;
    String sound = getiframe(model.getEndResult().get(2));
    this.infoURL = info;
    this.soundURL = sound;
  }
  
  /**
   * Handles this browser's reload.
   */
  public void handleBrowserReload() 
  {
    Model model = ba.getModel();
    String info = WEBSITE_DOMAIN + model.getEndResult().get(0) + WEBSITE_PAGE;
    String sound = getiframe(model.getEndResult().get(2));
    linkButton.setText(INFO_MESSAGE);
    isFirstPage = true;
    this.infoURL = info;
    this.soundURL = sound;
    browse(soundURL);
  }

  /**
   * Sets the URL of the info page to browse.
   * 
   * @param infoURL
   *          the infoURL to set
   */
  public final void setInfoURL(final String infoURL)
  {
    this.infoURL = infoURL;
  }

  /**
   * Sets the URL of the sound file page to play.
   * 
   * @param soundURL
   *          the soundURL to set
   */
  public final void setSoundURL(final String soundURL)
  {
    this.soundURL = soundURL;
  }

  /**
   * Given a url, loads the webpage.
   * 
   * @param url
   *          The url to go to.
   */
  public void browse(final String url)
  {
    Platform.runLater(() -> 
    {
      engine.load(url);
    });
  }

  /**
   * Parses a given iframe for a src file and loads it in this web browser.
   * 
   * @param iframeURL
   *          The full html iframe element.
   * @return The string of the found url.
   */
  public static String getiframe(final String iframeURL)
  {
    StringTokenizer tokens = new StringTokenizer(iframeURL, "\"");
    tokens.nextToken();
    String url = tokens.nextToken();
    tokens.nextToken();
    int height = Integer.valueOf(tokens.nextToken());
    tokens.nextToken();
    int width = Integer.valueOf(tokens.nextToken());
    web_WIDTH = width;
    web_HEIGHT = height;
    return url;
  }

  /**
   * Initializes buttons to test with. Must have FlowLayout.
   * 
   * @param given
   *          The JPanel to populate with buttons.
   * @return A JPanel fully outfitted with buttons.
   */
  private JPanel initButtons(final JPanel given)
  {
    JPanel panel = given;
    // Refresh button
    JButton refreshButton = new JButton("Reload");
    refreshButton.addActionListener((ActionEvent e) -> 
    {
      Platform.runLater(() -> engine.reload());
    });
    panel.add(refreshButton);

    // Link button
    linkButton.addActionListener((ActionEvent e) -> 
    {
      Platform.runLater(() -> 
      {
        if (isFirstPage)
        {
          this.setPreferredSize(new Dimension(web_WIDTH, web_HEIGHT));
          linkButton.setText(INFO_MESSAGE);
          browse(soundURL);
          isFirstPage = false;
        }
        else
        {
          this.setPreferredSize(new Dimension(1400, 1000));
          linkButton.setText("Hear bird call");
          browse(infoURL);
          isFirstPage = true;
        }
      });
    });
    panel.add(linkButton);

    // Start over button
    JButton restartButton = new JButton("Start over?");
    restartButton.addActionListener((ActionEvent e) -> 
    {
      ba.setLayeredPane(ba.getModel().getOverlay());
      ba.handleRestart();
    });
    panel.add(restartButton);
    return panel;
  }
}
