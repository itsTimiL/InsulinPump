package InsulinpumpSim;

import insulinPump.HardwareProblemExcept;


import java.util.Random;

	
	public class SenRandom implements Sensor, InspectTheHardware {
		private Random  ranGeneratorForMeasurement;
		private Random ranGeneratorForHardware;
		private int limit = 1500;
		
		 final static Float theMaxBloodSugarLevelAmount = 35f;
		 
		 public void senRandom (Random ranGeneratorForMeasurement, Random ranGeneratorForHardware) {
		        this.ranGeneratorForMeasurement = ranGeneratorForMeasurement;
		        this.ranGeneratorForHardware = ranGeneratorForHardware;
		 }
		 
		 @Override
		 public Float executeMeasure() throws HardwareProblemExcept
		    {
		        if (!isTheHardwareRunningProperly())
		            throw new HardwareProblemExcept ("The hardware for the sensor has a problem. ");

		        return 1 + ranGeneratorForMeasurement.nextFloat() * (theMaxBloodSugarLevelAmount - 1);
		    }
		 
		 @Override
		    public boolean isTheHardwareRunningProperly()
		    {
		        int ranNum = ranGeneratorForHardware.nextInt(limit);

		        if (ranNum == 0 || (ranNum > 876 && ranNum < 890))
		            return false;

		        return true;

		    }
		 }