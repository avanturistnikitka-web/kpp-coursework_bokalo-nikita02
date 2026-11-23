package ua.edu.coursework.eco.model;
public enum IncidentState {
    REGISTERED, VERIFIED, RESPONSE, CLOSED;
    public boolean canTransitionTo(IncidentState next) {
        if (next == null) return false;
        return switch (this) {
            case REGISTERED -> next == VERIFIED;
            case VERIFIED -> next == RESPONSE;
            case RESPONSE -> next == CLOSED;
            case CLOSED -> false;
        };
    }
}
