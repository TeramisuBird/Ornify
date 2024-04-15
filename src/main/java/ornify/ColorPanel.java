package ornify;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

public class ColorPanel extends CustomPanel implements ItemListener
{
  
//  private static final String[] COLORS = {"Orange", "Red", "Black", "Brown", "Tan", "Green",
//      "Yellow", "White", "Gray", "Blue"};
  
  private static final String[] BEAK = {"Beak", "Orange", "Red", "Black", "Brown", "Tan", 
      "Green", "Yellow", "White", "Gray", "Blue", "Light Tan"};
  private static final String[] CROWN = {"Crown", "Orange", "Red", "Black", "Brown", 
      "Tan", "Green", "Yellow", "White", "Gray", "Blue", "Mute Tan", "Iridescent"};
  private static final String[] SUPERCILIUM = {"Supercilium", "Orange", "Red", "Black", 
      "Brown", "Tan", "Green", "Yellow", "White", "Gray", "Blue", 
      "Mute Tan", "Iridescent", "White Tan"};
  private static final String[] EYESTRIPE = {"Eyestripe", "Orange", "Red", "Black", 
      "Brown", "Tan", "Green", "Yellow", "White", "Gray", "Blue"};
  private static final String[] AURICULARS = {"Auriculars", "Orange", "Red", "Black", 
      "Brown", "Tan", "Green", "Yellow", "White", "Gray", "Blue", "Mute Tan", "White Tan"};
  private static final String[] THROAT = {"Throat", "Orange", "Red", "Black", "Brown", 
      "Tan", "Green", "Yellow", "White", "Gray", "Blue"};
  private static final String[] BREAST = {"Breast", "Orange", "Red", "Black", "Brown", 
      "Tan", "Green", "Yellow", "White", "Gray", "Blue", "Tan"};
  private static final String[] WING = {"Wing", "Orange", "Red", "Black", "Brown", 
      "Tan", "Green", "Yellow", "White", "Gray", "Blue"};
  private static final String[] COVERTS = {"Coverts", "Orange", "Red", "Black", "Brown", 
      "Tan", "Green", "Yellow", "White", "Gray", "Blue"};
  private static final String[] FEET = {"Feet", "Orange", "Red", "Black", "Brown", 
      "Tan", "Green", "Yellow", "White", "Gray", "Blue"};
  
//  private static final String[] REGIONS = {"Beak", "Crown", "Supercilium", "Eyestripe", "Auriculars", "Throat",
//      "Breast", "Wing", "Coverts", " Feet"};
  
  private JComboBox<String> beakCombo = new JComboBox<String>(BEAK);
  private JComboBox<String> crownCombo = new JComboBox<String>(CROWN);
  private JComboBox<String> superciliumCombo = new JComboBox<String>(SUPERCILIUM);
  private JComboBox<String> eyestripeCombo = new JComboBox<String>(EYESTRIPE);
  private JComboBox<String> auricularCombo = new JComboBox<String>(AURICULARS);
  private JComboBox<String> throatCombo = new JComboBox<String>(THROAT);
  private JComboBox<String> breastCombo = new JComboBox<String>(BREAST);
  private JComboBox<String> wingCombo = new JComboBox<String>(WING);
  private JComboBox<String> covertCombo = new JComboBox<String>(COVERTS);
  private JComboBox<String> feetCombo = new JComboBox<String>(FEET);
  private String currentBeak = BEAK[0];
  private String currentCrown = CROWN[0];
  private String currentSupercilium = SUPERCILIUM[0];
  private String currentEyestripe = EYESTRIPE[0];
  private String currentAuricular = AURICULARS[0];
  private String currentThroat = THROAT[0];
  private String currentBreast = BREAST[0];
  private String currentWing = WING[0];
  private String currentCovert = COVERTS[0];
  private String currentFeet = FEET[0];

  public ColorPanel(String question, BaseApplication ba)
  {
    super(question, ba);
    
    super.image.setIcon(ImageReader.readImage("bird_parts.jpg", 450, 350));
    
    beakCombo.addItemListener(this);
    crownCombo.addItemListener(this);
    superciliumCombo.addItemListener(this);
    eyestripeCombo.addItemListener(this);
    auricularCombo.addItemListener(this);
    throatCombo.addItemListener(this);
    breastCombo.addItemListener(this);
    wingCombo.addItemListener(this);
    covertCombo.addItemListener(this);
    feetCombo.addItemListener(this);
    
    super.comboPanel.add(beakCombo);
    super.comboPanel.add(crownCombo);
    super.comboPanel.add(superciliumCombo);
    super.comboPanel.add(eyestripeCombo);
    super.comboPanel.add(auricularCombo);
    super.comboPanel.add(throatCombo);
    super.comboPanel.add(breastCombo);
    super.comboPanel.add(wingCombo);
    super.comboPanel.add(covertCombo);
    super.comboPanel.add(feetCombo);
  }
  
  private void withChoice(ItemEvent e, JComboBox<String> combo, String choice, int n) {
    if (e.getSource() == combo) {
      choice = (String) combo.getSelectedItem();
      this.baseApp.addChoice(choice, n);
    }
  }
  
  @Override
  public void itemStateChanged(ItemEvent e)
  {
    // if the state combobox is changed
    withChoice(e, beakCombo, currentBeak, 8);
    withChoice(e, crownCombo, currentCrown, 2);
    withChoice(e, superciliumCombo, currentSupercilium, 3);
    withChoice(e, eyestripeCombo, currentEyestripe, 4);
    withChoice(e, auricularCombo, currentAuricular, 5);
    withChoice(e, throatCombo, currentThroat, 9);
    withChoice(e, breastCombo, currentBreast, 10);
    withChoice(e, wingCombo, currentWing, 12);
    withChoice(e, covertCombo, currentCovert, 11);
    withChoice(e, feetCombo, currentFeet, 14);
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
