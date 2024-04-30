package ornify;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;

/**
 * Database Selections from user
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
  public Thread thread;
  public WebBrowser browser;
  public JLayeredPane overlay = null;
  public ArrayList<String> endResult = new ArrayList<String>();

  // Images for seasons animation
  public Image BUG_IMAGE;
  public Image LEAF_IMAGE;
  public Image RAIN_IMAGE;
  public Image SNOW_IMAGE;

  public ImageIcon TITLE_IMAGE;

  public static final String[] SIZE = {"Tiny", "Small", "Medium", "Large"};
  public static final String[] BEAK_LENGTH = {"Short", "Average", "Long"};
  public static final String[] FOOT_SHAPE = {"Clawed", "Climbing", "Perching", "Wading", "Webbed"};
  public static final String[] BEAK_SHAPE = {"Cone", "Chisel", "Pointy", "Hooked", "Flat",
      "Probing"};
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

  // title_bird.png is from
  // https://4vector.com/i/free-vector-cartoon-bird-03_098900_cartoon_bird_03.png

  // Images for the feet type panel
  public Image TINY_IMAGE;
  public Image SMALL_IMAGE;
  public Image MEDIUM_IMAGE;
  public Image LARGE_IMAGE;
  public Image[] SIZE_IMAGES = new Image[4];

  // Images for the feet type panel
  public Image CLAWED_IMAGE;
  public Image CLIMBING_IMAGE;
  public Image PERCHING_IMAGE;
  public Image WADING_IMAGE;
  public Image WEBBED_IMAGE;
  public Image[] FEET_IMAGES = new Image[5];

  // Images for the beak type panel
  public Image CONE_IMAGE;
  public Image CHISEL_IMAGE;
  public Image POINTY_IMAGE;
  public Image HOOKED_IMAGE;
  public Image FLAT_IMAGE;
  public Image PROBING_IMAGE;
  public Image[] BEAK_IMAGES = new Image[6];
  
  public ImageReader reader;
  
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
  
  public Model(final ImageReader reader) {
    this.reader = reader;
    BUG_IMAGE = reader.resizeImage(reader.readBuffered("Bug.png"),
        50, 50);
    LEAF_IMAGE = reader
        .resizeImage(reader.readBuffered("Leaf.png"), 50, 50);
    RAIN_IMAGE = reader
        .resizeImage(reader.readBuffered("raindrop.png"), 50, 50);
    SNOW_IMAGE = reader
        .resizeImage(reader.readBuffered("Snowflake.png"), 50, 50);
    TITLE_IMAGE = reader.readImage("title_bird.png");
    
    // Images for the feet type panel
    TINY_IMAGE = reader
        .resizeImage(reader.readBuffered("tiny.png"), SIZE_X, SIZE_Y);
    SMALL_IMAGE = reader
        .resizeImage(reader.readBuffered("small.png"), SIZE_X, SIZE_Y);
    MEDIUM_IMAGE = reader
        .resizeImage(reader.readBuffered("medium.png"), SIZE_X, SIZE_Y);
    LARGE_IMAGE = reader
        .resizeImage(reader.readBuffered("large.png"), SIZE_X, SIZE_Y);
    SIZE_IMAGES[0] = TINY_IMAGE;
    SIZE_IMAGES[1] = SMALL_IMAGE;
    SIZE_IMAGES[2] = MEDIUM_IMAGE; 
    SIZE_IMAGES[3] = LARGE_IMAGE;

    // Images for the feet type panel
    CLAWED_IMAGE = reader
        .resizeImage(reader.readBuffered("Clawed.png"), FEET_X, FEET_Y);
    CLIMBING_IMAGE = reader
        .resizeImage(reader.readBuffered("Climbing.png"), FEET_X, FEET_Y);
    PERCHING_IMAGE = reader
        .resizeImage(reader.readBuffered("Perching.png"), FEET_X, FEET_Y);
    WADING_IMAGE = reader
        .resizeImage(reader.readBuffered("Wading.png"), FEET_X, FEET_Y);
    WEBBED_IMAGE = reader
        .resizeImage(reader.readBuffered("Webbed.png"), FEET_X, FEET_Y);
    FEET_IMAGES[0] = CLAWED_IMAGE;
    FEET_IMAGES[1] = CLIMBING_IMAGE;
    FEET_IMAGES[2] = PERCHING_IMAGE;
    FEET_IMAGES[3] = WADING_IMAGE;
    FEET_IMAGES[4] = WEBBED_IMAGE;
    
    // Images for the beak type panel
    CONE_IMAGE = reader
        .resizeImage(reader.readBuffered("Cone.png"), BEAK_X, BEAK_Y);
    CHISEL_IMAGE = reader
        .resizeImage(reader.readBuffered("Chisel.png"), BEAK_X, BEAK_Y);
    POINTY_IMAGE = reader
        .resizeImage(reader.readBuffered("Pointy.png"), BEAK_X, BEAK_Y);
    HOOKED_IMAGE = reader
        .resizeImage(reader.readBuffered("Hooked.png"), BEAK_X, BEAK_Y);
    FLAT_IMAGE = reader
        .resizeImage(reader.readBuffered("Flat.png"), BEAK_X, BEAK_Y);
    PROBING_IMAGE = reader
        .resizeImage(reader.readBuffered("Probing.png"), BEAK_X, BEAK_Y);
    BEAK_IMAGES[0] = CONE_IMAGE;
    BEAK_IMAGES[1] = CHISEL_IMAGE;
    BEAK_IMAGES[2] = POINTY_IMAGE;
    BEAK_IMAGES[3] = HOOKED_IMAGE;
    BEAK_IMAGES[4] = FLAT_IMAGE;
    BEAK_IMAGES[5] = PROBING_IMAGE;
    
    // fill crown color options
    crownColor.add(new Pair<String, Color>(DEFAULT, new Color(172, 91, 91)));
    crownColor.add(new Pair<String, Color>(BLACK, new Color(0, 0, 0)));
    crownColor.add(new Pair<String, Color>(BROWN, new Color(88, 57, 39)));
    crownColor.add(new Pair<String, Color>(GRAY, new Color(183, 183, 183)));
    crownColor.add(new Pair<String, Color>(ORANGE, new Color(255, 153, 0)));
    crownColor.add(new Pair<String, Color>(RED, new Color(255, 0, 0)));
    crownColor.add(new Pair<String, Color>(MUTED_TAN, new Color(210, 187, 173)));

    // fill supercilium color options
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

    // fill eyestripe color coptions
    eyestripeColor.add(new Pair<String, Color>(DEFAULT, new Color(172, 91, 91)));
    eyestripeColor.add(new Pair<String, Color>(BLACK, new Color(0, 0, 0)));
    eyestripeColor.add(new Pair<String, Color>(WHITE, new Color(255, 255, 255)));
    eyestripeColor.add(new Pair<String, Color>(BROWN, new Color(88, 57, 39)));
    eyestripeColor.add(new Pair<String, Color>(ORANGE, new Color(255, 153, 0)));
    eyestripeColor.add(new Pair<String, Color>(MUTED_TAN, new Color(210, 187, 173)));
    eyestripeColor.add(new Pair<String, Color>(GRAY, new Color(183, 183, 183)));
    eyestripeColor.add(new Pair<String, Color>(RED, new Color(255, 0, 0)));

    // fill auricular color options
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

    // fill beak color options
    beakColor.add(new Pair<String, Color>(DEFAULT, new Color(172, 91, 91)));
    beakColor.add(new Pair<String, Color>(GRAY, new Color(183, 183, 183)));
    beakColor.add(new Pair<String, Color>(BLACK, new Color(0, 0, 0)));
    beakColor.add(new Pair<String, Color>(YELLOW, new Color(255, 255, 0)));
    beakColor.add(new Pair<String, Color>(ORANGE, new Color(255, 153, 0)));
    beakColor.add(new Pair<String, Color>(WHITE_TAN, new Color(210, 180, 140)));

    // fill throat color options
    throatColor.add(new Pair<String, Color>(DEFAULT, new Color(172, 91, 91)));
    throatColor.add(new Pair<String, Color>(WHITE, new Color(255, 255, 255)));
    throatColor.add(new Pair<String, Color>(TAN, new Color(210, 180, 140)));
    throatColor.add(new Pair<String, Color>(BROWN, new Color(88, 57, 39)));
    throatColor.add(new Pair<String, Color>(GRAY, new Color(183, 183, 183)));
    throatColor.add(new Pair<String, Color>(WHITE_TAN, new Color(240, 222, 210)));
    throatColor.add(new Pair<String, Color>(RED, new Color(255, 0, 0)));
    throatColor.add(new Pair<String, Color>(BLACK, new Color(0, 0, 0)));

    // fill breast color options
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

    // fill covert color options
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

    // fill wing color options
    wingColor.add(new Pair<String, Color>(DEFAULT, new Color(172, 91, 91)));
    wingColor.add(new Pair<String, Color>(BROWN, new Color(88, 57, 39)));
    wingColor.add(new Pair<String, Color>(BLACK, new Color(0, 0, 0)));
    wingColor.add(new Pair<String, Color>(LIGHT_GRAY, new Color(225, 225, 225)));
    wingColor.add(new Pair<String, Color>(GRAY, new Color(183, 183, 183)));
    wingColor.add(new Pair<String, Color>(ORANGE, new Color(255, 153, 0)));
    wingColor.add(new Pair<String, Color>(WHITE, new Color(255, 255, 255)));
    wingColor.add(new Pair<String, Color>(RED, new Color(255, 0, 0)));

    // fill foot color options
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
  
}
