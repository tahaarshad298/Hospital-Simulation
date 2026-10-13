import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CallBellTest {

    @Test
    void pressedBellIsDangerousButRoutine() {
        CallBell c = new CallBell(true);
        assertTrue(c.isPressed());
        assertTrue(c.dangerous());
        assertFalse(c.urgent());
    }

    @Test
    void unpressedBellIsNotDangerous() {
        CallBell c = new CallBell(false);
        assertFalse(c.isPressed());
        assertFalse(c.dangerous());
        assertFalse(c.urgent());
    }

    @Test
    void dataDescribesState() {
        assertTrue(new CallBell(true).data().contains("pressed"));
        assertNotEquals(new CallBell(true).data(), new CallBell(false).data());
    }
}
