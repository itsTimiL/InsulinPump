package InsulinpumpSim;

import insulinPump.HardwareProblemExcept;
;


public interface ThePump {
	void getInsulin (Integer insulincollecting) throws HardwareProblemExcept;
    boolean equals (Object o);
}


