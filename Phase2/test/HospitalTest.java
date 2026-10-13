import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HospitalTest {

    @Test
    void hasRequestedNumberOfPatients() {
        Hospital h = new Hospital(15);
        assertEquals(15, h.getPatientCount());
    }

    @Test
    void emptyHospitalAllowed() {
        assertEquals(0, new Hospital(0).getPatientCount());
    }

    @Test
    void negativePatientCountRejected() {
        assertThrows(IllegalArgumentException.class, () -> new Hospital(-1));
    }

    @Test
    void allPatientsExistAndAreUnique() {
        Hospital h = new Hospital(50);
        for (int i = 0; i < h.getPatientCount(); i++) {
            assertNotNull(h.getPatient(i));
            for (int j = i + 1; j < h.getPatientCount(); j++)
                assertNotEquals(h.getPatient(i).getId(), h.getPatient(j).getId());
        }
    }

    @Test
    void outOfRangeIndexThrows() {
        Hospital h = new Hospital(3);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> h.getPatient(3));
    }

    @Test
    void everyPatientHasDevices() {
        Hospital h = new Hospital(10);
        for (int i = 0; i < h.getPatientCount(); i++) {
            assertEquals(4, h.getPatient(i).getDeviceCount());
        }
    }

    @Test
    void updateAlertsReturnsNonNegativeCount() {
        Hospital h = new Hospital(10);
        assertTrue(h.updateAlerts(0) >= 0);
    }

    @Test
    void emptyHospitalRaisesNoAlerts() {
        assertEquals(0, new Hospital(0).updateAlerts(0));
    }

    @Test
    void updateAlertsEventuallyFindsAlertsAndNeverMoreThanDevices() {
        Hospital h = new Hospital(20);
        int total = 0;
        for (int step = 0; step < 100; step++) {
            int n = h.updateAlerts(step * 10);
            assertTrue(n <= 20 * 4);
            total += n;
        }
        assertTrue(total > 0);
    }
}
