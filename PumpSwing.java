package insulinpump;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.lang.Math;
import java.awt.FlowLayout;
import java.io.*;
/**
 *
 * 
 */
public class PumpSwing extends JFrame implements ActionListener {
    
    InsulinPumpMode pumpMode = new InsulinPumpMode();
    
    double carbohydrates, bloodGlucose, carbohydratesAccuracy, bloodGlucoseAccuracy, totalBolus;
    double aim = 120;
    
    //The front page 
    JPanel frontPage = new JPanel();
    JTextArea frontWel = new JTextArea("Welcome to your insulin pump! \n" + "Select whether you're a new user or a Returning User.");
    JButton frontNewU = new JButton ("New User");
    JButton frontReturning = new JButton (" Returning User");
    JButton frontExit = new JButton ("Exit");
    
    // For new users
    JPanel newUser = new JPanel();
    JLabel newUserCR = new JLabel("Insulin/carbohydrates Ratio:");
    JTextField newUserCRT = new JTextField(7);
    JLabel newUserSF = new JLabel("Insulin Sensitivity Factor:");
    JTextField newUserSFT = new JTextField(7);
    JButton newUserE = new JButton("Enter");
    JButton newUserB = new JButton("Back");
    JTextArea newUserTip = new JTextArea("Tip:\n" + "The insulin-to-carb ratio of the type of insulin you're using is \nhow many grams of carbohydrate each unit of that insulin can account for \n(e.g. 10 for a 1:10 ratio).\n" 
                                            + "Your insulin sensitivity factor is the mg/dl drop in blood glucose \ndue to 1 unit of insulin (e.g. 50). \n Bolus: Calcuates amount of insulin that needs to be injected.");
    
    // Returning User
    JPanel returningUser = new JPanel();
    JLabel returningLabel = new JLabel ("Hello " + pumpMode.getreturningName() + "!");
    JLabel returningCR = new JLabel("Your insulin/carbohydrates ratio is " + pumpMode.getInsulinCarb());
    JLabel returningSF = new JLabel ("Your insulin sensitivity factor is " + pumpMode.getInsulinSen());
    JButton returningCont = new JButton("Continue");
    JButton returningB = new JButton("Back");
    
    //Main Menu
    JPanel mainMenu = new JPanel();
    JLabel mainMenuL = new JLabel("Main Menu:");
    JButton mainMenuBolus = new JButton("Bolus");
    JButton mainMenuSett = new JButton("Settings");
    JButton mainMenuB = new JButton("Back");
    JButton mainMenuE = new JButton("Exit");
    JTextArea mainMenuTip = new JTextArea("\nBolus: Calcuates amount of insulin that needs to be injected.");

    //Bolus Panel
    JPanel bolus = new JPanel();
    JLabel bolusL = new JLabel("Current Blood Glucose (mg/dL):");
    JTextField bolusText = new JTextField(10);
    JLabel bolusCarbs = new JLabel("Carbs to account for with this dose:");
    JTextField bolusCarbsT = new JTextField(10);
    JButton bolusN = new JButton("Next");
    JLabel bolusTot = new JLabel("\nBolus Suggestion:");
    JLabel bolusWarn = new JLabel (" ");
    JButton bolusConfirm = new JButton ("Confirm");
    JButton bolusCancel = new JButton ("Cancel");
  
    //Confirm Panel
    JPanel confirm = new JPanel();
    JLabel confirmAreYouSure = new JLabel ("");
    JButton confirmYes = new JButton ("Deliver");
    JButton confirmBack = new JButton ("Back");
    JLabel confirmLabel = new JLabel("");
    JButton confirmButton = new JButton("Done");
    
    //Settings
    JPanel settings = new JPanel();
    JLabel settingsCR = new JLabel("Insulin/Carb Ratio: ");
    JLabel settingsSF = new JLabel ("Insulin Sensitivity Factor: ");
    JButton settingsC = new JButton ("Change");
    JButton settingsB = new JButton ("Back");
    
    //Change Settings Panel
    JPanel change = new JPanel(); 
    JLabel changeCF = new JLabel("Insulin/Carb Ratio");
    JTextField changeCFT = new JTextField(10);
    JLabel changeSF = new JLabel("Insulin Sensitivty Factor");
    JTextField changeSFT = new JTextField(10);
    JButton changeSubmit = new JButton("Enter");
    JButton changeCancel = new JButton("Cancel");
    
    public PumpSwing(){
        
    //Frame Contents
    this.setTitle("Insulin Pump");
    this.setVisible(true);
    this.setSize(500, 500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    //Start Panel
    this.getContentPane().add(frontPage);
    frontPage.setSize(500,500);
    frontPage.setBackground(Color.LIGHT_GRAY);
    frontPage.add(frontWel);
    
    frontPage.add(frontNewU);
    frontNewU.addActionListener(this);
    
    frontPage.add(frontReturning);
    frontReturning.addActionListener(this);
    
    frontPage.add(frontExit);
    frontExit.addActionListener(this);
    
    //New User Panel
    this.getContentPane().add(newUser);
    newUser.setSize(500,500);
    newUser.setBackground(Color.LIGHT_GRAY);
    
    newUser.add(newUserCR);
    newUser.add(newUserCRT);
    
    newUser.add(newUserSF);
    newUser.add(newUserSFT);
    
    newUser.add(newUserE);
    newUserE.addActionListener(this);
    
    newUser.add(newUserB);
    newUserB.addActionListener(this);
    
    newUser.add(newUserTip);
    
    //Pre Exist User
    this.getContentPane().add(returningUser);
    returningUser.setSize(500,500);
    returningLabel.setBackground(Color.LIGHT_GRAY);
    
    returningUser.add(returningLabel);
    returningUser.add(returningCR);
    returningUser.add(returningSF);
    returningUser.add(returningCont);
    returningCont.addActionListener(this);
    returningUser.add(returningB);
    returningB.addActionListener(this);
    
    //Main Menu
    this.getContentPane().add(mainMenu);
    mainMenu.setSize(500,500);
    mainMenu.setBackground(Color.LIGHT_GRAY);
    
    mainMenu.add(mainMenuL);
    mainMenu.add(mainMenuBolus);
    mainMenuBolus.addActionListener(this);
    mainMenu.add(mainMenuSett);
    mainMenuSett.addActionListener(this);
    mainMenu.add(mainMenuB);
    mainMenuB.addActionListener(this);
    mainMenu.add(mainMenuE);
    mainMenuE.addActionListener(this);
    mainMenu.add(mainMenuTip);
   
    //Bolus Panel
    this.getContentPane().add(bolus);
    bolus.setSize(500,500);
    bolus.setBackground(Color.LIGHT_GRAY);
    
    bolus.add(bolusL);
    bolus.add(bolusText);
    bolus.add(bolusCarbs);
    bolus.add(bolusCarbsT);
    bolus.add(bolusN);
    bolusN.addActionListener(this);
    bolus.add(bolusTot);
    bolus.add(bolusWarn);
    bolusWarn.setVisible(false);
    bolus.add(bolusConfirm);
    bolusConfirm.addActionListener(this);
    bolus.add(bolusCancel);
    bolusCancel.addActionListener(this);
    
    //Confirm Panel
    this.getContentPane().add(confirm);
    confirm.setSize(500,500);
    confirm.setBackground(Color.LIGHT_GRAY);
    
    confirm.add(confirmAreYouSure);
    confirm.add(confirmYes);
    confirmYes.addActionListener(this);
   
    confirm.add(confirmBack);
    confirmBack.addActionListener(this);
    
    confirm.add(confirmLabel);
    confirmLabel.setVisible(false);
    
    confirm.add(confirmButton);
    confirmButton.addActionListener(this);
    
    //Settings Panel
    this.getContentPane().add(settings);
    settings.setSize(500,500);
    settings.setBackground(Color.LIGHT_GRAY);
    
    settings.add(settingsCR);
    settings.add(settingsSF);
    settings.add(settingsC);
    settingsC.addActionListener(this);
    settings.add(settingsB);
    settingsB.addActionListener(this);
    
    //Change Settings Panel
    this.getContentPane().add(change);
    change.setSize(500,500);
    change.setBackground(Color.LIGHT_GRAY); 
    change.add(changeCF);
    change.add(changeCFT);
    change.add(changeSF);
    change.add(changeSFT);
    change.add(changeSubmit);
    changeSubmit.addActionListener(this);
    change.add(changeCancel);
    changeCancel.addActionListener(this);
    
    
    //setVisible
    frontPage.setVisible(true);
    newUser.setVisible(false);
    returningUser.setVisible(false);
    mainMenu.setVisible(false);
    bolus.setVisible(false);
    confirm.setVisible(false);
    settings.setVisible(false);
    change.setVisible(false);
        
    }
    
    public void actionPerformed (ActionEvent e){
    	
        //Start Panel actions
        if (e.getSource().equals(frontNewU)){
        	frontPage.setVisible(false);
            newUser.setVisible(true);
        }
        
        if (e.getSource().equals(frontReturning)){
        	pumpMode.setreturningName("Patient");
        	pumpMode.setInsulinCarb(9.00);
        	pumpMode.setInsulinSen(30.00);
            
            frontPage.setVisible(false);
            returningUser.setVisible(true);
            
            returningLabel.setText("Hello " + pumpMode.getreturningName() + "!");
            returningCR.setText("Your insulin/carb ratio is " + pumpMode.getInsulinCarb() + ".");
            returningSF.setText("Your insulin sensitivity factor is " + pumpMode.getInsulinSen() + ".");
            
            settingsCR.setText("Insulin/Carb Ratio: " + pumpMode.getInsulinCarb());
            settingsSF.setText("Insulin Sensitivity Factor: " + pumpMode.getInsulinSen());
        }
        
        if (e.getSource().equals(frontExit)){
            System.exit(0);
        }
        
        //new user actions
        if (e.getSource().equals(newUserE)){
            double itc = Double.parseDouble(newUserCRT.getText());
            double isf = Double.parseDouble(newUserSFT.getText());
            pumpMode.setInsulinCarb(itc);
            pumpMode.setInsulinSen(isf);
            //System.out.println (pumpSettings.getInsulinToCarb());
            //System.out.println (pumpSettings.getInsulinSensitivity());
            settingsCR.setText("Insulin/Carb Ratio: " + pumpMode.getInsulinCarb());
            settingsSF.setText("Insulin Sensitivity Factor: " + pumpMode.getInsulinSen());
            newUser.setVisible(false);
            mainMenu.setVisible(true);
        }
        
        if (e.getSource().equals(newUserB)){
            newUser.setVisible(false);
            frontPage.setVisible(true);
        }
        
        //pre existing user acions
        if (e.getSource().equals(returningCont)){
        	returningUser.setVisible(false);
            mainMenu.setVisible(true);
        }
        
        if (e.getSource().equals(returningB)){
        	returningUser.setVisible(false);
            frontPage.setVisible(true);
        }
        
        //Main Menu
        if (e.getSource().equals(mainMenuBolus)){
            mainMenu.setVisible(false);
            bolus.setVisible(true);
        }
        
        if (e.getSource().equals(mainMenuSett)){
            mainMenu.setVisible(false);
            frontPage.setVisible(true);
        }
        
        if (e.getSource().equals(mainMenuB)){
            mainMenu.setVisible(false);
            frontPage.setVisible(true);
        }
        
        if (e.getSource().equals(mainMenuE)){
            System.exit(0);
        }
        
        //Bolus 
        if (e.getSource().equals(bolusN)){
        	bloodGlucose = Double.parseDouble(bolusText.getText());
        	carbohydrates = Double.parseDouble(bolusCarbsT.getText());
            carbohydratesAccuracy = carbohydrates / pumpMode.getInsulinCarb();
            bloodGlucoseAccuracy = (bloodGlucose - aim) / pumpMode.getInsulinSen();
            totalBolus = Math.floor((carbohydratesAccuracy + bloodGlucoseAccuracy) * 100) / 100 ;
            bolusTot.setText("Bolus Suggestion: " + totalBolus + " Units of insulin");
            
            if (bloodGlucose >= 250){
            	bolusWarn.setVisible(true);
                bolusWarn.setText("Warning: High BG Alert!");
            }
            if (bloodGlucose <= 80){
            	bolusWarn.setVisible(true);
                bolusWarn.setText("Warning: Low BG Alert!");
            }
            if (bloodGlucose > 80 && bloodGlucose < 250){
                bolusWarn.setVisible(false);
            }
            confirmAreYouSure.setText(totalBolus + " Units of insulin. Deliver?");
           
            
        }
        if (e.getSource().equals(confirmYes))
        {
                confirmAreYouSure.setVisible(false);
                confirmYes.setVisible(false);
                confirmBack.setVisible(false);
                confirmLabel.setVisible(true);
                confirmLabel.setText(totalBolus + " Units Delivered !");
        }
        
        if (e.getSource().equals(confirmBack)){
            confirm.setVisible(false);
            bolus.setVisible(true);
        }
        
        if (e.getSource().equals(bolusConfirm)){
            bolus.setVisible(false);
            confirm.setVisible(true);
        }
        
        if (e.getSource().equals(bolusCancel)){
            bolus.setVisible(false);
            mainMenu.setVisible(true);
        }
        
        //confirm action
        if (e.getSource().equals(confirmButton)){
            confirmAreYouSure.setText(totalBolus + "U. Deliver?");
            confirmAreYouSure.setVisible(true);
                confirmYes.setVisible(true);
                confirmBack.setVisible(true);
                confirmLabel.setVisible(false);
            confirm.setVisible(false);
            mainMenu.setVisible(true);
        }
        
        //Settings
        if (e.getSource().equals(settingsC)){
            settings.setVisible(false);
            change.setVisible(true);
            
            
        }
        
        if (e.getSource().equals(settingsB)){
            settings.setVisible(false);
            mainMenu.setVisible(true);
        }
        
        
        //Change Settings - 
        if(e.getSource().equals(changeSubmit)){
            double newIC = Double.parseDouble(changeCFT.getText());
            double newISF = Double.parseDouble(changeSFT.getText());
            pumpMode.setInsulinCarb(newIC);
            pumpMode.setInsulinSen(newISF);
            change.setVisible(false);
            settings.setVisible(true);
            settingsCR.setText("Insulin/Carb Ratio: " + pumpMode.getInsulinCarb());
            settingsSF.setText("Insulin Sensitivity Factor: " + pumpMode.getInsulinSen());
            
        }
        if(e.getSource().equals(changeCancel)){
            change.setVisible(false);
            settings.setVisible(true);
        }
    }
}