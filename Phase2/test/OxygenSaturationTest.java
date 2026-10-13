import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OxygenSaturationTest {

    @Test
    void normalLevelIsNotDangerous() {
        OxygenSaturation o = new OxygenSaturation(98);
        assertFalse(o.dangerous());
        assertFalse(o.urgent());
    }

    @Test
    void boundaryOfSafeRange() {
        assertFalse(new OxygenSaturation(92).dangerous());
        assertTrue(new OxygenSaturation(91).dangerous());
    }

    @Test
    void lowButNotUrgent() {
        OxygenSaturation o = new OxygenSaturation(90);
        assertTrue(o.dangerous());
        assertFalse(o.urgent());
    }

    @Test
    void veryLowIsUrgent() {
        OxygenSaturation o = new OxygenSaturation(87);
        assertTrue(o.dangerous());
        assertTrue(o.urgent());
        assertFalse(new OxygenSaturation(88).urgent());
    }

    @Test
    void dataShowsValueAndUnits() {
        String d = new OxygenSaturation(95).data();
        assertTrue(d.contains("95"));
        assertTrue(d.contains("%"));
    }
}
