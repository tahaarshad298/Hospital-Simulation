/**
 * Is-A:   an Observation of heart rate.
 * Does-A: says whether the heart rate is dangerous / urgent.
 * Has-A:  beats per minute.
 *
 * Normal resting rate is 60 - 100 bpm. Below 50 or above 120 needs attention;
 * below 40 or above 150 is urgent (possible cardiac event).
 */
public class HeartRate extends Observation {

    public static final int LOW = 50;
    public static final int HIGH = 120;
    public static final int URGENT_LOW = 40;
    public static final int URGENT_HIGH = 150;

    private final int bpm;

    public HeartRate(int bpm) {
        this.bpm = bpm;
    }

    public int getBpm() {
        return bpm;
    }

    @Override
    public boolean dangerous() {
        return bpm < LOW || bpm > HIGH;
    }

    @Override
    public boolean urgent() {
        return bpm < URGENT_LOW || bpm > URGENT_HIGH;
    }

    @Override
    public String data() {
        return "Heart rate " + bpm + " bpm";
    }
}
