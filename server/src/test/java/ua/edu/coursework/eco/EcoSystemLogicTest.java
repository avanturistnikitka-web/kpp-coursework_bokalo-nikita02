package ua.edu.coursework.eco;

import org.junit.jupiter.api.Test;
import ua.edu.coursework.eco.model.Incident;
import ua.edu.coursework.eco.model.IncidentState;
import ua.edu.coursework.eco.service.IncidentService;
import ua.edu.coursework.eco.service.RiskCalculator;

import static org.junit.jupiter.api.Assertions.*;

class EcoSystemLogicTest {

    private final IncidentService incidentService = new IncidentService();

    @Test
    void testRiskIndexCalculationStandard() {
        double risk = RiskCalculator.calculateRiskIndex(75.0, 60.0, 50.0);
        assertEquals(63.5, risk, 0.001);
    }

    @Test
    void testRiskIndexZeroBoundaries() {
        assertEquals(0.0, RiskCalculator.calculateRiskIndex(0.0, 0.0, 0.0), 0.001);
    }

    @Test
    void testRiskIndexMaxBoundaries() {
        assertEquals(100.0, RiskCalculator.calculateRiskIndex(100.0, 100.0, 100.0), 0.001);
    }

    @Test
    void testRiskIndexNegativeFactorThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> RiskCalculator.calculateRiskIndex(-1.0, 50.0, 50.0));
    }

    @Test
    void testRiskIndexAboveHundredFactorThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> RiskCalculator.calculateRiskIndex(50.0, 100.5, 50.0));
    }

    @Test
    void testValidLifecycleTransitions() {
        Incident incident = new Incident(1L, 1L, "итік нафтопродуктів", "пис",
                IncidentState.REGISTERED, 70.0, 60.0, 50.0, 0.0);

        assertEquals(IncidentState.VERIFIED, incidentService.transitionState(incident, IncidentState.VERIFIED));
        assertEquals(IncidentState.RESPONSE, incidentService.transitionState(incident, IncidentState.RESPONSE));
        assertEquals(IncidentState.CLOSED, incidentService.transitionState(incident, IncidentState.CLOSED));
    }

    @Test
    void testSkipLifecycleStateThrows() {
        Incident incident = new Incident(1L, 1L, "абруднення", "пис",
                IncidentState.REGISTERED, 50.0, 40.0, 30.0, 0.0);

        assertThrows(IllegalStateException.class,
                () -> incidentService.transitionState(incident, IncidentState.RESPONSE));
    }

    @Test
    void testClosedStateCannotTransition() {
        Incident incident = new Incident(1L, 1L, "абруднення", "пис",
                IncidentState.CLOSED, 50.0, 40.0, 30.0, 41.5);

        assertThrows(IllegalStateException.class,
                () -> incidentService.transitionState(incident, IncidentState.VERIFIED));
    }

    @Test
    void testPrepareIncidentSetsDefaultStateAndCalculatesRisk() {
        Incident incident = new Incident();
        incident.setLocationId(1L);
        incident.setTitle("еревищення нормативу");
        incident.setState(null);
        incident.setF1(50.0);
        incident.setF2(40.0);
        incident.setF3(30.0);

        incidentService.prepareForCreateOrUpdate(incident);

        assertEquals(IncidentState.REGISTERED, incident.getState());
        assertEquals(41.5, incident.getRiskIndex(), 0.001);
    }

    @Test
    void testPrepareIncidentRejectsBlankTitle() {
        Incident incident = new Incident();
        incident.setLocationId(1L);
        incident.setTitle("   ");

        assertThrows(IllegalArgumentException.class,
                () -> incidentService.prepareForCreateOrUpdate(incident));
    }
}
