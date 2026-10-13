# Hospital Simulation (CMSC 131) - Phase 2: Devices, Observations, Alerts

## Run
Open this folder in IntelliJ IDEA (File > Open > select the folder, or pom.xml).
IntelliJ imports it as a Maven project and downloads JUnit automatically.

- Run the program: open `src/Main.java` and click the green Run arrow next to `main`.
  Alerts are printed to the screen as the simulation runs.
- Run the tests: right-click the `test` folder > Run 'All Tests'
  (or in a terminal: `mvn test`).

Requires JDK 11 or newer.

## Structure
- `src/`  - Main, Simulation, Hospital, Patient, Alert,
            Observation (Temperature, HeartRate, OxygenSaturation, CallBell),
            Device (TemperatureMonitor, HeartRateMonitor, OxygenMonitor, CallBellButton)
- `test/` - JUnit 5 tests for every class

## How alerts work
Each 10-minute step, Simulation asks the Hospital to update alerts; the Hospital polls each
Patient; each Patient samples its Devices, and any dangerous Observation becomes an Alert
(URGENT or ROUTINE), which is printed.
