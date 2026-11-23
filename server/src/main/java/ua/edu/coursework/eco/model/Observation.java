package ua.edu.coursework.eco.model;
public class Observation {
    private Long id; private Long incidentId; private String metricName;
    private double value; private double threshold; private double durationScore; private double scaleScore;
    public Observation() {}
    public Observation(Long id, Long incidentId, String metricName, double value,
                       double threshold, double durationScore, double scaleScore) {
        this.id = id; this.incidentId = incidentId; this.metricName = metricName;
        this.value = value; this.threshold = threshold; this.durationScore = durationScore; this.scaleScore = scaleScore;
    }
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public Long getIncidentId() { return incidentId; } public void setIncidentId(Long incidentId) { this.incidentId = incidentId; }
    public String getMetricName() { return metricName; } public void setMetricName(String metricName) { this.metricName = metricName; }
    public double getValue() { return value; } public void setValue(double value) { this.value = value; }
    public double getThreshold() { return threshold; } public void setThreshold(double threshold) { this.threshold = threshold; }
    public double getDurationScore() { return durationScore; } public void setDurationScore(double durationScore) { this.durationScore = durationScore; }
    public double getScaleScore() { return scaleScore; } public void setScaleScore(double scaleScore) { this.scaleScore = scaleScore; }
}
