package ornify;

import java.awt.image.BufferedImage;
import java.util.Random;

import visual.dynamic.described.AbstractSprite;
import visual.statik.TransformableContent;
import visual.statik.sampled.Content;

public class FallingSprite extends AbstractSprite
{
  private Content content;
  private Random rng;
  private double speed;

  public FallingSprite(Content content)
  {
    super();
    this.content = content;
    rng = new Random();
    this.setLocation(getRandomNumber(0, 300), getRandomNumber(-160, -80));
    setSpeed();

    setVisible(true);
  }

  public int getRandomNumber(int min, int max)
  {
    return (int) ((Math.random() * (max - min)) + min);
  }

  @Override
  protected TransformableContent getContent()
  {
    return content;
  }

  @Override
  public void handleTick(int time)
  {
    this.setLocation(this.x + getRandomNumber(-1, 1), this.y + (2 * speed));

    if (this.y > 300)
    {
      this.setLocation(getRandomNumber(0, 600), -80);
    }
  }

  public void setImage(BufferedImage im)
  {
    this.content.setImage(im);
  }

  public double getX()
  {
    return this.x;
  }

  public void setSpeed()
  {
    speed = rng.nextDouble();
  }

  public void setSprite(BufferedImage im)
  {
    setImage(im);
    setSpeed();
    this.setLocation(getRandomNumber(0, 600), getRandomNumber(-160, -80));
  }
}
