package ornify;

/**
 * These are the columns of the ornify database's bird table.
 * 
 * @author Joseph Blethen
 * @version 1.0
 * 
 */
public enum Columns
{
  ERROR,       // Out of bounds
  BIRD_ID,     // Primary ID of the database. Cannot be null. All else can.
  NAME,        // Name of bird included in "allaboutbirds.org" url
  SEASON,      // Season when the bird may exist in Harrisonburg, VA
  SIZE,        // General size of the bird from Nuthatch to Canada Goose
  CROWN,       // Color of bird's head area
  SUPERCILIUM, // Color of bird's face from beak to upper eye area
  EYESTRIPE,   // Color of bird's face from behind eye to cheek
  AURICULARS,  // Color of bird's cheeks
  IMAGE_URL,   // Url of bird's image as .jpg or .png
  BEAK_SHAPE,  // Morphologically specialized shape of beak
  BEAK_LENGTH, // Length of bird's keratin bill
  BEAK_COLOR,  // Color of bird's keratin bill
  THROAT,      // Color of bird's throat area under beak
  SOUND_URL,   // Url of bird's sound video as an iframe embedding
  BREAST,      // Color of bird's chest area
  COVERTS,     // Color of bird's upper-wing area and mantle
  WING,        // Color of bird's lower-wing area
  FOOT_SHAPE,  // Morphologically specialized shape of toes
  FOOT_COLOR   // Color of bird's tarsus and toes i.e., feet area
}
