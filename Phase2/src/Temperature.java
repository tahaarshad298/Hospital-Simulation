/**
 * Is-A:   an Observation of body temperature.
 * Does-A: says whether the temperature is dangerous / urgent.
 * Has-A:  a temperature in degrees Celsius.
 *
 * Normal adult temperature is about 36.1 - 37.2 C. In acute care a fever is
 * 38.0 C (100.4 F) or higher; 40.0 C (104 F) or higher is a medical emergency.
 * Below 35.0 C is hypothermia, and below 32.0 C is severe.
 */
public class Temperature extends Observation {

    public static final double FEVER = 38.0;
    public static final double HIGH_FEVER = 40.0;
    public static final double HYPOTHERMIA = 35.0;
    public static final double SEVERE_HYPOTHERMIA = 32.0;

    private final double celsius;

    public Temperature(double celsius) {
        this.celsius = celsius;
    }

    public double getCelsius() {
        return celsius;
    }

    @Override
    public boolean dangerous() {
        return celsius >= FEVER || celsius < HYPOTHERMIA;
    }

    @Override
    public boolean urgent() {
        return celsius >= HIGH_FEVER || celsius < SEVERE_HYPOTHERMIA;
    }

    @Override
    public String data() {
        return String.format("Temperature %.1f C", celsius);
    }
}
