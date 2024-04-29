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
  private static final int BEAK_X = 210;
  private static final int BEAK_Y = 210;
  private static final int FEET_X = 210;
  private static final int FEET_Y = 210;
  private static final int SIZE_X = 310;
  private static final int SIZE_Y = 310;
  
  public static Thread thread;
  public static WebBrowser browser;
  public static JLayeredPane overlay = null;
  public static ArrayList<String> endResult = new ArrayList<String>();
  
  public static final String[] SIZE = {"Tiny", "Small", "Medium", "Large"};
  public static final String[] BEAK_LENGTH = {"Short", "Average", "Long"};
  public static final String[] FOOT_SHAPE = {"Clawed", "Climbing", "Perching", "Wading", "Webbed"};
  public static final String[] BEAK_SHAPE = {"Cone", "Chisel", "Pointy", "Hooked", "Flat", "Probing"};
  public static final String[] YEAR = {"Migration", "Breeding", "Non-breeding", "All-year"};
  
  public static final ImageIcon TITLE_IMAGE = ImageReader.readImage("title_bird.png");
  // title_bird.png is from 
  // https://4vector.com/i/free-vector-cartoon-bird-03_098900_cartoon_bird_03.png
  
  //Images for the feet type panel
  public static final Image TINY_IMAGE = ImageReader.resizeImage(ImageReader.readBuffered("Tiny.png"), SIZE_X, SIZE_Y);
  public static final Image SMALL_IMAGE = ImageReader.resizeImage(ImageReader.readBuffered("Small.png"), SIZE_X, SIZE_Y);
  public static final Image MEDIUM_IMAGE = ImageReader.resizeImage(ImageReader.readBuffered("Medium.png"), SIZE_X, SIZE_Y);
  public static final Image LARGE_IMAGE = ImageReader.resizeImage(ImageReader.readBuffered("Large.png"), SIZE_X, SIZE_Y);
  public static final Image[] SIZE_IMAGES = {TINY_IMAGE, SMALL_IMAGE, MEDIUM_IMAGE, LARGE_IMAGE};
   
  // Images for the feet type panel
  public static final Image CLAWED_IMAGE = ImageReader.resizeImage(ImageReader.readBuffered("Clawed.png"), FEET_X, FEET_Y);
  public static final Image CLIMBING_IMAGE = ImageReader.resizeImage(ImageReader.readBuffered("Climbing.png"), FEET_X, FEET_Y);
  public static final Image PERCHING_IMAGE = ImageReader.resizeImage(ImageReader.readBuffered("Perching.png"), FEET_X, FEET_Y);
  public static final Image WADING_IMAGE = ImageReader.resizeImage(ImageReader.readBuffered("Wading.png"), FEET_X, FEET_Y);
  public static final Image WEBBED_IMAGE = ImageReader.resizeImage(ImageReader.readBuffered("Webbed.png"), FEET_X, FEET_Y);
  public static final Image[] FEET_IMAGES = {CLAWED_IMAGE, CLIMBING_IMAGE, PERCHING_IMAGE, WADING_IMAGE, WEBBED_IMAGE};
  
  //Images for the beak type panel
  public static final Image CONE_IMAGE = ImageReader.resizeImage(ImageReader.readBuffered("Cone.png"), BEAK_X, BEAK_Y);
  public static final Image CHISEL_IMAGE = ImageReader.resizeImage(ImageReader.readBuffered("Chisel.png"), BEAK_X, BEAK_Y);
  public static final Image POINTY_IMAGE = ImageReader.resizeImage(ImageReader.readBuffered("Pointy.png"), BEAK_X, BEAK_Y);
  public static final Image HOOKED_IMAGE = ImageReader.resizeImage(ImageReader.readBuffered("Hooked.png"), BEAK_X, BEAK_Y);
  public static final Image FLAT_IMAGE = ImageReader.resizeImage(ImageReader.readBuffered("Flat.png"), BEAK_X, BEAK_Y);
  public static final Image PROBING_IMAGE = ImageReader.resizeImage(ImageReader.readBuffered("Probing.png"), BEAK_X, BEAK_Y);
  public static final Image[] BEAK_IMAGES = {CONE_IMAGE, CHISEL_IMAGE, POINTY_IMAGE, HOOKED_IMAGE, FLAT_IMAGE, PROBING_IMAGE};
   
  // Images for seasons animation
  public static final Image BUG_IMAGE = ImageReader.resizeImage(ImageReader.readBuffered("Bug.png"), 50, 50);
  public static final Image LEAF_IMAGE = ImageReader.resizeImage(ImageReader.readBuffered("Leaf.png"), 50, 50);
  public static final Image RAIN_IMAGE = ImageReader.resizeImage(ImageReader.readBuffered("raindrop.png"), 50, 50);
  public static final Image SNOW_IMAGE = ImageReader.resizeImage(ImageReader.readBuffered("Snowflake.png"), 50, 50);
  
  public static ArrayList<Pair<String, Color>> beakColor = new ArrayList<Pair<String,Color>>();
  public static ArrayList<Pair<String, Color>> crownColor = new ArrayList<Pair<String,Color>>();
  public static ArrayList<Pair<String, Color>> superColor = new ArrayList<Pair<String,Color>>();
  public static ArrayList<Pair<String, Color>> eyestripeColor = new ArrayList<Pair<String,Color>>();
  public static ArrayList<Pair<String, Color>> auricColor = new ArrayList<Pair<String,Color>>();
  public static ArrayList<Pair<String, Color>> throatColor = new ArrayList<Pair<String,Color>>();
  public static ArrayList<Pair<String, Color>> breastColor = new ArrayList<Pair<String,Color>>();
  public static ArrayList<Pair<String, Color>> wingColor = new ArrayList<Pair<String,Color>>();
  public static ArrayList<Pair<String, Color>> covertColor = new ArrayList<Pair<String,Color>>();
  public static ArrayList<Pair<String, Color>> footColor = new ArrayList<Pair<String,Color>>();
  
  public static HashMap<String, Integer> selectionIndicies = new HashMap<String, Integer>();
  
  public static boolean[] picked = new boolean[15];
  public static String[] selections = new String[15];

  /**
   * static constructor.
   * 
   * fills color option ArrayLists & Selection/Index HashMap
   */
  static { 
//  fill crown color options
    crownColor.add(new Pair<String, Color>("default", new Color(172,91,91)));
    crownColor.add(new Pair<String, Color>("black", new Color(0,0,0)));
    crownColor.add(new Pair<String, Color>("brown", new Color(88,57,39)));
    crownColor.add(new Pair<String, Color>("gray", new Color(183,183,183)));
    crownColor.add(new Pair<String, Color>("orange", new Color(255,153,0)));
    crownColor.add(new Pair<String, Color>("red", new Color(255,0,0)));
    crownColor.add(new Pair<String, Color>("muted_tan", new Color(210,187,173)));
    
//  fill supercilium color options
    superColor.add(new Pair<String, Color>("default", new Color(172,91,91)));
    superColor.add(new Pair<String, Color>("light_gray", new Color(225,225,225)));
    superColor.add(new Pair<String, Color>("yellow", new Color(255,255,0)));
    superColor.add(new Pair<String, Color>("white", new Color(255,255,255)));
    superColor.add(new Pair<String, Color>("muted_tan", new Color(210,187,173)));
    superColor.add(new Pair<String, Color>("black", new Color(0,0,0)));
    superColor.add(new Pair<String, Color>("tan", new Color(210,180,140)));
    superColor.add(new Pair<String, Color>("gray", new Color(183,183,183)));
    superColor.add(new Pair<String, Color>("brown", new Color(88,57,39)));
    superColor.add(new Pair<String, Color>("orange", new Color(255,153,0)));
    superColor.add(new Pair<String, Color>("white_tan", new Color(240,222,210)));
    superColor.add(new Pair<String, Color>("red", new Color(255,0,0)));
    
//  fill eyestripe color coptions
    eyestripeColor.add(new Pair<String, Color>("default", new Color(172,91,91)));
    eyestripeColor.add(new Pair<String, Color>("black", new Color(0,0,0)));
    eyestripeColor.add(new Pair<String, Color>("white", new Color(255,255,255)));
    eyestripeColor.add(new Pair<String, Color>("brown", new Color(88,57,39)));
    eyestripeColor.add(new Pair<String, Color>("orange", new Color(255,153,0)));
    eyestripeColor.add(new Pair<String, Color>("muted_tan", new Color(210,187,173)));
    eyestripeColor.add(new Pair<String, Color>("gray", new Color(183,183,183)));
    eyestripeColor.add(new Pair<String, Color>("red", new Color(255,0,0)));
    
//  fill auricular color options
    auricColor.add(new Pair<String, Color>("default", new Color(172,91,91)));
    auricColor.add(new Pair<String, Color>("gray", new Color(183,183,183)));
    auricColor.add(new Pair<String, Color>("white", new Color(255,255,255)));
    auricColor.add(new Pair<String, Color>("muted_tan", new Color(210,187,173)));
    auricColor.add(new Pair<String, Color>("black", new Color(0,0,0)));
    auricColor.add(new Pair<String, Color>("tan", new Color(210,180,140)));
    auricColor.add(new Pair<String, Color>("orange", new Color(255,153,0)));
    auricColor.add(new Pair<String, Color>("brown", new Color(88,57,39)));
    auricColor.add(new Pair<String, Color>("muted_tan", new Color(210,187,173)));
    auricColor.add(new Pair<String, Color>("white_tan", new Color(240,222,210)));
    auricColor.add(new Pair<String, Color>("red", new Color(255,0,0)));
    
//  fill beak color options
    beakColor.add(new Pair<String, Color>("default", new Color(172,91,91)));
    beakColor.add(new Pair<String, Color>("gray", new Color(183,183,183)));
    beakColor.add(new Pair<String, Color>("black", new Color(0,0,0)));
    beakColor.add(new Pair<String, Color>("yellow", new Color(255,255,0)));
    beakColor.add(new Pair<String, Color>("orange", new Color(255,153,0)));
    beakColor.add(new Pair<String, Color>("white_tan", new Color(210,180,140)));
    
//  fill throat color options
    throatColor.add(new Pair<String, Color>("default", new Color(172,91,91)));
    throatColor.add(new Pair<String, Color>("white", new Color(255,255,255)));
    throatColor.add(new Pair<String, Color>("tan", new Color(210,180,140)));
    throatColor.add(new Pair<String, Color>("brown", new Color(88,57,39)));
    throatColor.add(new Pair<String, Color>("gray", new Color(183,183,183)));
    throatColor.add(new Pair<String, Color>("white_tan", new Color(240,222,210)));
    throatColor.add(new Pair<String, Color>("red", new Color(255,0,0)));
    throatColor.add(new Pair<String, Color>("black", new Color(0,0,0)));
    
//  fill breast color options
    breastColor.add(new Pair<String, Color>("default", new Color(172,91,91)));
    breastColor.add(new Pair<String, Color>("light_gray", new Color(225,225,225)));
    breastColor.add(new Pair<String, Color>("gray", new Color(183,183,183)));
    breastColor.add(new Pair<String, Color>("white", new Color(255,255,255)));
    breastColor.add(new Pair<String, Color>("orange", new Color(255,153,0)));
    breastColor.add(new Pair<String, Color>("white_tan", new Color(240,222,210)));
    breastColor.add(new Pair<String, Color>("black", new Color(0,0,0)));
    breastColor.add(new Pair<String, Color>("brown", new Color(88,57,39)));
    breastColor.add(new Pair<String, Color>("red", new Color(255,0,0)));
    breastColor.add(new Pair<String, Color>("tan", new Color(210,180,140)));
    
//  fill covert color options
    covertColor.add(new Pair<String, Color>("default", new Color(172,91,91)));
    covertColor.add(new Pair<String, Color>("brown", new Color(88,57,39)));
    covertColor.add(new Pair<String, Color>("gray", new Color(183,183,183)));
    covertColor.add(new Pair<String, Color>("muted_tan", new Color(210,187,173)));
    covertColor.add(new Pair<String, Color>("black", new Color(0,0,0)));
    covertColor.add(new Pair<String, Color>("light_gray", new Color(225,225,225)));
    covertColor.add(new Pair<String, Color>("orange", new Color(255,153,0)));
    covertColor.add(new Pair<String, Color>("white", new Color(255,255,255)));
    covertColor.add(new Pair<String, Color>("muted_tan", new Color(210,187,173)));
    covertColor.add(new Pair<String, Color>("red", new Color(255,0,0)));
    
//  fill wing color options
    wingColor.add(new Pair<String, Color>("default", new Color(172,91,91)));
    wingColor.add(new Pair<String, Color>("brown", new Color(88,57,39)));
    wingColor.add(new Pair<String, Color>("black", new Color(0,0,0)));
    wingColor.add(new Pair<String, Color>("light_gray", new Color(225,225,225)));
    wingColor.add(new Pair<String, Color>("gray", new Color(183,183,183)));
    wingColor.add(new Pair<String, Color>("orange", new Color(255,153,0)));
    wingColor.add(new Pair<String, Color>("white", new Color(255,255,255)));
    wingColor.add(new Pair<String, Color>("red", new Color(255,0,0)));
    
//  fill foot color options
    footColor.add(new Pair<String, Color>("default", new Color(172,91,91)));
    footColor.add(new Pair<String, Color>("brown", new Color(88,57,39)));
    footColor.add(new Pair<String, Color>("yellow", new Color(255,255,0)));
    footColor.add(new Pair<String, Color>("gray", new Color(183,183,183)));
    footColor.add(new Pair<String, Color>("orange", new Color(255,153,0)));
    footColor.add(new Pair<String, Color>("black", new Color(0,0,0)));
    
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
}
