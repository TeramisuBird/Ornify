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
  public static String infoURL;
  public static String soundURL;
  public static int web_WIDTH;
  public static int web_HEIGHT;
  public static boolean isFirstPage = true;
  private static final long serialVersionUID = 5223405231958560665L;
  public static JButton linkButton = new JButton("Get more info");
  private JFXPanel jfxPanel;
  private JPanel controlPanel;
  private Stage stage;
  private WebView browser;
  private WebEngine engine;
  private BaseApplication ba;

  /**
   * The constructor of this web browser.
   */
  public WebBrowser(BaseApplication ba)
  {
    this("http://www.google.com", ba);
  }

  public WebBrowser(String url, BaseApplication ba)
  {
    this.ba = ba;
    jfxPanel = new JFXPanel();
    Platform.setImplicitExit(false);
    ba.model.thread = new Thread(() -> Platform.runLater(() -> {
      stage = new Stage();
      stage.setResizable(true);
      Group root = new Group();
      Scene scene = new Scene(root, 80, 20);
      stage.setScene(scene);
      browser = new WebView();
      engine = browser.getEngine();
      engine.load(url);
      ObservableList<Node> children = root.getChildren();
      children.add(browser);
      jfxPanel.setScene(scene);
    }));
    ba.model.thread.start();
    this.setLayout(new BorderLayout());
    this.add(jfxPanel, BorderLayout.CENTER);
    controlPanel = new JPanel();
    controlPanel.setLayout(new FlowLayout());
    controlPanel = initButtons(controlPanel);
    this.add(controlPanel, BorderLayout.SOUTH);
  }

  /**
   * Given a url, loads the webpage.
   * 
   * @param url
   *          The url to go to.
   */
  public void browse(String url)
  {
    Platform.runLater(() -> {
      engine.load(url);
    });
  }

  /**
   * Parses a given iframe for a src file and loads it in this web browser.
   * 
   * @param iframeURL
   *          The full html iframe element.
   */
  public static String getiframe(String iframeURL)
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
   * @param panel
   *          The JPanel to populate with buttons.
   * @return A JPanel fully outfitted with buttons.
   */
  private JPanel initButtons(JPanel panel)
  {
    // Refresh button
    JButton refreshButton = new JButton("Reload");
    refreshButton.addActionListener((ActionEvent e) -> {
      Platform.runLater(() -> engine.reload());
    });
    panel.add(refreshButton);

    // Link button
    linkButton.addActionListener((ActionEvent e) -> {
      Platform.runLater(() -> {
        if (isFirstPage)
        {
          this.setPreferredSize(new Dimension(web_WIDTH, web_HEIGHT));
          linkButton.setText("Get more info");
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
    restartButton.addActionListener((ActionEvent e) -> {
      ba.setLayeredPane(ba.model.overlay);
      ba.handleRestart();
    });
    panel.add(restartButton);
    return panel;
  }
}
