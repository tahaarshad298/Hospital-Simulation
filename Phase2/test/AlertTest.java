import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AlertTest {

    @Test
    void storesObservationTimeAndSeverity() {
        Observation o = new Temperature(40.5);
        Alert a = new Alert(o, 120, Alert.Severity.URGENT);
        assertSame(o, a.getObservation());
        assertEquals(120, a.getTimeRaised());
        assertEquals(Alert.Severity.URGENT, a.getSeverity());
        assertTrue(a.isUrgent());
    }

    @Test
    void routineAlertIsNotUrgent() {
        Alert a = new Alert(new CallBell(true), 10, Alert.Severity.ROUTINE);
        assertFalse(a.isUrgent());
    }

    @Test
    void startsUnresolved() {
        Alert a = new Alert(new HeartRate(130), 50, Alert.Severity.ROUTINE);
        assertFalse(a.isResolved());
        assertEquals(-1, a.getTimeResolved());
    }

    @Test
    void resolveRecordsTime() {
        Alert a = new Alert(new HeartRate(130), 50, Alert.Severity.ROUTINE);
        a.resolve(80);
        assertTrue(a.isResolved());
        assertEquals(80, a.getTimeResolved());
    }

    @Test
    void toStringShowsSeverityTimeAndData() {
        Alert a = new Alert(new OxygenSaturation(85), 30, Alert.Severity.URGENT);
        String s = a.toString();
        assertTrue(s.contains("URGENT"));
        assertTrue(s.contains("30"));
        assertTrue(s.contains("85"));
    }

    @Test
    void nullObservationRejected() {
        assertThrows(IllegalArgumentException.class,
                () -> new Alert(null, 0, Alert.Severity.ROUTINE));
    }

    @Test
    void nullSeverityRejected() {
        assertThrows(IllegalArgumentException.class,
                () -> new Alert(new CallBell(true), 0, null));
    }
}
