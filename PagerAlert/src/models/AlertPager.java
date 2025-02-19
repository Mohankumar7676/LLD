package models;

import java.util.Date;

public class AlertPager {
    private String id;
    private String service;
    private String severity;
    private String message;
    private Date timestamp;
    private boolean acknowledged;

    public AlertPager(String id, String service, String severity, String message) {
        this.id = id;
        this.service = service;
        this.severity = severity;
        this.message = message;
        this.timestamp = new Date();
        this.acknowledged = false;
    }

    public void acknowledge() {
        this.acknowledged = true;
    }

    public boolean isAcknowledged() {
        return acknowledged;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}
