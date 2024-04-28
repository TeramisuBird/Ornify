package ornify;

import com.sun.javafx.application.PlatformImpl;
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
  private static final long serialVersionUID = 5223405231958560665L;
  private JFXPanel jfxPanel;
  private JPanel controlPanel;
  private Stage stage;
  private WebView browser;
  private WebEngine engine;
  private BaseApplication ba;

  /**
   * The constructor of this web browser.
   */
  public WebBrowser()
  {
    this("http://www.google.com");
  }
  
  public WebBrowser(String url, BaseApplication ba) {
    this(url);
    this.ba = ba;
  }
  
  public WebBrowser(String url)
  {
    jfxPanel = new JFXPanel();
    PlatformImpl.startup(() -> {
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
    });
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
    engine.load(url);
  }

  /**
   * Parses a given iframe for a src file and loads it in this web browser.
   * 
   * @param iframeURL
   *          The full html iframe element.
   */
  public void iframe(String iframeURL)
  {
    StringTokenizer tokens = new StringTokenizer(iframeURL, "\"");
    tokens.nextToken();
    String url = tokens.nextToken();
    tokens.nextToken();
    int height = Integer.valueOf(tokens.nextToken());
    tokens.nextToken();
    int width = Integer.valueOf(tokens.nextToken());
    this.setPreferredSize(new Dimension(width, height));
    browse(url);
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

    // iframe test button
    JButton iframeTestButton = new JButton("iframe Test");
    iframeTestButton.addActionListener((ActionEvent e) -> {
      Platform.runLater(() -> iframe(
          Model.endResult.get(2)));
    });
    panel.add(iframeTestButton);
    
    // Start over button
    JButton restartButton = new JButton("Start over?");
    restartButton.addActionListener((ActionEvent e) -> {
      Platform.runLater(() -> {
        ba.setLayeredPane(Model.overlay);
        ba.handleRestart();
      });
    });
    panel.add(restartButton);
    return panel;
  }
}
