import java.util.Random;

/**
 * Is-A:   one running instance of the hospital simulation model.
 * Does-A: setup() builds the data for a run, run() executes the simulation loop
 *         (asking the hospital to update alerts each step), process() computes/outputs
 *         results. Also supplies the shared random number generator.
 * Has-A:  the simulation parameters and the Hospital being simulated.
 */
public class Simulation {

    // ---- Shared random number generator (static, so any class can use it) ----
    private static final Random random = new Random();

    /** Returns the one shared Random object used by the whole simulation. */
    public static Random getRandom() {
        return random;
    }

    /** Convenience: a random double in [0.0, 1.0). */
    public static double nextDouble() {
        return random.nextDouble();
    }

    // ---- Simulation parameters (not hardcoded in the loop; change them here) ----
    private int numPatients = 20;
    private int timeStepMinutes = 10;          // length of one simulation step
    private int totalMinutes = 24 * 60;        // one complete simulated 24-hour period

    // ---- Simulation state ----
    private int currentTime;                   // elapsed minutes since the start of the run
    private int alertsRaised;                  // total alerts generated during the run
    private Hospital hospital;

    /** Does not need to do anything; all work happens in setup(). */
    public Simulation() {
    }

    /** Sets up all the data for one run of the simulation. */
    public void setup() {
        currentTime = 0;
        alertsRaised = 0;
        hospital = new Hospital(numPatients);
    }

    /** The simulation loop: steps time forward until the total time has been completed. */
    public void run() {
        currentTime = 0;
        alertsRaised = 0;
        while (currentTime < totalMinutes) {
            alertsRaised += hospital.updateAlerts(currentTime);
            // Later phases: nurses resolve alerts, store data, etc.
            currentTime += timeStepMinutes;
        }
    }

    /** Computes statistics and outputs results (just a setup summary for now). */
    public void process() {
        System.out.println("Simulation complete.");
        System.out.println("Alerts raised: " + alertsRaised);
        System.out.println("Simulated time: " + currentTime + " minutes ("
                + timeStepMinutes + "-minute steps)");
        System.out.println("Patients in hospital: " + hospital.getPatientCount());
        for (int i = 0; i < hospital.getPatientCount(); i++) {
            System.out.println("  " + hospital.getPatient(i));
        }
    }

    // ---- Accessors (needed for unit testing) ----
    public Hospital getHospital()   { return hospital; }
    public int getCurrentTime()     { return currentTime; }
    public int getAlertsRaised()    { return alertsRaised; }
    public int getNumPatients()     { return numPatients; }
    public int getTimeStepMinutes() { return timeStepMinutes; }
    public int getTotalMinutes()    { return totalMinutes; }
}
