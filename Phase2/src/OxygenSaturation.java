/**
 * Is-A:   an Observation of blood oxygen saturation (SpO2).
 * Does-A: says whether the oxygen level is dangerous / urgent.
 * Has-A:  a percentage.
 *
 * Normal is 95 - 100%. Below 92% is a concern; below 88% is urgent.
 */
public class OxygenSaturation extends Observation {

    public static final int LOW = 92;
    public static final int URGENT_LOW = 88;

    private final int percent;

    public OxygenSaturation(int percent) {
        this.percent = percent;
    }

    public int getPercent() {
        return percent;
    }

    @Override
    public boolean dangerous() {
        return percent < LOW;
    }

    @Override
    public boolean urgent() {
        return percent < URGENT_LOW;
    }

    @Override
    public String data() {
        return "Oxygen saturation " + percent + "%";
    }
}
