package ornify;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.JPanel;

public class ShapeTestPanel extends JPanel
{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  private Polygon poly;

  public ShapeTestPanel(Polygon poly)
  {
    this.poly = poly;
  }
  
  @Override
  protected void paintComponent(Graphics g){

    super.paintComponents(g);

    g.setColor(Color.BLACK);
//    g.drawPolygon(poly);
    g.fillPolygon(poly);
  } 
}
