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
            if (index >= Model.crownColor.size())
            {
              index = 0;
              colorIndex.put(region, new Pair<Polygon, Integer>(poly, index));
            }

            newPaint = Model.crownColor.get(index).getSecond();
            colorName = Model.crownColor.get(index).getFirst();
            break;
          case "supercilium":
            if (index >= Model.superColor.size())
            {
              index = 0;
              colorIndex.put(region, new Pair<Polygon, Integer>(poly, index));
            }

            newPaint = Model.superColor.get(index).getSecond();
            colorName = Model.superColor.get(index).getFirst();
            break;
          case "eyestripe":
            if (index >= Model.eyestripeColor.size())
            {
              index = 0;
              colorIndex.put(region, new Pair<Polygon, Integer>(poly, index));
            }

            newPaint = Model.eyestripeColor.get(index).getSecond();
            colorName = Model.eyestripeColor.get(index).getFirst();
            break;
          case "auriculars":
            if (index >= Model.auricColor.size())
            {
              index = 0;
              colorIndex.put(region, new Pair<Polygon, Integer>(poly, index));
            }

            newPaint = Model.auricColor.get(index).getSecond();
            colorName = Model.auricColor.get(index).getFirst();
            break;
          case BEAK:
            if (index >= Model.beakColor.size())
            {
              index = 0;
              colorIndex.put(region, new Pair<Polygon, Integer>(poly, index));
            }

            newPaint = Model.beakColor.get(index).getSecond();
            colorName = Model.beakColor.get(index).getFirst();
            break;
          case "throat":
            if (index >= Model.throatColor.size())
            {
              index = 0;
              colorIndex.put(region, new Pair<Polygon, Integer>(poly, index));
            }

            newPaint = Model.throatColor.get(index).getSecond();
            colorName = Model.throatColor.get(index).getFirst();
            break;
          case "breast":
            if (index >= Model.breastColor.size())
            {
              index = 0;
              colorIndex.put(region, new Pair<Polygon, Integer>(poly, index));
            }

            newPaint = Model.breastColor.get(index).getSecond();
            colorName = Model.breastColor.get(index).getFirst();
            break;
          case "coverts":
            if (index >= Model.covertColor.size())
            {
              index = 0;
              colorIndex.put(region, new Pair<Polygon, Integer>(poly, index));
            }

            newPaint = Model.covertColor.get(index).getSecond();
            colorName = Model.covertColor.get(index).getFirst();
            break;
          case "wing":
            if (index >= Model.wingColor.size())
            {
              index = 0;
              colorIndex.put(region, new Pair<Polygon, Integer>(poly, index));
            }

            newPaint = Model.wingColor.get(index).getSecond();
            colorName = Model.wingColor.get(index).getFirst();
            break;
          case FOOT:
            if (index >= Model.footColor.size())
            {
              index = 0;
              colorIndex.put(region, new Pair<Polygon, Integer>(poly, index));
            }

            newPaint = Model.footColor.get(index).getSecond();
            colorName = Model.footColor.get(index).getFirst();
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
          Model.picked[Model.selectionIndicies.get(region + add)] = false;
        }
        else
        {
          Model.picked[Model.selectionIndicies.get(region + add)] = true;

          String text = region + "='" + colorName + "'";
          Model.selections[Model.selectionIndicies.get(region)] = text;
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
   * @param e the click
   */
  @Override public void mouseClicked(final MouseEvent e)
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
   * @param e the event
   */
  @Override public void mousePressed(final MouseEvent e)
  {

  }

  /**
   * Method that checks when the mouse button is released.
   * @param e the event
   */
  @Override public void mouseReleased(final MouseEvent e)
  {

  }

  /**
   * Method that checks when the mouse enters an object.
   * @param e the event
   */
  @Override public void mouseEntered(final MouseEvent e)
  {

  }

  /**
   * Method that checks when the mouse exits an object.
   * @param e the event
   */
  @Override public void mouseExited(final MouseEvent e)
  {

  }
}
