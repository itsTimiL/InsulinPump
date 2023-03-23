package InsulinpumpSim;

import insulinPump.HardwareProblemExcept;


public interface AssemblyTheNeedle {
	void injection (Integer insuliInjection) throws HardwareProblemExcept;
    boolean equals(Object o);
}


