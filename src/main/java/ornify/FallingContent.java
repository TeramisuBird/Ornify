package ornify;

import java.awt.Graphics;

import visual.statik.sampled.Content;

public class FallingContent extends Content
{
  public FallingContent()
  {
    super();
    this.setLocation(getRandomNumber(0, 300), -80);
  }
  
  public void render(Graphics g)
  {
    this.setLocation(this.x + getRandomNumber(-5, 5), this.y + 10);
    
    if (this.y > 300)
    {
      this.setLocation(getRandomNumber(0, 600), -80);
    }
    super.render(g);
  }
  
  public int getRandomNumber(int min, int max) {
    return (int) ((Math.random() * (max - min)) + min);
  }
}
