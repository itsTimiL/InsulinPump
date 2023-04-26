package insulinPump;

import insulinpumpSim.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Controller
public class AppController {
	private final int initialDelay = 5; // seconds
	private final int measuremRoutine = 10; //  seconds
	private final int hardwareTestRoutine = 1; //  seconds
	private final int insulinMinmumDose = 1;

	@Autowired
	private SimpMessagingTemplate temp;
	private TheModeOfTheController mode;
	private List<Measure> measurements;
	private Sensor sen;
	private ThePump pump;
	private AssemblyTheNeedle AN;
	private ScheduledExecutorService executor1;

	@Autowired
	public AppController(List<Measure> measure, TheModeOfTheController mode, Sensor sen, ThePump pump,
			AssemblyTheNeedle AN, SimpMessagingTemplate temp) {
		this.measurements = measure;
		this.mode = mode;
		this.sen = sen;
		this.pump = pump;
		this.AN = AN;
		this.temp = temp;
		executor1 = Executors.newScheduledThreadPool(1);
	}

	@RequestMapping("/")
	public String index() {
		runAndSendMeasurement(executor1);
		runAndSendHardwareCheck(executor1);

		return "insulinPump";
	}

	@RequestMapping("/rebootDevice")
	public String rebootDevice() {
		measurements.clear();
		mode = TheModeOfTheController.RUNNING;
		executor1.shutdownNow();
		executor1 = Executors.newScheduledThreadPool(1);

		return "redirect:/";
	}

	// 10 sec
	// @Scheduled(fixedDelay=10000)
	public void runAndSendMeasurement(ScheduledExecutorService executor) {

		Runnable runMeasurement = new Runnable() {
			public void run() {
				Measure currentMeasurement = null;

				if (mode.equals(TheModeOfTheController.RUNNING))
					currentMeasurement = measurementFlow(measurements); // updates the state attribute

				// check again if the state has changed (eg: hardware test failure)
				if (currentMeasurement != null && mode.equals(TheModeOfTheController.RUNNING)) {
					temp.convertAndSend("/topic/measurements", currentMeasurement);

				}

			}
		};

		// Calls measurement every 10 seconds
		executor1.scheduleAtFixedRate(runMeasurement, initialDelay, measuremRoutine, TimeUnit.SECONDS);
	}

	// 1 sec
	// @Scheduled(fixedDelay=1000)
	public void runAndSendHardwareCheck(ScheduledExecutorService executor) {

		Runnable runMeasurement = new Runnable() {
			public void run() {
				try {
					if (mode.equals(TheModeOfTheController.RUNNING)) {
						checkHardwareIssue();
					}
				} catch (HardwareProblemExcept except) {
					temp.convertAndSend("/topic/state", "Hardware issue: reboot device");
				}
			}
		};

		// Calls measurement every 1 second
		executor.scheduleAtFixedRate(runMeasurement, initialDelay, hardwareTestRoutine, TimeUnit.SECONDS);
	}

	public Measure measurementFlow(List<Measure> measurements) {
		Float bloodSugarLevel;
		Integer compDose;

		try {
			if (mode.equals(TheModeOfTheController.RUNNING)) {
				bloodSugarLevel = measureBloodSugarLevel();
				updateMeasurement(bloodSugarLevel, measurements);
				compDose = computeInsulinToInject(measurements.get(measurements.size() - 1));
				measurements.get(measurements.size() - 1).setCompDose(compDose);
				injectInsulin(compDose);
				return measurements.get(measurements.size() - 1);
			}
		} catch (HardwareProblemExcept except) {
			this.temp.convertAndSend("/topic/state", "Hardware Issue: reboot device!");
		}

		return null;
	}

	public void checkHardwareIssue() throws HardwareProblemExcept {
		if (!((SenRandom) sen).isTheHardwareRunningProperly())
			throwExceptionAndSend("Hardware issue: sensor problem");

		if (!((RanPump) pump).isTheHardwareRunningProperly())
			throwExceptionAndSend("Hardware issue: pump problem");

		if (!((RandomNeedle) AN).isTheHardwareRunningProperly())
			throwExceptionAndSend("Hardware issue: needle problem");
	}

	public Float measureBloodSugarLevel() throws HardwareProblemExcept {
		try {
			return sen.executeMeasure();
		} catch (HardwareProblemExcept except) {
			mode = TheModeOfTheController.ERROR;
			this.temp.convertAndSend("/topic/state", "Hardware issue: sensor issue");
			throw except;
		}
	}

	public void updateMeasurement(Float lastMeasurement, List<Measure> measurementList) {
		if (measurementList.isEmpty())
			measurementList.add(new Measure(lastMeasurement));
		else if (measurementList.size() == 1)
			measurementList.add(new Measure(measurementList.get(0).getR2(), lastMeasurement));
		else
			measurementList.add(new Measure(measurementList.get(measurementList.size() - 1).getR1(),
					measurementList.get(measurementList.size() - 1).getR2(), lastMeasurement));
	}

	public int computeInsulinToInject(Measure measure) {
		int compDose = 0;

		if (measure.hasOneMeasurement())
			return 0;

		// this is executed when we have at least two element in the ArrayList
		// Sugar level falling or stable
		if (measure.getR2() <= measure.getR1()) {
			return 0;
		}

		// this is executed when we have at least three element in the ArrayList
		if (measure.ThreeMeasurements()) {
			// Sugar level increasing and rate of increase decreasing
			if (measure.getR2() > measure.getR1()) {
				if ((measure.getR2() - measure.getR1()) < (measure.getR1() - measure.getR0()))
					return 0;
				else
					compDose = Math.round((measure.getR2() - measure.getR1()) / 4);

				if (compDose == 0) {
					return insulinMinmumDose;
				}
			}
		}

		return compDose;
	}

	public void injection(Integer insuliInjection) throws HardwareProblemExcept {
		pump.getInsulin(insuliInjection);
		AN.injection(insuliInjection);
	}

	public void throwExceptionAndSend(String messge) throws HardwareProblemExcept {
		mode = TheModeOfTheController.ERROR;
		temp.convertAndSend("/topic/state", messge);
		throw new HardwareProblemExcept(messge);
	}

	public SimpMessagingTemplate getTemplate() {
		return temp;
	}

	public TheModeOfTheController getState() {
		return mode;
	}

	public List<Measure> getMeasurements() {
		return measurements;
	}

	public Sensor getSensor() {
		return sen;
	}

	public ThePump getPump() {
		return pump;
	}

	public AssemblyTheNeedle getNeedleAssembly() {
		return AN;
	}
}
