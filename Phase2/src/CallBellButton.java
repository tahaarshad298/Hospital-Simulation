import java.util.Random;

/**
 * Is-A:   a Device that is the patient's call bell button.
 * Does-A: each time it is sampled, the patient has occasionally pressed it.
 * Has-A:  nothing; uses the shared Random from Simulation.
 */
public class CallBellButton extends Device {

    /** Chance the bell has been pressed during one time step. */
    public static final double PRESS_CHANCE = 0.02;

    @Override
    public Observation sample() {
        Random r = Simulation.getRandom();
        return new CallBell(r.nextDouble() < PRESS_CHANCE);
    }
}
