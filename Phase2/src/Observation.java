/**
 * Is-A:   abstract base class for any piece of data recorded about a patient
 *         (temperature, heart rate, oxygen level, call bell, ...).
 * Does-A: reports whether the recorded value is dangerous, whether it is urgent,
 *         and converts itself to a printable String.
 * Has-A:  nothing here; each subclass stores its own kind of data.
 *
 * CLASS CONTRACT (every subclass must follow this):
 *  - dangerous() returns true if and only if the value is outside safe bounds, so
 *    that the nursing staff should be alerted. It must not change the observation.
 *  - urgent() may return true ONLY if dangerous() is also true. It reports whether
 *    the situation needs immediate attention (urgent alert) or can wait its turn
 *    (routine alert).
 *  - data() returns a short human-readable String containing the recorded value
 *    and its units, never null.
 *  - An Observation is a fixed record of one moment: it is immutable once created.
 */
public abstract class Observation {

    /** True if this value is outside safe bounds. */
    public abstract boolean dangerous();

    /** True if this value needs immediate attention. Only possible when dangerous(). */
    public abstract boolean urgent();

    /** The recorded value as readable text, including units. */
    public abstract String data();
}
