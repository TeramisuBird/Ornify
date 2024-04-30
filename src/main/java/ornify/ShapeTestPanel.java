package ornify;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import io.ResourceFinder;
import resources.Marker;
import visual.Visualization;
import visual.statik.described.Content;

/**
 * Shape "Test" Panel (part 2 of 2 of Color Panel)
 * 
 * This work complies with the JMU Honor Code.
 */
public class ShapeTestPanel extends Visualization implements MouseListener
{
  private static final String FOOT = "foot";
  private static final String BEAK = "beak";

  private PolygonReader reader;
  private ResourceFinder jarFinder;
  private HashMap<Polygon, String> shapes;
  private HashMap<String, Pair<Polygon, Integer>> colorIndex;

  /**
   * constructor.
   */
  public ShapeTestPanel()
  {
    this.jarFinder = ResourceFinder.createInstance(new Marker());
    this.reader = new PolygonReader(jarFinder);
    this.shapes = new HashMap<Polygon, String>();
    this.colorIndex = new HashMap<String, Pair<Polygon, Integer>>();
    this.addMouseListener(this);
    this.getView().setSize(new Dimension(600, 600));
  }

  /**
   * read a single file into a polygon to add to the panel.
   * 
   * @param name
   *          for name of map file
   * @param bigOutline
   *          is polygon interactable?
   */
  public void read(final String name, final boolean bigOutline)
  {
    Content content = new Content();
    Polygon poly = reader.read(name + ".map");

    content.setShape(poly);
    content.setColor(new Color(180, 167, 214));
    content.setPaint(new Color(172, 91, 91));
    if (!bigOutline)
    {
      shapes.put(poly, name);
    }

    colorIndex.put(name, new Pair<Polygon, Integer>(poly, 0));
    this.add(content);
  }

  /**
   * re-draw all shapes into the panel.
   */
  private void redraw()
  {
    this.clear();
    Content con = new Content();
    String constReg1 = "full_bird";
    String constReg2 = "eyehole";
    Polygon poly = this.colorIndex.get(constReg1).getFirst();

    con.setShape(poly);
    con.setColor(new Color(180, 167, 214));
    con.setPaint(new Color(172, 91, 91));
    this.add(con);

    con = new Content();
    poly = this.colorIndex.get(constReg2).getFirst();
    con.setShape(poly);
    con.setColor(new Color(180, 167, 214));
    con.setPaint(new Color(172, 91, 91));
    this.add(con);

    for (String region : this.colorIndex.keySet())
    {
      if (!region.equals(constReg1) && !region.equals(constReg2))
      {
        con = new Content();
        poly = this.colorIndex.get(region).getFirst();
        int index = this.colorIndex.get(region).getSecond();

        con.setShape(poly);
        con.setColor(new Color(180, 167, 214));
        Color newPaint = null;
        String colorName = "";
        String add = "";

        switch (region)
        {
          case "crown":
            if (index >= Model.getCrownColors().size())
            {
              index = 0;
              colorIndex.put(region, new Pair<Polygon, Integer>(poly, index));
            }

            newPaint = Model.getCrownColors().get(index).getSecond();
            colorName = Model.getCrownColors().get(index).getFirst();
            break;
          case "supercilium":
            if (index >= Model.getSuperColors().size())
            {
              index = 0;
              colorIndex.put(region, new Pair<Polygon, Integer>(poly, index));
            }

            newPaint = Model.getSuperColors().get(index).getSecond();
            colorName = Model.getSuperColors().get(index).getFirst();
            break;
          case "eyestripe":
            if (index >= Model.getEyestrColors().size())
            {
              index = 0;
              colorIndex.put(region, new Pair<Polygon, Integer>(poly, index));
            }

            newPaint = Model.getEyestrColors().get(index).getSecond();
            colorName = Model.getEyestrColors().get(index).getFirst();
            break;
          case "auriculars":
            if (index >= Model.getAuricColors().size())
            {
              index = 0;
              colorIndex.put(region, new Pair<Polygon, Integer>(poly, index));
            }

            newPaint = Model.getAuricColors().get(index).getSecond();
            colorName = Model.getAuricColors().get(index).getFirst();
            break;
          case BEAK:
            if (index >= Model.getBeakColors().size())
            {
              index = 0;
              colorIndex.put(region, new Pair<Polygon, Integer>(poly, index));
            }

            newPaint = Model.getBeakColors().get(index).getSecond();
            colorName = Model.getBeakColors().get(index).getFirst();
            break;
          case "throat":
            if (index >= Model.getThroatColors().size())
            {
              index = 0;
              colorIndex.put(region, new Pair<Polygon, Integer>(poly, index));
            }

            newPaint = Model.getThroatColors().get(index).getSecond();
            colorName = Model.getThroatColors().get(index).getFirst();
            break;
          case "breast":
            if (index >= Model.getBreastColors().size())
            {
              index = 0;
              colorIndex.put(region, new Pair<Polygon, Integer>(poly, index));
            }

            newPaint = Model.getBreastColors().get(index).getSecond();
            colorName = Model.getBreastColors().get(index).getFirst();
            break;
          case "coverts":
            if (index >= Model.getCovertColors().size())
            {
              index = 0;
              colorIndex.put(region, new Pair<Polygon, Integer>(poly, index));
            }

            newPaint = Model.getCovertColors().get(index).getSecond();
            colorName = Model.getCovertColors().get(index).getFirst();
            break;
          case "wing":
            if (index >= Model.getWingColors().size())
            {
              index = 0;
              colorIndex.put(region, new Pair<Polygon, Integer>(poly, index));
            }

            newPaint = Model.getWingColors().get(index).getSecond();
            colorName = Model.getWingColors().get(index).getFirst();
            break;
          case FOOT:
            if (index >= Model.getFootColors().size())
            {
              index = 0;
              colorIndex.put(region, new Pair<Polygon, Integer>(poly, index));
            }

            newPaint = Model.getFootColors().get(index).getSecond();
            colorName = Model.getFootColors().get(index).getFirst();
            break;
          default:
            System.out.println("Unknown Bird Region - SHOULD NOT REACH");
            break;
        }

        if (region.equals(FOOT) || region.equals(BEAK))
        {
          add = "_color";
        }

        if (index == 0)
        {
          Model.getPicked()[Model.getIndicies().get(region + add)] = false;
        }
        else
        {
          Model.getPicked()[Model.getIndicies().get(region + add)] = true;

          String text = region + "='" + colorName + "'";
          Model.getSelections()[Model.getIndicies().get(region + add)] = text;
        }

        if (newPaint == null)
        {
          newPaint = new Color(172, 91, 91);
        }
        con.setPaint(newPaint);
        this.add(con);
      }
    }
  }

  /**
   * Method that checks for when mouse is clicked.
   * 
   * @param e
   *          the click
   */
  @Override
  public void mouseClicked(final MouseEvent e)
  {
    int xPos = e.getX();
    int yPos = e.getY();
    Point mousePosition = new Point(xPos, yPos);
    // System.out.println(mousePosition);

    for (Polygon p : this.shapes.keySet())
    {
      if (p.contains(mousePosition))
      {
        String name = this.shapes.get(p);
        int oldIndex = this.colorIndex.get(name).getSecond();
        Pair<Polygon, Integer> newPair = new Pair<>(p, oldIndex + 1);
        this.colorIndex.put(name, newPair);
        // System.out.println(name);

        redraw();
      }
    }
  }

  /**
   * Method that checks for when mouse is clicked down.
   * 
   * @param e
   *          the event
   */
  @Override
  public void mousePressed(final MouseEvent e)
  {

  }

  /**
   * Method that checks when the mouse button is released.
   * 
   * @param e
   *          the event
   */
  @Override
  public void mouseReleased(final MouseEvent e)
  {

  }

  /**
   * Method that checks when the mouse enters an object.
   * 
   * @param e
   *          the event
   */
  @Override
  public void mouseEntered(final MouseEvent e)
  {

  }

  /**
   * Method that checks when the mouse exits an object.
   * 
   * @param e
   *          the event
   */
  @Override
  public void mouseExited(final MouseEvent e)
  {

  }
}
