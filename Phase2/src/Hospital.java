/**
 * Is-A:   the simulated hospital, with a population of patients (and, in later phases, nurses).
 * Does-A: creates its patients, gives access to them, and polls them for new alerts.
 * Has-A:  an array of Patient objects.
 */
public class Hospital {

    private final Patient[] patients;

    /** Creates a hospital populated with the given number of patients. */
    public Hospital(int numPatients) {
        if (numPatients < 0) {
            throw new IllegalArgumentException("numPatients must not be negative");
        }
        patients = new Patient[numPatients];
        for (int i = 0; i < patients.length; i++) {
            patients[i] = Patient.create();
        }
    }

    public int getPatientCount() {
        return patients.length;
    }

    /** Returns the patient at the given index. */
    public Patient getPatient(int index) {
        return patients[index];
    }

    /**
     * Polls each patient for new alerts. For now each alert is simply printed to the
     * screen; in the next phase they will be stored for the nurses.
     *
     * @param currentTime elapsed simulation time in minutes
     * @return how many alerts were raised in this step
     */
    public int updateAlerts(int currentTime) {
        int total = 0;
        for (int i = 0; i < patients.length; i++) {
            Alert[] alerts = patients[i].generateAlerts(currentTime);
            for (int j = 0; j < alerts.length; j++) {
                System.out.println(patients[i] + " " + alerts[j]);
            }
            total += alerts.length;
        }
        return total;
    }
}
