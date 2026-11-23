package ua.edu.coursework.eco.model;

import java.time.LocalDate;

public class ResponseAction {
    private Long id;
    private Long incidentId;
    private String actionType;
    private String executor;
    private LocalDate plannedDate;

    public ResponseAction() {
    }

    public ResponseAction(Long id, Long incidentId, String actionType, String executor, LocalDate plannedDate) {
        this.id = id;
        this.incidentId = incidentId;
        this.actionType = actionType;
        this.executor = executor;
        this.plannedDate = plannedDate;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getIncidentId() { return incidentId; }
    public void setIncidentId(Long incidentId) { this.incidentId = incidentId; }
    public String getActionType() { return actionType; }
    public void setActionType(String actionType) { this.actionType = actionType; }
    public String getExecutor() { return executor; }
    public void setExecutor(String executor) { this.executor = executor; }
    public LocalDate getPlannedDate() { return plannedDate; }
    public void setPlannedDate(LocalDate plannedDate) { this.plannedDate = plannedDate; }
}
