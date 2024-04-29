package ornify;

import java.awt.event.ActionEvent;

/**
 * Color "question" panel class (part 1 of 2)
 * 
 * This work complies with the JMU Honor Code.
 */
public class ColorPanel extends CustomPanel
{
  /**
   * Panel constructor.
   * 
   * @param question
   *          for panel's question
   * @param ba
   *          for application to add panel to
   */
  public ColorPanel(final String question, final BaseApplication ba)
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

  /**
   * Method that checks for the action performed.
   * @param e the action
   */
  @Override public void actionPerformed(final ActionEvent e)
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
