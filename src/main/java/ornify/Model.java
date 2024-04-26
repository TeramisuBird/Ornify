package ornify;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;

public class Model
{
  public static final String[] SIZE = {"Tiny", "Small", "Medium", "Large"};
  public static final String[] BEAK_LENGTH = {"Short", "Average", "Long"};
  public static final String[] FOOT_SHAPE = {"Clawed", "Climbing", "Perching", "Wading", "Webbed"};
  public static final String[] BEAK_SHAPE = {"Cone", "Chisel", "Pointy", "Hooked", "Flat", "Probing"};
  public static final String[] YEAR = {"Migration", "Breeding", "Non-breeding", "All-year"};
//  public static final String[] BEAK_COLOR = {"Beak", "Orange", "Red", "Black", "Brown", "Tan", 
//      "Green", "Yellow", "White", "Gray", "Blue", "Light Tan"};
//  public static final String[] CROWN_COLOR = {"Crown", "Orange", "Red", "Black", "Brown", 
//      "Tan", "Green", "Yellow", "White", "Gray", "Blue", "Mute Tan"};
//  public static final String[] SUPERCILIUM_COLOR = {"Supercilium", "Orange", "Red", "Black", 
//      "Brown", "Tan", "Green", "Yellow", "White", "Gray", "Blue", 
//      "Mute Tan", "White Tan"};
//  public static final String[] EYESTRIPE_COLOR = {"Eyestripe", "Orange", "Red", "Black", 
//      "Brown", "Tan", "Green", "Yellow", "White", "Gray", "Blue"};
//  public static final String[] AURICULARS_COLOR = {"Auriculars", "Orange", "Red", "Black", 
//      "Brown", "Tan", "Green", "Yellow", "White", "Gray", "Blue", "Mute Tan", "White Tan"};
//  public static final String[] THROAT_COLOR = {"Throat", "Orange", "Red", "Black", "Brown", 
//      "Tan", "Green", "Yellow", "White", "Gray", "Blue"};
//  public static final String[] BREAST_COLOR = {"Breast", "Orange", "Red", "Black", "Brown", 
//      "Tan", "Green", "Yellow", "White", "Gray", "Blue",};
//  public static final String[] WING_COLOR = {"Wing", "Orange", "Red", "Black", "Brown", 
//      "Tan", "Green", "Yellow", "White", "Gray", "Blue"};
//  public static final String[] COVERTS_COLOR = {"Coverts", "Orange", "Red", "Black", "Brown", 
//      "Tan", "Green", "Yellow", "White", "Gray", "Blue"};
//  public static final String[] FOOT_COLOR = {"Feet", "Orange", "Red", "Black", "Brown", 
//      "Tan", "Green", "Yellow", "White", "Gray", "Blue"}
  
  public static final ImageIcon YEAR_IMAGE = ImageReader.readImage("cardinal_in_snow.jpg", 600, 400);
  public static final ImageIcon TITLE_IMAGE = ImageReader.readImage("title_bird.png");
  public static final ImageIcon FOOT_IMAGE = ImageReader.readImage("feet_types.png", 400, 450);
  public static final ImageIcon SIZE_IMAGE = ImageReader.readImage("bird_sizes.png", 500, 300);
  public static final ImageIcon BEAK_IMAGE = ImageReader.readImage("beak_types.png", 550, 450);
//  public static final ImageIcon BEAKLEN_IMAGE = ImageReader.readImage("lorikeet_beak.jpg", 500, 300);
  public static final ImageIcon COLOR_IMAGE = ImageReader.readImage("bird_parts.jpg", 450, 350);
  
  public static final Image BEAKLEN_IMAGE = ImageReader.resizeImage(ImageReader.readBuffered("lorikeet_beak.jpg"), 200, 200);
  
  //private static final String[] COLORS = {"Orange", "Red", "Black", "Brown", "Tan", "Green",
  //"Yellow", "White", "Gray", "Blue"};
  //private static final String[] REGIONS = {"Beak", "Crown", "Supercilium", "Eyestripe", "Auriculars", "Throat",
  //"Breast", "Wing", "Coverts", " Feet"};
  
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
