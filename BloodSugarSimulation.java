import java.util.Random;

public class BloodSugarSimulation {
  private static int insulinReserve = 500;

  public static int measureBloodSugar() {
    Random rand = new Random();
    return rand.nextInt(80) + 50;
  }

  public static void administerInsulin(int units) {
    insulinReserve -= units;
  }

  public static int updateBloodSugar(int currentBloodSugar, int insulinUnits, int lowBloodSugar, int highBloodSugar) {
    if (currentBloodSugar > lowBloodSugar) {
      return currentBloodSugar - insulinUnits * 3;
    } else if (currentBloodSugar < highBloodSugar) {
      return currentBloodSugar + insulinUnits * 3;
    } else {
      return currentBloodSugar;
    }
  }

  public static void main(String[] args) throws InterruptedException {
    int lowBloodSugar = 70;
    int highBloodSugar = 110;
    int hour = 1;

    System.out.println("Hourly Insulin rates\n------------------------");
    while (hour <= 24) {
      int currentBloodSugar = measureBloodSugar();
      System.out.println("Hour: " + hour + " | Blood sugar level: " + currentBloodSugar);

      if (currentBloodSugar < lowBloodSugar) {
        int units = (lowBloodSugar - currentBloodSugar) / 2;
        System.out.println("Blood sugar too low, administering insulin: " + units + " units");

        administerInsulin(units);
        System.out.println(
            "Updated blood sugar: " + updateBloodSugar(currentBloodSugar, units, lowBloodSugar, highBloodSugar));

      } else if (currentBloodSugar > highBloodSugar) {
        int units = (currentBloodSugar - highBloodSugar) / 2;
        System.out.println("Blood sugar too high, administering insulin: " + units + " units");

        administerInsulin(units);
        System.out.println(
            "Updated blood sugar: " + updateBloodSugar(currentBloodSugar, units, lowBloodSugar, highBloodSugar));
      }
      System.out.println("Insulin reserve: " + insulinReserve);
      Thread.sleep(1000);
      hour++;
    }
  }
}
