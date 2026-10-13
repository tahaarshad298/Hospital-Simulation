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

    @Test
    void newPatientHasFourDevices() {
        Patient p = Patient.create();
        assertEquals(4, p.getDeviceCount());
        for (int i = 0; i < p.getDeviceCount(); i++) {
            assertNotNull(p.getDevice(i));
        }
    }

    @Test
    void newPatientHasOneOfEachDeviceType() {
        Patient p = Patient.create();
        boolean temp = false, hr = false, ox = false, bell = false;
        for (int i = 0; i < p.getDeviceCount(); i++) {
            Device d = p.getDevice(i);
            if (d instanceof TemperatureMonitor) temp = true;
            if (d instanceof HeartRateMonitor) hr = true;
            if (d instanceof OxygenMonitor) ox = true;
            if (d instanceof CallBellButton) bell = true;
        }
        assertTrue(temp && hr && ox && bell);
    }

    @Test
    void plainPatientStartsWithNoDevices() {
        assertEquals(0, new Patient().getDeviceCount());
    }

    @Test
    void addDeviceAppendsDevice() {
        Patient p = new Patient();
        Device d1 = new TemperatureMonitor();
        Device d2 = new CallBellButton();
        p.addDevice(d1);
        p.addDevice(d2);
        assertEquals(2, p.getDeviceCount());
        assertSame(d1, p.getDevice(0));
        assertSame(d2, p.getDevice(1));
    }

    @Test
    void addNullDeviceRejected() {
        assertThrows(IllegalArgumentException.class, () -> new Patient().addDevice(null));
    }

    @Test
    void patientWithNoDevicesRaisesNoAlerts() {
        Alert[] alerts = new Patient().generateAlerts(0);
        assertEquals(0, alerts.length);
    }

    @Test
    void generatedAlertsAreDangerousAndStampedWithTime() {
        Patient p = Patient.create();
        int total = 0;
        for (int step = 0; step < 2000; step++) {
            Alert[] alerts = p.generateAlerts(step * 10);
            assertTrue(alerts.length <= p.getDeviceCount());
            for (int i = 0; i < alerts.length; i++) {
                assertNotNull(alerts[i]);
                assertTrue(alerts[i].getObservation().dangerous());
                assertEquals(step * 10, alerts[i].getTimeRaised());
                assertEquals(alerts[i].getObservation().urgent(), alerts[i].isUrgent());
                assertFalse(alerts[i].isResolved());
            }
            total += alerts.length;
        }
        assertTrue(total > 0, "expected some alerts over 2000 steps");
    }

    @Test
    void deviceThatIsAlwaysDangerousAlwaysRaisesAlert() {
        Patient p = new Patient();
        p.addDevice(new Device() {
            @Override
            public Observation sample() {
                return new Temperature(41.0);
            }
        });
        Alert[] alerts = p.generateAlerts(70);
        assertEquals(1, alerts.length);
        assertEquals(Alert.Severity.URGENT, alerts[0].getSeverity());
        assertEquals(70, alerts[0].getTimeRaised());
    }

    @Test
    void safeObservationRaisesNoAlert() {
        Patient p = new Patient();
        p.addDevice(new Device() {
            @Override
            public Observation sample() {
                return new Temperature(36.8);
            }
        });
        assertEquals(0, p.generateAlerts(0).length);
    }

    @Test
    void routineDangerousObservationGivesRoutineAlert() {
        Patient p = new Patient();
        p.addDevice(new Device() {
            @Override
            public Observation sample() {
                return new CallBell(true);
            }
        });
        Alert[] alerts = p.generateAlerts(5);
        assertEquals(1, alerts.length);
        assertEquals(Alert.Severity.ROUTINE, alerts[0].getSeverity());
    }
}
