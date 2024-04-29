package ornify;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Polygon;
import java.awt.geom.Ellipse2D;

import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import visual.Visualization;
import visual.statik.described.Content;

/**
 * Beak-Length question panel class.
 * 
 * This work complies with the JMU Honor Code.
 */
public class BeakLenPanel extends CustomPanel implements ChangeListener
{
  private static final String SHORT = "Short";
  private static final String AVERAGE = "Average";
  private static final String LONG = "Long";
  private static final String BEAK_LEN = "beak_length";
  
  private JSlider slider;
  private JLabel value;
  private String currentOption;

  private Visualization visualPanel;
  private Content topBeak;
  private Content bottomBeak;
  private Content headCon;
  private Content eyeCon;

  private Point2D[] pairsTop = {new Point2D.Double(280, 160), new Point2D.Double(480, 200),
      new Point2D.Double(280, 200), new Point2D.Double(280, 200)};
  private Point2D[] pairsBottom = {new Point2D.Double(280, 200), new Point2D.Double(480, 200),
      new Point2D.Double(280, 240), new Point2D.Double(280, 220)};

  /**
   * Panel constructor.
   * 
   * @param question
   *          for panel's question
   * @param ba
   *          for application to add panel to
   */
  public BeakLenPanel(final String question, final BaseApplication ba)
  {
    super(question, ba);
    this.visualPanel = new Visualization();
    visualPanel.getView().setPreferredSize(new Dimension(400, 400));

    this.topBeak = new Content();
    topBeak.setColor(new Color(0, 0, 0));
    topBeak.setPaint(new Color(232, 232, 88));

    this.bottomBeak = new Content();
    bottomBeak.setColor(new Color(0, 0, 0));
    bottomBeak.setPaint(new Color(232, 232, 88));

    this.headCon = new Content();
    headCon.setColor(new Color(0, 0, 0));
    headCon.setPaint(Color.RED);

    this.eyeCon = new Content();
    eyeCon.setColor(new Color(0, 0, 0));
    eyeCon.setPaint(new Color(0, 0, 0));

    Polygon shape = createShape(1, pairsTop);
    topBeak.setShape(shape);

    Polygon shapeTwo = createShape(1, pairsBottom);
    bottomBeak.setShape(shapeTwo);

    Ellipse2D head = new Ellipse2D.Double(100, 100, 200, 200);
    headCon.setShape(head);

    Ellipse2D eye = new Ellipse2D.Double(230, 140, 20, 20);
    eyeCon.setShape(eye);

    visualPanel.add(headCon);
    visualPanel.add(eyeCon);
    visualPanel.add(topBeak);
    visualPanel.add(bottomBeak);

    this.slider = new JSlider(1, 100, 100);
    slider.setPreferredSize(new Dimension(600, 50));
    slider.addChangeListener(this);

    this.value = new JLabel("The current value is: Long");
    value.setPreferredSize(new Dimension(200, 30));

    this.currentOption = LONG;

    super.questionPanel.add(visualPanel.getView(), BorderLayout.CENTER);
    super.comboPanel.add(slider);
    super.comboPanel.add(value);

    int index = Model.selectionIndicies.get(BEAK_LEN);
    Model.picked[index] = true;
    Model.selections[index] = "";
  }

  /**
   * getter for user's current selection.
   * 
   * @return currentOption
   */
  public String getOption()
  {
    return currentOption;
  }

  /**
   * Method that checks for an action performed.
   * @param e the action
   */
  @Override public void actionPerformed(final ActionEvent e)
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

  /**
   * Method that checks when the state is changed.
   * @param e the state
   */
  @Override public void stateChanged(final ChangeEvent e)
  {
    float val = slider.getValue();
    String size = "";

    if (inRange(slider.getValue(), 0, 33))
    {
      size = SHORT;
    }
    else if (inRange(slider.getValue(), 3, 66))
    {
      size = AVERAGE;
    }
    else
    {
      size = LONG;
    }

    int index = Model.selectionIndicies.get(BEAK_LEN);
    Model.selections[index] = "(beak_length ='" + size.toLowerCase() + "')";

    value.setText("The current value is: " + size);
    val = val / 100;

    Polygon shape = createShape(val, this.pairsTop);
    this.topBeak.setShape(shape);

    Polygon shapeTwo = createShape(val, this.pairsBottom);
    this.bottomBeak.setShape(shapeTwo);
    this.visualPanel.repaint();
  }

  /**
   * check if value is within some range.
   * 
   * @param newValue
   *          for value to check
   * @param start
   *          for range start (exclusive)
   * @param end
   *          for range end (inclusive)
   * @return result of range check
   */
  private boolean inRange(final int newValue, final int start, final int end)
  {
    return newValue > start && newValue <= end;
  }

  /**
   * make a polygon.
   * 
   * @param scale
   *          for scale of polygon
   * @param points
   *          for points of the polygon
   * @return created polygon
   */
  private Polygon createShape(final float scale, final Point2D[] points)
  {
    Polygon shape = new Polygon();

    for (int i = 0; i < points.length; i++)
    {
      if (i != 1)
      {
        shape.addPoint((int) points[i].getX(), (int) points[i].getY());
      }
      else
      {
        float lenDif = 150 - (150 * scale);
        shape.addPoint((int) (points[i].getX() - lenDif), (int) points[i].getY());
      }
    }

    return shape;
  }
}
