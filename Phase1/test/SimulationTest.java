import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SimulationTest {

    @Test
    void setupCreatesHospitalWithPatients() {
        Simulation sim = new Simulation();
        sim.setup();
        assertNotNull(sim.getHospital());
        assertEquals(sim.getNumPatients(), sim.getHospital().getPatientCount());
    }

    @Test
    void timeStartsAtZeroAfterSetup() {
        Simulation sim = new Simulation();
        sim.setup();
        assertEquals(0, sim.getCurrentTime());
    }

    @Test
    void runAdvancesTimeThroughFullPeriod() {
        Simulation sim = new Simulation();
        sim.setup();
        sim.run();
        assertEquals(sim.getTotalMinutes(), sim.getCurrentTime());
    }

    @Test
    void defaultsAre10MinuteStepsAnd24Hours() {
        Simulation sim = new Simulation();
        assertEquals(10, sim.getTimeStepMinutes());
        assertEquals(24 * 60, sim.getTotalMinutes());
    }

    @Test
    void sharedRandomIsSingleInstance() {
        assertNotNull(Simulation.getRandom());
        assertSame(Simulation.getRandom(), Simulation.getRandom());
    }

    @Test
    void nextDoubleInRange() {
        for (int i = 0; i < 1000; i++) {
            double d = Simulation.nextDouble();
            assertTrue(d >= 0.0 && d < 1.0);
        }
    }

    @Test
    void processRunsWithoutError() {
        Simulation sim = new Simulation();
        sim.setup();
        sim.run();
        assertDoesNotThrow(sim::process);
    }
}
