import java.util.UUID;

/**
 * Is-A:   a single patient in the simulated hospital.
 * Does-A: provides access to its unique identifier; toString() for debugging output.
 * Has-A:  a unique identifier (UUID).
 */
public class Patient {

    private final UUID id;

    /** Computes and stores this patient's unique identity. */
    public Patient() {
        id = UUID.randomUUID();
    }

    /** Static factory method: the Hospital calls this to create Patient objects. */
    public static Patient create() {
        return new Patient();
    }

    /** Provides access to the patient's identifier. */
    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Patient[id=" + id + "]";
    }
}
