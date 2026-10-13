/**
 * Is-A:   an Observation of the patient's call bell.
 * Does-A: says whether the patient is calling for a nurse. A pressed bell is always
 *         something the staff must respond to, but it is a routine (not urgent) alert.
 * Has-A:  whether the bell was pressed.
 */
public class CallBell extends Observation {

    private final boolean pressed;

    public CallBell(boolean pressed) {
        this.pressed = pressed;
    }

    public boolean isPressed() {
        return pressed;
    }

    @Override
    public boolean dangerous() {
        return pressed;
    }

    @Override
    public boolean urgent() {
        return false;
    }

    @Override
    public String data() {
        return pressed ? "Call bell pressed" : "Call bell not pressed";
    }
}
