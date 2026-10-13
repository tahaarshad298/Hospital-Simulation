import java.util.UUID;

/**
 * Is-A:   a single patient in the simulated hospital.
 * Does-A: provides access to its unique identifier, accepts devices, and polls its
 *         devices to generate alerts; toString() for debugging output.
 * Has-A:  a unique identifier (UUID) and an array of Devices attached to the patient.
 */
public class Patient {

    private final UUID id;
    private Device[] devices;

    /** Computes and stores this patient's unique identity, with no devices yet. */
    public Patient() {
        id = UUID.randomUUID();
        devices = new Device[0];
    }

    /**
     * Static factory method: the Hospital calls this to create Patient objects.
     * Each new patient gets a full set of devices attached.
     */
    public static Patient create() {
        Patient p = new Patient();
        p.addDevice(new TemperatureMonitor());
        p.addDevice(new HeartRateMonitor());
        p.addDevice(new OxygenMonitor());
        p.addDevice(new CallBellButton());
        return p;
    }

    /** Attaches a device to this patient. */
    public void addDevice(Device device) {
        if (device == null) {
            throw new IllegalArgumentException("device must not be null");
        }
        Device[] bigger = new Device[devices.length + 1];
        for (int i = 0; i < devices.length; i++) {
            bigger[i] = devices[i];
        }
        bigger[devices.length] = device;
        devices = bigger;
    }

    public int getDeviceCount() {
        return devices.length;
    }

    public Device getDevice(int index) {
        return devices[index];
    }

    /**
     * Polls every attached device. For each new observation that is dangerous, creates
     * an Alert (urgent or routine) stamped with the given time.
     *
     * @param currentTime elapsed simulation time in minutes
     * @return the alerts raised this step (an empty array if there were none)
     */
    public Alert[] generateAlerts(int currentTime) {
        Alert[] found = new Alert[devices.length];
        int count = 0;
        for (int i = 0; i < devices.length; i++) {
            Observation obs = devices[i].sample();
            if (obs.dangerous()) {
                Alert.Severity severity = obs.urgent() ? Alert.Severity.URGENT
                                                       : Alert.Severity.ROUTINE;
                found[count] = new Alert(obs, currentTime, severity);
                count++;
            }
        }
        Alert[] result = new Alert[count];
        for (int i = 0; i < count; i++) {
            result[i] = found[i];
        }
        return result;
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
