# Hospital Simulation (CMSC 131) - Phase 1

## Run
Open this folder in IntelliJ IDEA (File > Open > select the folder, or pom.xml).
IntelliJ imports it as a Maven project and downloads JUnit automatically.

- Run the program: open `src/Main.java` and click the green Run arrow next to `main`.
- Run the tests: right-click the `test` folder > Run 'All Tests'
  (or in a terminal: `mvn test`).

Requires JDK 11 or newer.

## Structure
- `src/`  - Main, Simulation, Hospital, Patient
- `test/` - JUnit 5 tests (SimulationTest, HospitalTest, PatientTest)
