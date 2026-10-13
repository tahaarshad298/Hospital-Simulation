import java.util.Random;

/**
 * Is-A:   a Device (pulse oximeter) that measures blood oxygen saturation.
 * Does-A: generates simulated SpO2 readings; mostly normal, sometimes low.
 * Has-A:  nothing; uses the shared Random from Simulation.
 */
public class OxygenMonitor extends Device {

    @Override
    public Observation sample() {
        Random r = Simulation.getRandom();
        double roll = r.nextDouble();
        int percent;
        if (roll < 0.004) {
            percent = 80 + r.nextInt(8);                 // severely low (urgent): 80 - 87
        } else if (roll < 0.025) {
            percent = 88 + r.nextInt(4);                 // low: 88 - 91
        } else {
            percent = 93 + r.nextInt(8);                 // normal: 93 - 100
        }
        return new OxygenSaturation(percent);
    }
}
