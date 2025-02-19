package models;

import java.util.List;

public class EscalationPolicy {
    private String id;
    private String service;
    private List<UserAlert> escalationLevels;
    private int timeLimit; // Time before escalating

    public EscalationPolicy(String id, String service, List<UserAlert> escalationLevels, int timeLimit) {
        this.id = id;
        this.service = service;
        this.escalationLevels = escalationLevels;
        this.timeLimit = timeLimit;
    }

    public List<UserAlert> getEscalationLevels() {
        return escalationLevels;
    }

    public int getTimeLimit() {
        return timeLimit;
    }
}
