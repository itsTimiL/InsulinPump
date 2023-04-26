package insulinPump;

import insulinpumpSim.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Configuration
@ComponentScan(" insulinPump ")
public class Conf {

	@Autowired
	private SimpMessagingTemplate temp;

	@Bean
	public TheModeOfTheController controllerMode() {
		return TheModeOfTheController.RUNNING;
	}

	@Bean
	public Sensor sen() {
		return new SenRandom(new Random(), new Random());
	}

	@Bean
	public ThePump pump() {
		return new RanPump(new Random());
	}

	@Bean
	public AssemblyTheNeedle AN() {
		return new RandomNeedle(new Random());
	}

	@Bean
	public List<Measure> measurements() {
		return new ArrayList<>();
	}

	@Bean
	public SimpMessagingTemplate temp() {
		return temp;
	}
}