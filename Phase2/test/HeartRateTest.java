import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HeartRateTest {

    @Test
    void normalRateIsNotDangerous() {
        HeartRate h = new HeartRate(72);
        assertFalse(h.dangerous());
        assertFalse(h.urgent());
    }

    @Test
    void boundariesOfSafeRange() {
        assertFalse(new HeartRate(50).dangerous());
        assertTrue(new HeartRate(49).dangerous());
        assertFalse(new HeartRate(120).dangerous());
        assertTrue(new HeartRate(121).dangerous());
    }

    @Test
    void tooFastButNotUrgent() {
        HeartRate h = new HeartRate(140);
        assertTrue(h.dangerous());
        assertFalse(h.urgent());
    }

    @Test
    void veryFastIsUrgent() {
        HeartRate h = new HeartRate(151);
        assertTrue(h.dangerous());
        assertTrue(h.urgent());
    }

    @Test
    void veryslowIsUrgent() {
        HeartRate h = new HeartRate(35);
        assertTrue(h.dangerous());
        assertTrue(h.urgent());
        assertFalse(new HeartRate(45).urgent());
    }

    @Test
    void dataShowsValueAndUnits() {
        String d = new HeartRate(88).data();
        assertTrue(d.contains("88"));
        assertTrue(d.contains("bpm"));
    }
}
