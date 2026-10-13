import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TemperatureTest {

    @Test
    void normalTemperatureIsNotDangerous() {
        Temperature t = new Temperature(36.8);
        assertFalse(t.dangerous());
        assertFalse(t.urgent());
    }

    @Test
    void feverStartsAt38() {
        assertFalse(new Temperature(37.9).dangerous());
        assertTrue(new Temperature(38.0).dangerous());
        assertTrue(new Temperature(39.0).dangerous());
    }

    @Test
    void moderateFeverIsNotUrgent() {
        assertFalse(new Temperature(39.9).urgent());
    }

    @Test
    void highFeverIsUrgent() {
        Temperature t = new Temperature(40.0);
        assertTrue(t.dangerous());
        assertTrue(t.urgent());
    }

    @Test
    void hypothermiaIsDangerous() {
        assertFalse(new Temperature(35.0).dangerous());
        assertTrue(new Temperature(34.9).dangerous());
        assertFalse(new Temperature(34.9).urgent());
    }

    @Test
    void severeHypothermiaIsUrgent() {
        Temperature t = new Temperature(31.5);
        assertTrue(t.dangerous());
        assertTrue(t.urgent());
    }

    @Test
    void dataShowsValueAndUnits() {
        String d = new Temperature(38.5).data();
        assertTrue(d.contains("38.5"));
        assertTrue(d.contains("C"));
    }

    @Test
    void getterReturnsValue() {
        assertEquals(37.2, new Temperature(37.2).getCelsius(), 0.0001);
    }

    @Test
    void isAnObservation() {
        Observation o = new Temperature(37.0);
        assertFalse(o.dangerous());
    }
}
