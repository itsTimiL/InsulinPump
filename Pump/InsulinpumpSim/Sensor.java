package InsulinpumpSim;
import insulinPump.HardwareProblemExcept;


public interface Sensor {
	Float executeMeasure() throws HardwareProblemExcept;
    boolean equals(Object o);
}
