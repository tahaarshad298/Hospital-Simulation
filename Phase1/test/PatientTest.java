import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PatientTest {

    @Test
    void createReturnsPatientWithId() {
        Patient p = Patient.create();
        assertNotNull(p);
        assertNotNull(p.getId());
    }

    @Test
    void idsAreUnique() {
        Patient a = Patient.create();
        Patient b = Patient.create();
        assertNotEquals(a.getId(), b.getId());
    }

    @Test
    void manyPatientsHaveDistinctIds() {
        Patient[] ps = new Patient[500];
        for (int i = 0; i < ps.length; i++) ps[i] = Patient.create();
        for (int i = 0; i < ps.length; i++)
            for (int j = i + 1; j < ps.length; j++)
                assertNotEquals(ps[i].getId(), ps[j].getId());
    }

    @Test
    void idDoesNotChange() {
        Patient p = Patient.create();
        assertEquals(p.getId(), p.getId());
    }

    @Test
    void toStringContainsId() {
        Patient p = Patient.create();
        assertTrue(p.toString().contains(p.getId().toString()));
    }
}
