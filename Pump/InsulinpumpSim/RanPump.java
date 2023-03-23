package InsulinpumpSim;

import insulinPump.HardwareProblemExcept;
import java.util.Random;

	public class RanPump implements ThePump , InspectTheHardware 
	{
		private  Random ranGeneratorForHardware;
		private int limit= 1500;
		
		public RanPump (Random ranGeneratorForHardware )
		{
			this.ranGeneratorForHardware = ranGeneratorForHardware;
		}
		
		@Override
		public void getInsulin (Integer insulinInjection) throws HardwareProblemExcept {
			if (!isTheHardwareRunningProperly())
	            throw new HardwareProblemExcept (" pump problem * ");
	}

		@Override
		 public boolean isTheHardwareRunningProperly() {
	        int ranNum = ranGeneratorForHardware.nextInt(limit);

	        if (ranNum == 0 || (ranNum > 42 && ranNum < 53))
	            return false;

	        return true;

	    }
		
		@Override
	    public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			RanPump rN = (RanPump) o;
			return limit == rN.limit;
					
		}
		
	}
