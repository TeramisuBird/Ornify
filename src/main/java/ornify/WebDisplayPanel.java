package ornify;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLayeredPane;

public class WebDisplayPanel
{

  public WebDisplayPanel(BaseApplication ba)
  {
    if (Model.endResult.get(2) != null)
    {
      WebBrowser browser = new WebBrowser("https://www.allaboutbirds.org/guide/"+Model.endResult.get(0)+"/overview", ba);
      Model.overlay = ba.getLayeredPane();
      JLayeredPane layeredPane = new JLayeredPane();
      layeredPane.setLayout(new BorderLayout());
      layeredPane.setPreferredSize(new Dimension(1400, 1000));
      layeredPane.add(browser);
      ba.setLayeredPane(layeredPane);
    }
  }

}
