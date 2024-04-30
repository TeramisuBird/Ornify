package ornify;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLayeredPane;

public class WebDisplayPanel
{
  BaseApplication ba;

  public WebDisplayPanel(BaseApplication ba)
  {
    this.ba = ba;
    initialize();
  }

  public void initialize()
  {
    if (ba.model.endResult.get(2) != null)
    {
      WebBrowser.infoURL = "https://www.allaboutbirds.org/guide/" + ba.model.endResult.get(0)
          + "/overview";
      WebBrowser.soundURL = WebBrowser.getiframe(ba.model.endResult.get(2));
      ba.model.browser = new WebBrowser(WebBrowser.soundURL, ba);
      ba.model.overlay = ba.getLayeredPane();
      JLayeredPane layeredPane = new JLayeredPane();
      layeredPane.setLayout(new BorderLayout());
      layeredPane.setPreferredSize(new Dimension(1400, 1000));
      layeredPane.add(ba.model.browser);
      ba.setLayeredPane(layeredPane);
    }
  }

  public void refresh()
  {
    if (ba.model.endResult.get(2) != null)
    {
      WebBrowser.linkButton.setText("Get more info");
      WebBrowser.isFirstPage = true;
      WebBrowser.infoURL = "https://www.allaboutbirds.org/guide/" + ba.model.endResult.get(0)
          + "/overview";
      WebBrowser.soundURL = WebBrowser.getiframe(ba.model.endResult.get(2));
      ba.model.browser.browse(WebBrowser.soundURL);
      ba.model.overlay = ba.getLayeredPane();
      JLayeredPane layeredPane = new JLayeredPane();
      layeredPane.setLayout(new BorderLayout());
      layeredPane.setPreferredSize(new Dimension(1400, 1000));
      layeredPane.add(ba.model.browser);
      ba.setLayeredPane(layeredPane);
    }
  }

}
