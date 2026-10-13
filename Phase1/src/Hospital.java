/**
 * Is-A:   the simulated hospital, with a population of patients (and, in later phases, nurses).
 * Does-A: creates its patients and gives access to them.
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
}
