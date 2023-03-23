package InsulinpumpSim;

import insulinPump.HardwareProblemExcept;
import java.util.Random;


public class RandomNeedle implements AssemblyTheNeedle , InspectTheHardware {
	private  Random ranGeneratorForHardware;
	private int limit= 1500;

	public RandomNeedle (Random ranGeneratorForHardware )
	{
		this.ranGeneratorForHardware = ranGeneratorForHardware;
	}
	
	@Override
	public void injection (Integer insuliInjection) throws HardwareProblemExcept {
		if(!isTheHardwareRunningProperly())
            throw new HardwareProblemExcept (" Needle problem * ");
}

	@Override
	 public boolean isTheHardwareRunningProperly() {
        int ranNum = ranGeneratorForHardware.nextInt(limit);

        if (ranNum == 0 || (ranNum > 101 && ranNum < 112))
            return false;

        return true;

    }
	
	@Override
    public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		RandomNeedle rN = (RandomNeedle) o;
		return limit == rN.limit;
				
	}
	}


