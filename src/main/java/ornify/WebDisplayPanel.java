package ornify;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLayeredPane;

/**
 * Creates a WebsiteDisplayPanel to render.
 * 
 * @author Joseph Blethen
 * @version 1.0
 * 
 *          This work complies with the JMU Honor Code.
 *
 */
public class WebDisplayPanel
{

  private BaseApplication ba;

  /**
   * The parameterized constructor of the WebDisplayPanel.
   * 
   * @param ba
   *          the BaseApplication that this program is running on.
   */
  public WebDisplayPanel(final BaseApplication ba)
  {
    this.ba = ba;
    initialize();
  }

  /**
   * The initializer of this program. Can only be ran once.
   */
  private void initialize()
  {
    if (ba.getModel().getEndResult().get(2) != null)
    {
      ba.getModel().setBrowser(new WebBrowser(ba));
      ba.getModel().setOverlay(ba.getLayeredPane());
      JLayeredPane layeredPane = new JLayeredPane();
      layeredPane.setLayout(new BorderLayout());
      layeredPane.setPreferredSize(new Dimension(1400, 1000));
      layeredPane.add(ba.getModel().getBrowser());
      ba.setLayeredPane(layeredPane);
    }
  }

  /**
   * Refreshes this program's web browser when called.
   */
  public void refresh()
  {
    if (ba.getModel().getEndResult().get(2) != null)
    {
      ba.getModel().getBrowser().handleBrowserReload();
      ba.getModel().setOverlay(ba.getLayeredPane());
      JLayeredPane layeredPane = new JLayeredPane();
      layeredPane.setLayout(new BorderLayout());
      layeredPane.setPreferredSize(new Dimension(1400, 1000));
      layeredPane.add(ba.getModel().getBrowser());
      ba.setLayeredPane(layeredPane);
    }
  }

}
