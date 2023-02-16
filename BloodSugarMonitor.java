import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

public class BloodSugarMonitor {
    private static double bloodSugarLevel = 100.0; // initial blood sugar level
    public static final int HIGH = 120; // High blood sugar level
    public static final int LOW = 80; // Low blood sugar level

    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                checkBloodSugarLevel();
            }
        };
        timer.schedule(task, 0, 2000); // schedule task to run every 2 seconds
    }

    public static void checkBloodSugarLevel() {
        Random bsl = new Random();

        // check blood sugar level
        if (bloodSugarLevel > HIGH) {
            bloodSugarLevel = giveInsulin(bloodSugarLevel); // gives insulin and update blood sugar level
        }
        if (bloodSugarLevel < LOW) {
            bloodSugarLevel = eatSugar(bloodSugarLevel); // user eats sugary food to raise blood sugar level
        }

        System.out.println("Current blood sugar level: " + bloodSugarLevel + " mg/dL");
        bloodSugarLevel += bsl.nextInt(41) - 20; // increase or decrease blood sugar level from -20 to 20
    }

    private static double eatSugar(double bloodSugarLevel) {
        System.out.println("Blood Sugar Level too Low: Eat Sugary Foods");
        return bloodSugarLevel = bloodSugarLevel * 1.5;
    }

    public static double giveInsulin(double bloodSugarLevel) {
        // code to give insulin goes here
        bloodSugarLevel = bloodSugarLevel - (HIGH * .2);
        System.out.println("Blood Sugar Level too High: Take Insulin");
        System.out.println("Current Blood Sugar Level: " + bloodSugarLevel + " mg/dl");
        return bloodSugarLevel; // return the updated blood sugar level
    }
}
