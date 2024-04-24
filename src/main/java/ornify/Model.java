package ornify;

import javax.swing.ImageIcon;

public class Model
{
  public static final String[] SIZE = {"Tiny", "Small", "Medium", "Large"};
  public static final String[] BEAK_LENGTH = {"Short", "Average", "Long"};
  public static final String[] FOOT_SHAPE = {"Clawed", "Climbing", "Perching", "Wading", "Webbed"};
  public static final String[] BEAK_SHAPE = {"Cone", "Chisel", "Pointy", "Hooked", "Flat", "Probing"};
  public static final String[] YEAR = {"----", "Migration", "Breeding", "Non-breeding", "All-year"};
  public static final String[] BEAK_COLOR = {"Beak", "Orange", "Red", "Black", "Brown", "Tan", 
      "Green", "Yellow", "White", "Gray", "Blue", "Light Tan"};
  public static final String[] CROWN_COLOR = {"Crown", "Orange", "Red", "Black", "Brown", 
      "Tan", "Green", "Yellow", "White", "Gray", "Blue", "Mute Tan", "Iridescent"};
  public static final String[] SUPERCILIUM_COLOR = {"Supercilium", "Orange", "Red", "Black", 
      "Brown", "Tan", "Green", "Yellow", "White", "Gray", "Blue", 
      "Mute Tan", "Iridescent", "White Tan"};
  public static final String[] EYESTRIPE_COLOR = {"Eyestripe", "Orange", "Red", "Black", 
      "Brown", "Tan", "Green", "Yellow", "White", "Gray", "Blue"};
  public static final String[] AURICULARS_COLOR = {"Auriculars", "Orange", "Red", "Black", 
      "Brown", "Tan", "Green", "Yellow", "White", "Gray", "Blue", "Mute Tan", "White Tan"};
  public static final String[] THROAT_COLOR = {"Throat", "Orange", "Red", "Black", "Brown", 
      "Tan", "Green", "Yellow", "White", "Gray", "Blue"};
  public static final String[] BREAST_COLOR = {"Breast", "Orange", "Red", "Black", "Brown", 
      "Tan", "Green", "Yellow", "White", "Gray", "Blue", "Tan"};
  public static final String[] WING_COLOR = {"Wing", "Orange", "Red", "Black", "Brown", 
      "Tan", "Green", "Yellow", "White", "Gray", "Blue"};
  public static final String[] COVERTS_COLOR = {"Coverts", "Orange", "Red", "Black", "Brown", 
      "Tan", "Green", "Yellow", "White", "Gray", "Blue"};
  public static final String[] FOOT_COLOR = {"Feet", "Orange", "Red", "Black", "Brown", 
      "Tan", "Green", "Yellow", "White", "Gray", "Blue"};
  
  public static final ImageIcon YEAR_IMAGE = ImageReader.readImage("cardinal_in_snow.jpg", 600, 400);
  public static final ImageIcon TITLE_IMAGE = ImageReader.readImage("title_bird.png");
  public static final ImageIcon FOOT_IMAGE = ImageReader.readImage("feet_types.png", 400, 450);
  public static final ImageIcon SIZE_IMAGE = ImageReader.readImage("bird_sizes.png", 500, 300);
  public static final ImageIcon BEAK_IMAGE = ImageReader.readImage("beak_types.png", 550, 450);
  public static final ImageIcon BEAKLEN_IMAGE = ImageReader.readImage("lorikeet_beak.jpg", 500, 300);
  public static final ImageIcon COLOR_IMAGE = ImageReader.readImage("bird_parts.jpg", 450, 350);
  
  //private static final String[] COLORS = {"Orange", "Red", "Black", "Brown", "Tan", "Green",
  //"Yellow", "White", "Gray", "Blue"};
  //private static final String[] REGIONS = {"Beak", "Crown", "Supercilium", "Eyestripe", "Auriculars", "Throat",
  //"Breast", "Wing", "Coverts", " Feet"};

}
