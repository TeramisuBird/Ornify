package ornify;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;

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
  
  private JComboBox<String> beakCombo;
  private JComboBox<String> crownCombo;
  private JComboBox<String> superciliumCombo;
  private JComboBox<String> eyestripeCombo;
  private JComboBox<String> auricularCombo;
  private JComboBox<String> throatCombo;
  private JComboBox<String> breastCombo;
  private JComboBox<String> wingCombo;
  private JComboBox<String> covertCombo;
  private JComboBox<String> feetCombo;
  private String currentBeak;
  private String currentCrown;
  private String currentSupercilium;
  private String currentEyestripe;
  private String currentAuricular;
  private String currentThroat;
  private String currentBreast;
  private String currentWing;
  private String currentCovert;
  private String currentFeet;

  public ColorPanel(String question, BaseApplication ba)
  {
    super(question, ba);
    
    this.beakCombo = new JComboBox<String>(BEAK);
    this.crownCombo = new JComboBox<String>(CROWN);
    this.superciliumCombo = new JComboBox<String>(SUPERCILIUM);
    this.eyestripeCombo = new JComboBox<String>(EYESTRIPE);
    this.auricularCombo = new JComboBox<String>(AURICULARS);
    this.throatCombo = new JComboBox<String>(THROAT);
    this.breastCombo = new JComboBox<String>(BREAST);
    this.wingCombo = new JComboBox<String>(WING);
    this.covertCombo = new JComboBox<String>(COVERTS);
    this.feetCombo = new JComboBox<String>(FEET);

    beakCombo.setBounds((WIDTH / 2) - 260, (HEIGHT / 2) - 55, 100, 50);
    crownCombo.setBounds((WIDTH / 2) - 155, (HEIGHT / 2) - 55, 100, 50);
    superciliumCombo.setBounds((WIDTH / 2) - 50, (HEIGHT / 2) - 55, 100, 50);
    eyestripeCombo.setBounds((WIDTH / 2) + 55, (HEIGHT / 2) - 55, 100, 50);
    auricularCombo.setBounds((WIDTH / 2) + 160, (HEIGHT / 2) - 55, 100, 50);
    throatCombo.setBounds((WIDTH / 2) - 260, (HEIGHT / 2), 100, 50);
    breastCombo.setBounds((WIDTH / 2) - 155, (HEIGHT / 2), 100, 50);
    wingCombo.setBounds((WIDTH / 2) - 50, (HEIGHT / 2), 100, 50);
    covertCombo.setBounds((WIDTH / 2) + 55, (HEIGHT / 2), 100, 50);
    feetCombo.setBounds((WIDTH / 2) + 160, (HEIGHT / 2), 100, 50);
    
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
    
    JPanel panel = this.getPanel();
    panel.add(beakCombo);
    panel.add(crownCombo);
    panel.add(superciliumCombo);
    panel.add(eyestripeCombo);
    panel.add(auricularCombo);
    panel.add(throatCombo);
    panel.add(breastCombo);
    panel.add(wingCombo);
    panel.add(covertCombo);
    panel.add(feetCombo);

    currentBeak = BEAK[0];
    currentCrown = CROWN[0];
    currentSupercilium = SUPERCILIUM[0];
    currentEyestripe = EYESTRIPE[0];
    currentAuricular = AURICULARS[0];
    currentThroat = THROAT[0];
    currentBreast = BREAST[0];
    currentWing = WING[0];
    currentCovert = COVERTS[0];
    currentFeet = FEET[0];
  }
  
  @Override
  public void itemStateChanged(ItemEvent e)
  {
    // if the state combobox is changed
    if (e.getSource() == beakCombo) {
      currentBeak = (String) beakCombo.getSelectedItem();
      this.baseApp.addChoice(currentBeak, 8);
    }
    
    if (e.getSource() == crownCombo) {
      currentCrown = (String) crownCombo.getSelectedItem();
      this.baseApp.addChoice(currentCrown, 2);
    }
    
    if (e.getSource() == superciliumCombo) {
      currentSupercilium = (String) superciliumCombo.getSelectedItem();
      this.baseApp.addChoice(currentSupercilium, 3);
    }
    
    if (e.getSource() == eyestripeCombo) {
      currentEyestripe = (String) eyestripeCombo.getSelectedItem();
      this.baseApp.addChoice(currentEyestripe, 4);
    }
    
    if (e.getSource() == auricularCombo) {
      currentAuricular = (String) auricularCombo.getSelectedItem();
      this.baseApp.addChoice(currentAuricular, 5);
    }
    
    if (e.getSource() == throatCombo) {
      currentThroat = (String) throatCombo.getSelectedItem();
      this.baseApp.addChoice(currentThroat, 9);
    }
    
    if (e.getSource() == breastCombo) {
      currentBreast = (String) breastCombo.getSelectedItem();
      this.baseApp.addChoice(currentBreast, 10);
    }
    
    if (e.getSource() == wingCombo) {
      currentWing = (String) wingCombo.getSelectedItem();
      this.baseApp.addChoice(currentWing, 12);
    }
    
    if (e.getSource() == covertCombo) {
      currentCovert = (String) covertCombo.getSelectedItem();
      this.baseApp.addChoice(currentCovert, 11);
    }
    
    if (e.getSource() == feetCombo) {
      currentFeet = (String) feetCombo.getSelectedItem();
      this.baseApp.addChoice(currentFeet, 14);
    }
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
        this.baseApp.handleNext();
        break;
      default:
        break;
    }
  }
}
