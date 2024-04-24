package ornify;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import io.ResourceFinder;
import resources.Marker;

/**
 * Finds local images or downloads images off the web given urls.
 * 
 * @author Joseph Blethen
 * @version 1.0
 *
 */
public class ImageReader
{
  /**
   * Must be instantiated at local resources folder.
   */
  private static ResourceFinder finder = ResourceFinder.createInstance(new Marker());

  /**
   * Handles the resizing of images.
   * 
   * @param img
   *          The BufferedImage object to resize.
   * @param x
   *          The resized width.
   * @param y
   *          The resized height.
   * @return A resized image as an ImageIcon object.
   */
  private static ImageIcon getResizedIcon(BufferedImage img, int x, int y)
  {
    ImageIcon icon = null;
    Image tmp = img.getScaledInstance(x, y, Image.SCALE_SMOOTH);
    BufferedImage dimg = new BufferedImage(x, y, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2d = dimg.createGraphics();
    g2d.drawImage(tmp, 0, 0, null);
    g2d.dispose();
    icon = new ImageIcon(dimg);
    return icon;
  }

  /**
   * Reads local image files in 200x200 pixel format.
   * 
   * @param path
   *          The filename of the image.
   * @return An ImageIcon object of the local image.
   */
  public static ImageIcon readImage(String path)
  {
    return readImage(path, 200, 200);
  }

  /**
   * Reads local image in given (x, y) pixel format.
   * 
   * @param path
   *          The filename of the image
   * @param x
   *          The pixel width
   * @param y
   *          The pixel height
   * @return An ImageIcon object of the local image.
   */
  public static ImageIcon readImage(String path, int x, int y)
  {
    ImageIcon icon = null;
    InputStream stream = null;
    stream = finder.findInputStream(path);
    try
    {
      icon = getResizedIcon(ImageIO.read(stream), x, y);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return icon;
  }
  
  public static BufferedImage readBuffered(String path)
  {
    BufferedImage image = null;
    InputStream stream = null;
    stream = finder.findInputStream(path);
    try
    {
      image = ImageIO.read(stream);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    
    return image;
  }
  
  public static BufferedImage resizeImage(BufferedImage image, int x, int y)
  {
    Image tmp = image.getScaledInstance(x, y, Image.SCALE_SMOOTH);
    BufferedImage dimg = new BufferedImage(x, y, BufferedImage.TYPE_INT_ARGB);

    Graphics2D g2d = dimg.createGraphics();
    g2d.drawImage(tmp, 0, 0, null);
    g2d.dispose();

    return dimg;
  }

  /**
   * Downloads an image in 250x250 pixel format.
   * 
   * @param url
   *          The link to the image download.
   * @return A downloaded 250 by 250 image.
   */
  public static ImageIcon downloadImage(String url)
  {
    return downloadImage(url, 250, 250);
  }

  /**
   * Downloads an image in given (x, y) pixel format.
   * 
   * @param url
   *          The link to the image download.
   * @param x
   *          The pixel width
   * @param y
   *          The pixel height
   * @return A downloaded and resized image.
   */
  public static ImageIcon downloadImage(String url, int x, int y)
  {
    ImageIcon icon = null;
    try
    {
      URL path = new URL(url);
      icon = getResizedIcon(ImageIO.read(path), x, y);
    }
    catch (MalformedURLException e)
    {
      e.printStackTrace();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return icon;
  }
}
