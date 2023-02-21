/*
 * BloodSugarMonitor.java
 * This code simulates a blood sugar monitor that tracks a user's blood sugar level and performs appropriate procedures 
 * when it's too high or low. The program calls the checkBloodSugarLevel() method, which displays the current blood sugar 
 * level, updates it by adding a random number between -20 and 20 to simulate normal fluctuations in blood sugar levels, 
 * and checks to make sure it's within the set parameters. If the blood sugar level is too high, the program calls the 
 * giveInsulin() method, which simulates administering insulin to the user. If the blood sugar level is too low, the 
 * program calls the eatSugar() method, which simulates the user eating sugary food to raise their blood sugar level. 
 * The main() method runs this simulation 24 times to simulate checking the blood sugar level every hour in a day.
 */

import java.util.*;

public class BloodSugarMonitor {
    private static double bloodSugarLevel = 100.0; // initial blood sugar level
    public static final int HIGH = 120; // High blood sugar level
    public static final int LOW = 80; // Low blood sugar level

    public static void checkBloodSugarLevel() {
        Random rand = new Random();
        System.out.println("Current blood sugar level: " + bloodSugarLevel + " mg/dL");

        // check blood sugar level
        if (bloodSugarLevel > HIGH) {
            bloodSugarLevel = giveInsulin(bloodSugarLevel); // user takes insulin to lower blood sugar level
        }
        if (bloodSugarLevel < LOW) {
            bloodSugarLevel = eatSugar(bloodSugarLevel); // user eats sugary food to raise blood sugar level
        }

        bloodSugarLevel += rand.nextInt(41) - 20; // update blood sugar level in range [-20,20]
    }

    private static double eatSugar(double bloodSugarLevel) {
        System.out.println("Blood Sugar Level too Low: Eat Sugary Foods");
        bloodSugarLevel *= 1.5;

        System.out.println("Current Blood Sugar Level: " + bloodSugarLevel + " mg/dl");
        return bloodSugarLevel;
    }

    public static double giveInsulin(double bloodSugarLevel) {
        System.out.println("Blood Sugar Level too High: Take Insulin");
        bloodSugarLevel -= (HIGH * .2);

        System.out.println("Current Blood Sugar Level: " + bloodSugarLevel + " mg/dl");
        return bloodSugarLevel;
    }

    public static void main(String[] args) {
        int day = 24;

        System.out.println("Blood Sugar Simulation\n--------------------------");
        for (int hour = 1; hour <= day; hour++) {
            System.out.println("Hour: " + hour);
            checkBloodSugarLevel();
        }
    }
}
