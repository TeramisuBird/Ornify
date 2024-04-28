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
  
  public void initialize() {
    if (Model.endResult.get(2) != null)
    {
      WebBrowser.savedURL = "https://www.allaboutbirds.org/guide/"+Model.endResult.get(0)+"/overview";
      Model.browser = new WebBrowser("https://www.allaboutbirds.org/guide/"+Model.endResult.get(0)+"/overview", baseApp);
      Model.overlay = baseApp.getLayeredPane();
      JLayeredPane layeredPane = new JLayeredPane();
      layeredPane.setLayout(new BorderLayout());
      layeredPane.setPreferredSize(new Dimension(1400, 1000));
      layeredPane.add(Model.browser);
      baseApp.setLayeredPane(layeredPane);
    }
  }
  
  public void refresh() {
    if (Model.endResult.get(2) != null)
    {
      WebBrowser.savedURL = "https://www.allaboutbirds.org/guide/"+Model.endResult.get(0)+"/overview";
      Model.browser.browse("https://www.allaboutbirds.org/guide/"+Model.endResult.get(0)+"/overview");
      Model.overlay = baseApp.getLayeredPane();
      JLayeredPane layeredPane = new JLayeredPane();
      layeredPane.setLayout(new BorderLayout());
      layeredPane.setPreferredSize(new Dimension(1400, 1000));
      layeredPane.add(Model.browser);
      Model.browser.showStage();
      baseApp.setLayeredPane(layeredPane);
    }
  }

}
