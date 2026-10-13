/**
 * Is-A:   a request to the nursing staff to do something, based on an Observation.
 * Does-A: records when it was raised and (in Phase 3) when it was resolved,
 *         plus how severe it is; prints itself with toString().
 * Has-A:  a reference to the Observation, a time raised, a time resolved,
 *         and a Severity.
 * Constructor arguments: the Observation, the time raised, and the severity.
 *
 * Times are elapsed minutes since the start of the simulation, not times of day.
 */
public class Alert {

    /** Urgent alerts are handled by nurses before routine ones. */
    public enum Severity { URGENT, ROUTINE }

    private static final int NOT_RESOLVED = -1;

    private final Observation observation;
    private final int timeRaised;
    private final Severity severity;
    private int timeResolved = NOT_RESOLVED;

    public Alert(Observation observation, int timeRaised, Severity severity) {
        if (observation == null) {
            throw new IllegalArgumentException("observation must not be null");
        }
        if (severity == null) {
            throw new IllegalArgumentException("severity must not be null");
        }
        this.observation = observation;
        this.timeRaised = timeRaised;
        this.severity = severity;
    }

    public Observation getObservation() { return observation; }
    public int getTimeRaised()          { return timeRaised; }
    public Severity getSeverity()       { return severity; }
    public boolean isUrgent()           { return severity == Severity.URGENT; }

    public boolean isResolved()         { return timeResolved != NOT_RESOLVED; }

    /** Time the alert was resolved, or -1 if it has not been resolved yet. */
    public int getTimeResolved()        { return timeResolved; }

    /** Records the time at which a nurse resolved this alert (used in the next phase). */
    public void resolve(int time) {
        timeResolved = time;
    }

    @Override
    public String toString() {
        return "Alert[" + severity + ", raised at " + timeRaised + " min: "
                + observation.data() + "]";
    }
}
