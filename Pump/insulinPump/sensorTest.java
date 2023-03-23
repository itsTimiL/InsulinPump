package insulinPump;
import org.junit.Test;
import InsulinpumpSim.SenRandom;
import InsulinpumpSim.Sensor;

import java.util.Random;

import static org.junit.Assert.*;

public class sensorTest {
	
    @Test
    public void testSuccessRunMeasurement() throws HardwareProblemExcept
    {
        //using 1 as a 'random' number: the HW test will pass
    	Sensor sen = new SenRandom(new Random(), new hardwareRepairTest(0));
        Float bloodS = sen.executeMeasure();

        assertTrue(bloodS >= 1f && bloodS <=35f);
    }

    @Test
    public void tesMeasureFail()
    {
        // using 0 as a 'random' number: the HW check will fail
        Sensor sen = new SenRandom(new Random(), new hardwareRepairTest(0));
        assertThrows(HardwareProblemExcept.class, () -> {
            sen.executeMeasure();
        });
    }

    @Test
    public void testisTheHardwareRunningProperly()
    {
        //using 1 as a 'random' number: the HW test will pass
        Sensor sen = new SenRandom(new Random(), new hardwareRepairTest(1));
        assertTrue(((SenRandom) sen).isTheHardwareRunningProperly());
    }

    @Test
    public void testIsHardwareNoRunningProperly()
    {
        //using 877 as a 'random' number: the HW test will fail
        Sensor sen = new SenRandom(new Random(), new hardwareRepairTest(0));
        assertFalse(((SenRandom) sen).isTheHardwareRunningProperly());
    }

    @Test
    public void testEquals()
    {
        assertTrue(new SenRandom(new Random(), new Random()).equals(new SenRandom(new Random(), new Random())));
    }
}
