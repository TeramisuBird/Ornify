package ornify;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLayeredPane;

/**
 * Class for displaying the web panel.
 */
public class WebDisplayPanel
{
  BaseApplication baseApp;
  
  private String over = "/overview";
  private String url = "https://www.allaboutbirds.org/guide/";

  /**
   * Constructor.
   * @param ba
   */
  public WebDisplayPanel(final BaseApplication ba)
  {
    this.baseApp = ba;
    initialize();
  }

  /**
   * Method to initialize the panel.
   */
  public void initialize()
  {
    if (Model.endResult.get(2) != null)
    {
      WebBrowser.infoURL = url + Model.endResult.get(0)
          + over;
      WebBrowser.soundURL = WebBrowser.getiframe(Model.endResult.get(2));
      Model.browser = new WebBrowser(WebBrowser.soundURL, baseApp);
      Model.overlay = baseApp.getLayeredPane();
      JLayeredPane layeredPane = new JLayeredPane();
      layeredPane.setLayout(new BorderLayout());
      layeredPane.setPreferredSize(new Dimension(1400, 1000));
      layeredPane.add(Model.browser);
      baseApp.setLayeredPane(layeredPane);
    }
  }

  /**
   * Method to refresh the panel.
   */
  public void refresh()
  {
    if (Model.endResult.get(2) != null)
    {
      WebBrowser.linkButton.setText("Get more info");
      WebBrowser.isFirstPage = true;
      WebBrowser.infoURL = url + Model.endResult.get(0)
          + over;
      WebBrowser.soundURL = WebBrowser.getiframe(Model.endResult.get(2));
      Model.browser.browse(WebBrowser.soundURL);
      Model.overlay = baseApp.getLayeredPane();
      JLayeredPane layeredPane = new JLayeredPane();
      layeredPane.setLayout(new BorderLayout());
      layeredPane.setPreferredSize(new Dimension(1400, 1000));
      layeredPane.add(Model.browser);
      baseApp.setLayeredPane(layeredPane);
    }
  }

}
