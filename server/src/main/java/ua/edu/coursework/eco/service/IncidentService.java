package ua.edu.coursework.eco.service;

import ua.edu.coursework.eco.model.Incident;
import ua.edu.coursework.eco.model.IncidentState;

public class IncidentService {

    public void prepareForCreateOrUpdate(Incident incident) {
        if (incident == null) {
            throw new IllegalArgumentException("Incident must not be null");
        }
        if (incident.getLocationId() == null || incident.getLocationId() <= 0) {
            throw new IllegalArgumentException("Valid locationId is required");
        }
        if (incident.getTitle() == null || incident.getTitle().isBlank()) {
            throw new IllegalArgumentException("Incident title must not be blank");
        }
        if (incident.getState() == null) {
            incident.setState(IncidentState.REGISTERED);
        }
        double risk = RiskCalculator.calculateRiskIndex(
                incident.getF1(), incident.getF2(), incident.getF3()
        );
        incident.setRiskIndex(risk);
    }

    public IncidentState transitionState(Incident incident, IncidentState nextState) {
        if (incident == null) {
            throw new IllegalArgumentException("Incident must not be null");
        }
        IncidentState current = incident.getState() != null ? incident.getState() : IncidentState.REGISTERED;
        if (!current.canTransitionTo(nextState)) {
            throw new IllegalStateException(
                    "Invalid state transition from " + current + " to " + nextState
            );
        }
        incident.setState(nextState);
        return nextState;
    }
}
