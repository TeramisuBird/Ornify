package ornify;

import java.awt.image.BufferedImage;
import java.util.Random;

import visual.dynamic.described.AbstractSprite;
import visual.statik.TransformableContent;
import visual.statik.sampled.Content;

/**
 * Class that controls the sprite for the animation.
 */
public class FallingSprite extends AbstractSprite
{
  private Content content;
  private Random rng;
  private double speed;

  /** 
   * Constructor.
   * @param content
   */
  public FallingSprite(final Content content)
  {
    super();
    this.content = content;
    rng = new Random();
    this.setLocation(getRandomNumber(0, 300), getRandomNumber(-160, -80));
    setSpeed();

    setVisible(true);
  }

  /**
   * Method that creates a random number.
   * @param min 
   * @param max
   * @return the number
   */
  public int getRandomNumber(final int min, final int max)
  {
    return (int) ((Math.random() * (max - min)) + min);
  }

  /**
   * Method that returns the content.
   * @return the content
   */
  @Override protected TransformableContent getContent()
  {
    return content;
  }

  /**
   * Method that handles the movement.
   * @param time
   */
  @Override public void handleTick(final int time)
  {
    this.setLocation(this.x + getRandomNumber(-1, 1), this.y + (2 * speed));

    if (this.y > 300)
    {
      this.setLocation(getRandomNumber(0, 600), -80);
    }
  }

  /**
   * Method that sets a new image.
   * @param im
   */
  public void setImage(final BufferedImage im)
  {
    this.content.setImage(im);
  }

  /**
   * Method that returns the current x value.
   * @return the x value
   */
  public double getX()
  {
    return this.x;
  }

  /**
   * Method that sets a new speed.
   */
  public void setSpeed()
  {
    speed = rng.nextDouble();
  }

  /**
   * Method that sets properties of the sprite.
   * @param im
   */
  public void setSprite(final BufferedImage im)
  {
    setImage(im);
    setSpeed();
    this.setLocation(getRandomNumber(0, 600), getRandomNumber(-160, -80));
  }
}
