package ornify;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import visual.statik.described.Content;

public class BeakLenPanel extends CustomPanel implements ChangeListener
{
  private JSlider slider;
  private JLabel value;
  private String currentOption;
  
  private ImageIcon icon;
  
  private VisualPanel visual_panel;
  private Content top_beak;
  private Content bottom_beak;
  
  private Point2D[] pairs_top = {new Point2D.Double(0, 0), 
      new Point2D.Double(200, 10), new Point2D.Double(0, 10), new Point2D.Double(0, 0)};
  private Point2D[] pairs_bottom = {new Point2D.Double(0, 20), 
      new Point2D.Double(180, 10), new Point2D.Double(0, 10), new Point2D.Double(0, 20)};
  
  public BeakLenPanel(String question, BaseApplication ba)
  {
    super(question, ba);
    this.visual_panel = new VisualPanel();
    visual_panel.getView().setPreferredSize(new Dimension(200, 200));
    
    this.top_beak = new Content();
    top_beak.setColor(new Color(0, 0, 0));
    top_beak.setPaint(new Color(232, 232, 88));
    
    this.bottom_beak = new Content();
    bottom_beak.setColor(new Color(0, 0, 0));
    bottom_beak.setPaint(new Color(232, 232, 88));
    
    Polygon shape = createShape(1, pairs_top);
    top_beak.setShape(shape);
    
    Polygon shape_two = createShape(1, pairs_bottom);
    bottom_beak.setShape(shape_two);
    visual_panel.add(top_beak);
    visual_panel.add(bottom_beak);
    
    this.slider = new JSlider(1, 100, 100);
    slider.setPreferredSize(new Dimension(400, 50));
    slider.addChangeListener(this);
    
    this.value = new JLabel("The current value is: 50");
    value.setPreferredSize(new Dimension(200, 30));
    
    this.icon = new ImageIcon(Model.BEAKLEN_IMAGE);
    super.image.setIcon(icon);
    this.currentOption = null;
    
    super.questionPanel.add(visual_panel.getView(), BorderLayout.EAST);
    super.comboPanel.add(slider);
    super.comboPanel.add(value);
  }
  
  public String getOption()
  {
    return currentOption;
  }
  
  @Override
  public void actionPerformed(ActionEvent e)
  {
    switch (e.getActionCommand())
    {
      case "Return":
        this.baseApp.handleReturn();
        break;
      case "Next":
        this.baseApp.handleNext();
        break;
      default:
        break;
    }
  }

  @Override
  public void stateChanged(ChangeEvent e)
  {
    float val = slider.getValue();
    String size = "";
    
    if (inRange(slider.getValue(), 1, 33))
    {
      size = "Short";
    }
    else if (inRange(slider.getValue(), 3, 66))
    {
      size = "Average";
    }
    else
    {
      size = "Long";
    }
    
    value.setText("The current value is: " + size);
    val = val / 100;
    
    Polygon shape = createShape(val, this.pairs_top);
    this.top_beak.setShape(shape);
    
    Polygon shape_two = createShape(val, this.pairs_bottom);
    this.bottom_beak.setShape(shape_two);
    this.visual_panel.repaint();
  }
  
  private boolean inRange(int value, int start, int end)
  {
    if (value > start && value <= end)
    {
      return true;
    }
    else
    {
      return false;
    }
  }
  
  private Polygon createShape(float scale, Point2D[] points)
  {
    Polygon shape = new Polygon();
    
    for (int i = 0; i < points.length; i++)
    {
      if (i != 1)
      {
        shape.addPoint((int)points[i].getX(), (int)points[i].getY());
      }
      else
      {
        shape.addPoint((int)(points[i].getX() * scale), (int)points[i].getY());
      }
    }
    
    return shape;
  }
}
