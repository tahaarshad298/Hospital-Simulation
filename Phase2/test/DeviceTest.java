import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DeviceTest {

    private static final int SAMPLES = 5000;

    @Test
    void temperatureMonitorReturnsTemperaturesInPlausibleRange() {
        Device d = new TemperatureMonitor();
        for (int i = 0; i < SAMPLES; i++) {
            Observation o = d.sample();
            assertTrue(o instanceof Temperature);
            double c = ((Temperature) o).getCelsius();
            assertTrue(c >= 34.0 && c <= 41.5, "out of range: " + c);
        }
    }

    @Test
    void temperatureMonitorProducesSomeFeversButMostlyNormal() {
        Device d = new TemperatureMonitor();
        int dangerous = 0;
        for (int i = 0; i < SAMPLES; i++) {
            if (d.sample().dangerous()) dangerous++;
        }
        assertTrue(dangerous > 0, "expected at least some fevers");
        assertTrue(dangerous < SAMPLES / 4, "too many dangerous readings: " + dangerous);
    }

    @Test
    void heartRateMonitorReturnsHeartRatesInPlausibleRange() {
        Device d = new HeartRateMonitor();
        for (int i = 0; i < SAMPLES; i++) {
            Observation o = d.sample();
            assertTrue(o instanceof HeartRate);
            int bpm = ((HeartRate) o).getBpm();
            assertTrue(bpm >= 30 && bpm <= 180, "out of range: " + bpm);
        }
    }

    @Test
    void oxygenMonitorReturnsPercentagesInPlausibleRange() {
        Device d = new OxygenMonitor();
        for (int i = 0; i < SAMPLES; i++) {
            Observation o = d.sample();
            assertTrue(o instanceof OxygenSaturation);
            int p = ((OxygenSaturation) o).getPercent();
            assertTrue(p >= 80 && p <= 100, "out of range: " + p);
        }
    }

    @Test
    void callBellButtonReturnsCallBellsAndSometimesPressed() {
        Device d = new CallBellButton();
        int pressed = 0;
        for (int i = 0; i < SAMPLES; i++) {
            Observation o = d.sample();
            assertTrue(o instanceof CallBell);
            if (((CallBell) o).isPressed()) pressed++;
        }
        assertTrue(pressed > 0, "bell never pressed");
        assertTrue(pressed < SAMPLES / 4, "bell pressed too often: " + pressed);
    }

    @Test
    void everyDeviceReturnsNewNonNullObservation() {
        Device[] devices = {
            new TemperatureMonitor(), new HeartRateMonitor(),
            new OxygenMonitor(), new CallBellButton()
        };
        for (int i = 0; i < devices.length; i++) {
            Observation a = devices[i].sample();
            Observation b = devices[i].sample();
            assertNotNull(a);
            assertNotNull(b);
            assertNotSame(a, b);
            assertNotNull(a.data());
        }
    }

    @Test
    void urgentImpliesDangerousForEverySample() {
        Device[] devices = {
            new TemperatureMonitor(), new HeartRateMonitor(),
            new OxygenMonitor(), new CallBellButton()
        };
        for (int i = 0; i < devices.length; i++) {
            for (int n = 0; n < SAMPLES; n++) {
                Observation o = devices[i].sample();
                if (o.urgent()) {
                    assertTrue(o.dangerous());
                }
            }
        }
    }
}
