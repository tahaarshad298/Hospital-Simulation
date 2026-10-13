/**
 * Is-A:   abstract base class for any monitor or instrument attached to a patient.
 * Does-A: produces a new Observation each time it is sampled.
 * Has-A:  nothing here; each subclass decides how its readings are produced.
 *
 * CLASS CONTRACT: sample() returns a new, non-null Observation of the one type
 * this device measures.
 */
public abstract class Device {

    /** Takes a new reading. */
    public abstract Observation sample();
}
