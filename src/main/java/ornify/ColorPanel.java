package ornify;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

//import javax.swing.JComboBox;

public class ColorPanel extends CustomPanel implements ItemListener
{
//  private JComboBox<String> beakCombo = new JComboBox<String>(Model.BEAK_COLOR);
//  private JComboBox<String> crownCombo = new JComboBox<String>(Model.CROWN_COLOR);
//  private JComboBox<String> superciliumCombo = new JComboBox<String>(Model.SUPERCILIUM_COLOR);
//  private JComboBox<String> eyestripeCombo = new JComboBox<String>(Model.EYESTRIPE_COLOR);
//  private JComboBox<String> auricularCombo = new JComboBox<String>(Model.AURICULARS_COLOR);
//  private JComboBox<String> throatCombo = new JComboBox<String>(Model.THROAT_COLOR);
//  private JComboBox<String> breastCombo = new JComboBox<String>(Model.BREAST_COLOR);
//  private JComboBox<String> wingCombo = new JComboBox<String>(Model.WING_COLOR);
//  private JComboBox<String> covertCombo = new JComboBox<String>(Model.COVERTS_COLOR);
//  private JComboBox<String> feetCombo = new JComboBox<String>(Model.FOOT_COLOR);
//  private String currentBeak = Model.BEAK_COLOR[0];
//  private String currentCrown = Model.CROWN_COLOR[0];
//  private String currentSupercilium = Model.SUPERCILIUM_COLOR[0];
//  private String currentEyestripe = Model.EYESTRIPE_COLOR[0];
//  private String currentAuricular = Model.AURICULARS_COLOR[0];
//  private String currentThroat = Model.THROAT_COLOR[0];
//  private String currentBreast = Model.BREAST_COLOR[0];
//  private String currentWing = Model.WING_COLOR[0];
//  private String currentCovert = Model.COVERTS_COLOR[0];
//  private String currentFeet = Model.FOOT_COLOR[0];

  public ColorPanel(String question, BaseApplication ba)
  {
    super(question, ba);
    
//    super.image.setIcon(Model.COLOR_IMAGE);
    
//    beakCombo.addItemListener(this);
//    crownCombo.addItemListener(this);
//    superciliumCombo.addItemListener(this);
//    eyestripeCombo.addItemListener(this);
//    auricularCombo.addItemListener(this);
//    throatCombo.addItemListener(this);
//    breastCombo.addItemListener(this);
//    wingCombo.addItemListener(this);
//    covertCombo.addItemListener(this);
//    feetCombo.addItemListener(this);
    
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
  
//  private void withChoice(ItemEvent e, JComboBox<String> combo, String choice, int n) {
//    if (e.getSource() == combo) {
//      choice = (String) combo.getSelectedItem();
//      this.baseApp.addChoice(choice, n);
//    }
//  }
  
  @Override
  public void itemStateChanged(ItemEvent e)
  {
    // if the state combobox is changed
//    withChoice(e, beakCombo, currentBeak, 8);
//    withChoice(e, crownCombo, currentCrown, 2);
//    withChoice(e, superciliumCombo, currentSupercilium, 3);
//    withChoice(e, eyestripeCombo, currentEyestripe, 4);
//    withChoice(e, auricularCombo, currentAuricular, 5);
//    withChoice(e, throatCombo, currentThroat, 9);
//    withChoice(e, breastCombo, currentBreast, 10);
//    withChoice(e, wingCombo, currentWing, 12);
//    withChoice(e, covertCombo, currentCovert, 11);
//    withChoice(e, feetCombo, currentFeet, 14);
  }
  
//  public String getOption()
//  {
//    return currentOption;
//  }
  
  @Override
  public void actionPerformed(ActionEvent e)
  {
    switch (e.getActionCommand())
    {
      case "Return":
        this.baseApp.handleReturn();
        break;
      case "Next":
        //this.baseApp.handleNext();
        this.baseApp.handleResults();
        break;
      default:
        break;
    }
  }
}
