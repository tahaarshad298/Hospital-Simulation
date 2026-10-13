import java.util.Random;

/**
 * Is-A:   a Device that measures temperature.
 * Does-A: generates simulated temperatures for acute-care patients, skewed toward
 *         fevers compared with a healthy population.
 * Has-A:  nothing; uses the shared Random from Simulation.
 */
public class TemperatureMonitor extends Device {

    @Override
    public Observation sample() {
        Random r = Simulation.getRandom();
        double roll = r.nextDouble();
        double celsius;
        if (roll < 0.005) {
            celsius = 34.0 + r.nextDouble();             // hypothermia: 34.0 - 35.0
        } else if (roll < 0.010) {
            celsius = 40.0 + r.nextDouble() * 1.5;       // high fever (urgent): 40.0 - 41.5
        } else if (roll < 0.045) {
            celsius = 38.0 + r.nextDouble() * 1.9;       // fever: 38.0 - 39.9
        } else {
            celsius = 36.1 + r.nextDouble() * 1.7;       // normal to mildly raised: 36.1 - 37.8
        }
        return new Temperature(celsius);
    }
}
