import java.util.Random;

/**
 * Is-A:   a Device that measures heart rate.
 * Does-A: generates simulated heart rates; mostly normal, occasionally too slow or fast.
 * Has-A:  nothing; uses the shared Random from Simulation.
 */
public class HeartRateMonitor extends Device {

    @Override
    public Observation sample() {
        Random r = Simulation.getRandom();
        double roll = r.nextDouble();
        int bpm;
        if (roll < 0.003) {
            bpm = 30 + r.nextInt(10);                    // very slow (urgent): 30 - 39
        } else if (roll < 0.008) {
            bpm = 151 + r.nextInt(30);                   // very fast (urgent): 151 - 180
        } else if (roll < 0.015) {
            bpm = 40 + r.nextInt(10);                    // slow: 40 - 49
        } else if (roll < 0.030) {
            bpm = 121 + r.nextInt(30);                   // fast: 121 - 150
        } else {
            bpm = 55 + r.nextInt(55);                    // normal: 55 - 109
        }
        return new HeartRate(bpm);
    }
}
