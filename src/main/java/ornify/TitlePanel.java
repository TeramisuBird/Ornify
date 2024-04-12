package ornify;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.ImageIcon;

import visual.Visualization;

public class TitlePanel extends Visualization implements ActionListener
{
  public static final int WIDTH = 600;
  public static final int HEIGHT = 600;
  
  public JButton startButton;
  public JPanel panel;
  public BaseApplication baseApp;
  
  public TitlePanel(String title, BaseApplication ba)
  {
    super();
    this.getView().setSize(WIDTH, HEIGHT);
    
    this.baseApp = ba;
    this.panel = new JPanel();
    this.panel.setLayout(null);
    this.panel.setBounds(0, 0, WIDTH, HEIGHT);
    
    JLabel label = new JLabel(title);
    label.setFont(new Font("Verdana", Font.BOLD, 30));

    label.setBounds((WIDTH / 2) - 50, 50, 500, 100);
    
    this.startButton = new JButton("Start");
    this.startButton.setBounds((WIDTH / 2) - 50, (HEIGHT / 2) + 200, 100, 50);
    this.startButton.addActionListener(this);
    
    this.panel.setBackground(BaseApplication.background_color);
    
    BufferedImage myPicture;
    JLabel picLabel;
    try
    {
      myPicture = ImageIO.read(new File("src/main/java/resources/title_bird.png"));
      Image tmp = myPicture.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
      BufferedImage dimg = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
      Graphics2D g2d = dimg.createGraphics();
      g2d.drawImage(tmp, 0, 0, null);
      g2d.dispose();
      
      picLabel = new JLabel(new ImageIcon(dimg));
      picLabel.setBounds((WIDTH / 2) - (dimg.getWidth() / 2), (HEIGHT / 2) - (dimg.getHeight() / 2), dimg.getWidth(), dimg.getHeight());
      this.panel.add(picLabel);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    
    this.panel.add(label);
    this.panel.add(this.startButton);
    this.panel.setVisible(true);
  }
  
  public JPanel getPanel()
  {
    return this.panel;
  }
  
  @Override
  public void actionPerformed(ActionEvent e)
  {
    switch (e.getActionCommand())
    {
      case "Start":
        this.baseApp.handleStart();
        break;
      default:
        break;
    }
  }
}

