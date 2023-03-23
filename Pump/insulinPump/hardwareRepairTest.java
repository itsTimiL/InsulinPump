package insulinPump;
import java.util.Random;

public class hardwareRepairTest extends Random {
	
	private int RanNumTest;
	
	public hardwareRepairTest (int RanNumTest)
	{
		this.RanNumTest = RanNumTest;
	}
	
	@Override
	public int nextInt (int limit) {
		return RanNumTest;
	}

}
