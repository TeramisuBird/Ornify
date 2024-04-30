package ornify;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;

/**
 * Database Selections from user.
 * <li> 0. Season
 * <li> 1. Size
 * <li> 2. Crown
 * <li> 3. Supercilium
 * <li> 4. Eyestripe
 * <li> 5. Auriculars
 * <li> 6. Beak Shape
 * <li> 7. Beak Length
 * <li> 8. Beak Color
 * <li> 9. Throat
 * <li> 10. Breast
 * <li> 11. Coverts
 * <li> 12. Wing
 * <li> 13. Foot Shape
 * <li> 14. Foot Color
 *
 */
/**
 * Collection of various selection options for different panels.
 * 
 * This work complies with the JMU Honor Code.
 */
public class Model
{
  public static final String[] BEAK_LENGTH = {"Short", "Average", "Long"};
  public static final String[] BEAK_SHAPE = {"Cone", "Chisel", "Pointy", "Hooked", "Flat",
      "Probing"};
  public static final String[] FOOT_SHAPE = {"Clawed", "Climbing", "Perching", "Wading", "Webbed"};
  public static final String[] SIZE = {"Tiny", "Small", "Medium", "Large"};
  public static final String[] YEAR = {"Migration", "Breeding", "Non-breeding", "All-year"};

  private static final int BEAK_X = 210;
  private static final int BEAK_Y = 210;
  private static final int FEET_X = 210;
  private static final int FEET_Y = 210;
  private static final int SIZE_X = 310;
  private static final int SIZE_Y = 310;

  private static final String DEFAULT = "default";
  private static final String BLACK = "black";
  private static final String BROWN = "brown";
  private static final String GRAY = "gray";
  private static final String LIGHT_GRAY = "light_gray";
  private static final String TAN = "tan";
  private static final String WHITE_TAN = "white_tan";
  private static final String MUTED_TAN = "muted_tan";
  private static final String RED = "red";
  private static final String ORANGE = "orange";
  private static final String YELLOW = "yellow";
  private static final String WHITE = "white";

  private static ArrayList<Pair<String, Color>> beakColor = new ArrayList<Pair<String, Color>>();
  private static ArrayList<Pair<String, Color>> crownColor = new ArrayList<Pair<String, Color>>();
  private static ArrayList<Pair<String, Color>> superColor = new ArrayList<Pair<String, Color>>();
  private static ArrayList<Pair<String, Color>> eyestripeColor 
      = new ArrayList<Pair<String, Color>>();
  private static ArrayList<Pair<String, Color>> auricColor = new ArrayList<Pair<String, Color>>();
  private static ArrayList<Pair<String, Color>> throatColor = new ArrayList<Pair<String, Color>>();
  private static ArrayList<Pair<String, Color>> breastColor = new ArrayList<Pair<String, Color>>();
  private static ArrayList<Pair<String, Color>> wingColor = new ArrayList<Pair<String, Color>>();
  private static ArrayList<Pair<String, Color>> covertColor = new ArrayList<Pair<String, Color>>();
  private static ArrayList<Pair<String, Color>> footColor = new ArrayList<Pair<String, Color>>();
  
  private static boolean[] picked = new boolean[15];
  private static HashMap<String, Integer> selectionIndicies = new HashMap<String, Integer>();
  private static String[] selections = new String[15];
  
  private ArrayList<String> endResult = new ArrayList<String>();
  private JLayeredPane overlay = null;
  private Thread thread;
  private WebBrowser browser;
  
  // Images for the feet type panel.
  private Image[] sizeImages = new Image[4];

  // Images for the feet type panel.
  private Image[] feetImages = new Image[5];

  // Images for the beak type panel.
  private Image[] beakImages = new Image[6];

  // Images for seasons animation.
  private Image[] seasonImages = new Image[4];

  // title_bird.png is from
  // https://4vector.com/i/free-vector-cartoon-bird-03_098900_cartoon_bird_03.png
  private ImageIcon titleImage;
  private ImageReader reader;

  /**
   * Parameterized constructor of the model.
   * 
   * @param reader
   *          - The image reader object.
   */
  public Model(final ImageReader reader)
  {
    this.reader = reader;
    Image imageBug = reader.resizeImage(reader.readBuffered("Bug.png"), 50, 50);
    Image imageLeaf = reader.resizeImage(reader.readBuffered("Leaf.png"), 50, 50);
    Image imageRain = reader.resizeImage(reader.readBuffered("raindrop.png"), 50, 50);
    Image imageSnow = reader.resizeImage(reader.readBuffered("Snowflake.png"), 50, 50);
    seasonImages[0] = imageLeaf;
    seasonImages[1] = imageSnow;
    seasonImages[2] = imageRain;
    seasonImages[3] = imageBug;
    this.titleImage = reader.readImage("title_bird.png");

    // Images for the feet type panel.
    Image imageTiny = reader.resizeImage(reader.readBuffered("tiny.png"), SIZE_X, SIZE_Y);
    Image imageSmall = reader.resizeImage(reader.readBuffered("small.png"), SIZE_X, SIZE_Y);
    Image imageMedium = reader.resizeImage(reader.readBuffered("medium.png"), SIZE_X, SIZE_Y);
    Image imageLarge = reader.resizeImage(reader.readBuffered("large.png"), SIZE_X, SIZE_Y);
    sizeImages[0] = imageTiny;
    sizeImages[1] = imageSmall;
    sizeImages[2] = imageMedium;
    sizeImages[3] = imageLarge;

    // Images for the feet type panel.
    Image imageClawed = reader.resizeImage(reader.readBuffered("Clawed.png"), FEET_X, FEET_Y);
    Image imageClimbing = reader.resizeImage(reader.readBuffered("Climbing.png"), FEET_X, FEET_Y);
    Image imagePerching = reader.resizeImage(reader.readBuffered("Perching.png"), FEET_X, FEET_Y);
    Image imageWading = reader.resizeImage(reader.readBuffered("Wading.png"), FEET_X, FEET_Y);
    Image imageWebbed = reader.resizeImage(reader.readBuffered("Webbed.png"), FEET_X, FEET_Y);
    feetImages[0] = imageClawed;
    feetImages[1] = imageClimbing;
    feetImages[2] = imagePerching;
    feetImages[3] = imageWading;
    feetImages[4] = imageWebbed;

    // Images for the beak type panel.
    Image imageCone = reader.resizeImage(reader.readBuffered("Cone.png"), BEAK_X, BEAK_Y);
    Image imageChisel = reader.resizeImage(reader.readBuffered("Chisel.png"), BEAK_X, BEAK_Y);
    Image imagePointy = reader.resizeImage(reader.readBuffered("Pointy.png"), BEAK_X, BEAK_Y);
    Image imageHooked = reader.resizeImage(reader.readBuffered("Hooked.png"), BEAK_X, BEAK_Y);
    Image imageFlat = reader.resizeImage(reader.readBuffered("Flat.png"), BEAK_X, BEAK_Y);
    Image imageProbing = reader.resizeImage(reader.readBuffered("Probing.png"), BEAK_X, BEAK_Y);
    beakImages[0] = imageCone;
    beakImages[1] = imageChisel;
    beakImages[2] = imagePointy;
    beakImages[3] = imageHooked;
    beakImages[4] = imageFlat;
    beakImages[5] = imageProbing;

    // fill crown color options.
    crownColor.add(new Pair<String, Color>(DEFAULT, new Color(172, 91, 91)));
    crownColor.add(new Pair<String, Color>(BLACK, new Color(0, 0, 0)));
    crownColor.add(new Pair<String, Color>(BROWN, new Color(88, 57, 39)));
    crownColor.add(new Pair<String, Color>(GRAY, new Color(183, 183, 183)));
    crownColor.add(new Pair<String, Color>(ORANGE, new Color(255, 153, 0)));
    crownColor.add(new Pair<String, Color>(RED, new Color(255, 0, 0)));
    crownColor.add(new Pair<String, Color>(MUTED_TAN, new Color(210, 187, 173)));

    // fill supercilium color options.
    superColor.add(new Pair<String, Color>(DEFAULT, new Color(172, 91, 91)));
    superColor.add(new Pair<String, Color>(LIGHT_GRAY, new Color(225, 225, 225)));
    superColor.add(new Pair<String, Color>(YELLOW, new Color(255, 255, 0)));
    superColor.add(new Pair<String, Color>(WHITE, new Color(255, 255, 255)));
    superColor.add(new Pair<String, Color>(MUTED_TAN, new Color(210, 187, 173)));
    superColor.add(new Pair<String, Color>(BLACK, new Color(0, 0, 0)));
    superColor.add(new Pair<String, Color>(TAN, new Color(210, 180, 140)));
    superColor.add(new Pair<String, Color>(GRAY, new Color(183, 183, 183)));
    superColor.add(new Pair<String, Color>(BROWN, new Color(88, 57, 39)));
    superColor.add(new Pair<String, Color>(ORANGE, new Color(255, 153, 0)));
    superColor.add(new Pair<String, Color>(WHITE_TAN, new Color(240, 222, 210)));
    superColor.add(new Pair<String, Color>(RED, new Color(255, 0, 0)));

    // fill eyestripe color coptions.
    eyestripeColor.add(new Pair<String, Color>(DEFAULT, new Color(172, 91, 91)));
    eyestripeColor.add(new Pair<String, Color>(BLACK, new Color(0, 0, 0)));
    eyestripeColor.add(new Pair<String, Color>(WHITE, new Color(255, 255, 255)));
    eyestripeColor.add(new Pair<String, Color>(BROWN, new Color(88, 57, 39)));
    eyestripeColor.add(new Pair<String, Color>(ORANGE, new Color(255, 153, 0)));
    eyestripeColor.add(new Pair<String, Color>(MUTED_TAN, new Color(210, 187, 173)));
    eyestripeColor.add(new Pair<String, Color>(GRAY, new Color(183, 183, 183)));
    eyestripeColor.add(new Pair<String, Color>(RED, new Color(255, 0, 0)));

    // fill auricular color options.
    auricColor.add(new Pair<String, Color>(DEFAULT, new Color(172, 91, 91)));
    auricColor.add(new Pair<String, Color>(GRAY, new Color(183, 183, 183)));
    auricColor.add(new Pair<String, Color>(WHITE, new Color(255, 255, 255)));
    auricColor.add(new Pair<String, Color>(MUTED_TAN, new Color(210, 187, 173)));
    auricColor.add(new Pair<String, Color>(BLACK, new Color(0, 0, 0)));
    auricColor.add(new Pair<String, Color>(TAN, new Color(210, 180, 140)));
    auricColor.add(new Pair<String, Color>(ORANGE, new Color(255, 153, 0)));
    auricColor.add(new Pair<String, Color>(BROWN, new Color(88, 57, 39)));
    auricColor.add(new Pair<String, Color>(MUTED_TAN, new Color(210, 187, 173)));
    auricColor.add(new Pair<String, Color>(WHITE_TAN, new Color(240, 222, 210)));
    auricColor.add(new Pair<String, Color>(RED, new Color(255, 0, 0)));

    // fill beak color options.
    beakColor.add(new Pair<String, Color>(DEFAULT, new Color(172, 91, 91)));
    beakColor.add(new Pair<String, Color>(GRAY, new Color(183, 183, 183)));
    beakColor.add(new Pair<String, Color>(BLACK, new Color(0, 0, 0)));
    beakColor.add(new Pair<String, Color>(YELLOW, new Color(255, 255, 0)));
    beakColor.add(new Pair<String, Color>(ORANGE, new Color(255, 153, 0)));
    beakColor.add(new Pair<String, Color>(WHITE_TAN, new Color(210, 180, 140)));

    // fill throat color options.
    throatColor.add(new Pair<String, Color>(DEFAULT, new Color(172, 91, 91)));
    throatColor.add(new Pair<String, Color>(WHITE, new Color(255, 255, 255)));
    throatColor.add(new Pair<String, Color>(TAN, new Color(210, 180, 140)));
    throatColor.add(new Pair<String, Color>(BROWN, new Color(88, 57, 39)));
    throatColor.add(new Pair<String, Color>(GRAY, new Color(183, 183, 183)));
    throatColor.add(new Pair<String, Color>(WHITE_TAN, new Color(240, 222, 210)));
    throatColor.add(new Pair<String, Color>(RED, new Color(255, 0, 0)));
    throatColor.add(new Pair<String, Color>(BLACK, new Color(0, 0, 0)));

    // fill breast color options.
    breastColor.add(new Pair<String, Color>(DEFAULT, new Color(172, 91, 91)));
    breastColor.add(new Pair<String, Color>(LIGHT_GRAY, new Color(225, 225, 225)));
    breastColor.add(new Pair<String, Color>(GRAY, new Color(183, 183, 183)));
    breastColor.add(new Pair<String, Color>(WHITE, new Color(255, 255, 255)));
    breastColor.add(new Pair<String, Color>(ORANGE, new Color(255, 153, 0)));
    breastColor.add(new Pair<String, Color>(WHITE_TAN, new Color(240, 222, 210)));
    breastColor.add(new Pair<String, Color>(BLACK, new Color(0, 0, 0)));
    breastColor.add(new Pair<String, Color>(BROWN, new Color(88, 57, 39)));
    breastColor.add(new Pair<String, Color>(RED, new Color(255, 0, 0)));
    breastColor.add(new Pair<String, Color>(TAN, new Color(210, 180, 140)));

    // fill covert color options.
    covertColor.add(new Pair<String, Color>(DEFAULT, new Color(172, 91, 91)));
    covertColor.add(new Pair<String, Color>(BROWN, new Color(88, 57, 39)));
    covertColor.add(new Pair<String, Color>(GRAY, new Color(183, 183, 183)));
    covertColor.add(new Pair<String, Color>(MUTED_TAN, new Color(210, 187, 173)));
    covertColor.add(new Pair<String, Color>(BLACK, new Color(0, 0, 0)));
    covertColor.add(new Pair<String, Color>(LIGHT_GRAY, new Color(225, 225, 225)));
    covertColor.add(new Pair<String, Color>(ORANGE, new Color(255, 153, 0)));
    covertColor.add(new Pair<String, Color>(WHITE, new Color(255, 255, 255)));
    covertColor.add(new Pair<String, Color>(MUTED_TAN, new Color(210, 187, 173)));
    covertColor.add(new Pair<String, Color>(RED, new Color(255, 0, 0)));

    // fill wing color options.
    wingColor.add(new Pair<String, Color>(DEFAULT, new Color(172, 91, 91)));
    wingColor.add(new Pair<String, Color>(BROWN, new Color(88, 57, 39)));
    wingColor.add(new Pair<String, Color>(BLACK, new Color(0, 0, 0)));
    wingColor.add(new Pair<String, Color>(LIGHT_GRAY, new Color(225, 225, 225)));
    wingColor.add(new Pair<String, Color>(GRAY, new Color(183, 183, 183)));
    wingColor.add(new Pair<String, Color>(ORANGE, new Color(255, 153, 0)));
    wingColor.add(new Pair<String, Color>(WHITE, new Color(255, 255, 255)));
    wingColor.add(new Pair<String, Color>(RED, new Color(255, 0, 0)));

    // fill foot color options.
    footColor.add(new Pair<String, Color>(DEFAULT, new Color(172, 91, 91)));
    footColor.add(new Pair<String, Color>(BROWN, new Color(88, 57, 39)));
    footColor.add(new Pair<String, Color>(YELLOW, new Color(255, 255, 0)));
    footColor.add(new Pair<String, Color>(GRAY, new Color(183, 183, 183)));
    footColor.add(new Pair<String, Color>(ORANGE, new Color(255, 153, 0)));
    footColor.add(new Pair<String, Color>(BLACK, new Color(0, 0, 0)));

    selectionIndicies.put("season", 0);
    selectionIndicies.put("size", 1);
    selectionIndicies.put("crown", 2);
    selectionIndicies.put("supercilium", 3);
    selectionIndicies.put("eyestripe", 4);
    selectionIndicies.put("auriculars", 5);
    selectionIndicies.put("beak_shape", 6);
    selectionIndicies.put("beak_length", 7);
    selectionIndicies.put("beak_color", 8);
    selectionIndicies.put("throat", 9);
    selectionIndicies.put("breast", 10);
    selectionIndicies.put("coverts", 11);
    selectionIndicies.put("wing", 12);
    selectionIndicies.put("foot_shape", 13);
    selectionIndicies.put("foot_color", 14);
  }

  /**
   * get auricular colors.
   * 
   * @return array of beak auricular options
   */
  public static ArrayList<Pair<String, Color>> getAuricColors()
  {
    return Model.auricColor;
  }

  /**
   * get beak colors.
   * 
   * @return array of beak color options
   */
  public static ArrayList<Pair<String, Color>> getBeakColors()
  {
    return Model.beakColor;
  }

  /**
   * get breast colors.
   * 
   * @return array of breast color options
   */
  public static ArrayList<Pair<String, Color>> getBreastColors()
  {
    return Model.breastColor;
  }

  /**
   * get covert colors.
   * 
   * @return array of covert color options
   */
  public static ArrayList<Pair<String, Color>> getCovertColors()
  {
    return Model.covertColor;
  }

  /**
   * get crown colors.
   * 
   * @return array of crown color options
   */
  public static ArrayList<Pair<String, Color>> getCrownColors()
  {
    return Model.crownColor;
  }

  /**
   * get eyestripe colors.
   * 
   * @return array of eyestripe color options
   */
  public static ArrayList<Pair<String, Color>> getEyestrColors()
  {
    return Model.eyestripeColor;
  }

  /**
   * get foot colors.
   * 
   * @return array of foot color options
   */
  public static ArrayList<Pair<String, Color>> getFootColors()
  {
    return Model.beakColor;
  }

  /**
   * SelectionIndicies HashMap getter.
   * 
   * @return hashMap of Indicies & their name
   */
  public static HashMap<String, Integer> getIndicies()
  {
    return Model.selectionIndicies;
  }

  /**
   * get boolean array for option selected.
   * 
   * @return array of booleans
   */
  public static boolean[] getPicked()
  {
    return Model.picked;
  }

  /**
   * get string array of user selections.
   * 
   * @return array of selections
   */
  public static String[] getSelections()
  {
    return Model.selections;
  }

  /**
   * get supercilium colors.
   * 
   * @return array of supercilium color options
   */
  public static ArrayList<Pair<String, Color>> getSuperColors()
  {
    return Model.superColor;
  }

  /**
   * get throat colors.
   * 
   * @return array of throat color options
   */
  public static ArrayList<Pair<String, Color>> getThroatColors()
  {
    return Model.throatColor;
  }

  /**
   * get wing colors.
   * 
   * @return array of wing color options
   */
  public static ArrayList<Pair<String, Color>> getWingColors()
  {
    return Model.footColor;
  }

  /**
   * get the feet images.
   * 
   * @return the feetImages
   */
  public final Image[] getFeetImages()
  {
    return feetImages;
  }

  /**
   * get the beak images.
   * 
   * @return the beakImages
   */
  public final Image[] getBeakImages()
  {
    return beakImages;
  }
  
  /**
   * get the season images.
   * 
   * @return the seasonImages
   */
  public final Image[] getSeasonImages()
  {
    return seasonImages;
  }
  
  /**
   * get the size images.
   * 
   * @return the sizeImages
   */
  public final Image[] getSizeImages()
  {
    return sizeImages;
  }
  
  /**
   * fetches a list of results from the final stage of the program.
   * @return a list of results to retrieve.
   */
  public ArrayList<String> getEndResult()
  {
    return endResult;
  }
  
  /**
   * sets a list of results from the final stage of the program.
   * @param endResult the list of results to save.
   */
  public void setEndResult(final ArrayList<String> endResult)
  {
    this.endResult = endResult;
  }

  /**
   * fetches this program's original saved pane overlay.
   * @return the overlay for this program to retrieve.
   */
  public JLayeredPane getOverlay()
  {
    return overlay;
  }
  
  /**
   * saves this program's pane overlay for the browser.
   * @param overlay the overlay for this program to save.
   */
  public void setOverlay(final JLayeredPane overlay)
  {
    this.overlay = overlay;
  }

  /**
   * fetches the thread of this program's web browser.
   * @return the Thread object that this program runs on.
   */
  public Thread getThread()
  {
    return thread;
  }
  
  /**
   * saves the thread this program's web browser runs on.
   * @param thread a Thread object to save.
   */
  public void setThread(final Thread thread)
  {
    this.thread = thread;
  }
  
  /**
   * gets this program's web browser.
   * @return a WebBrowser panel of this program.
   */
  public WebBrowser getBrowser()
  {
    return browser;
  }
  
  /**
   * sets this program's browser.
   * @param browser the Browser of this program.
   */
  public void setBrowser(final WebBrowser browser)
  {
    this.browser = browser;
  }
  
  /**
   * gets the Title Image.
   * @return a TitleImage of this program
   */
  public ImageIcon getTitleImage()
  {
    return titleImage;
  }
  
  /**
   * gets the ImageReader.
   * @return an ImageReader object
   */
  public ImageReader getReader()
  {
    return reader;
  }

}
