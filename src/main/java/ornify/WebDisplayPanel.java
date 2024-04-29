package ornify;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLayeredPane;

public class WebDisplayPanel
{
  BaseApplication baseApp;

  public WebDisplayPanel(BaseApplication ba)
  {
    this.baseApp = ba;
    initialize();
  }

  public void initialize()
  {
    if (Model.endResult.get(2) != null)
    {
      WebBrowser.infoURL = "https://www.allaboutbirds.org/guide/" + Model.endResult.get(0)
          + "/overview";
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

  public void refresh()
  {
    if (Model.endResult.get(2) != null)
    {
      WebBrowser.linkButton.setText("Get more info");
      WebBrowser.isFirstPage = true;
      WebBrowser.infoURL = "https://www.allaboutbirds.org/guide/" + Model.endResult.get(0)
          + "/overview";
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
