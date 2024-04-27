package ornify;

import java.awt.event.ActionEvent;

public class ColorPanel extends CustomPanel
{

  public ColorPanel(String question, BaseApplication ba)
  {
    super(question, ba);

    ShapeTestPanel shapePanel = new ShapeTestPanel();

    shapePanel.read("full_bird", true);
    shapePanel.read("foot", false);
    shapePanel.read("crown", false);
    shapePanel.read("beak", false);
    shapePanel.read("supercilium", false);
    shapePanel.read("eyestripe", false);
    shapePanel.read("auriculars", false);
    shapePanel.read("throat", false);
    shapePanel.read("breast", false);
    shapePanel.read("coverts", false);
    shapePanel.read("wing", false);
    shapePanel.read("eyehole", true);
    shapePanel.getView().setVisible(true);
    super.getPanel().add(shapePanel.getView());
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    switch (e.getActionCommand())
    {
      case "Return":
        this.baseApp.handleReturn();
        break;
      case "Next":
        // this.baseApp.handleNext();
        this.baseApp.handleResults();
        break;
      default:
        break;
    }
  }
}
