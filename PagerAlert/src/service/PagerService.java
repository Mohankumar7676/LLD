package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.AlertPager;
import models.EscalationPolicy;
import models.UserAlert;

public class PagerService {

    private Map<String, AlertPager> alertMap = new HashMap<>();
    private Map<String, EscalationPolicy> policyMap;

    private NotificationService notificationService;

    public PagerService(NotificationService notificationService, Map<String, EscalationPolicy> policyMap) {
        this.notificationService = notificationService;
        this.policyMap = policyMap;
    }

    public void receiveAlert(AlertPager alert) {
        alertMap.put(alert.getId(), alert);
        escalate(alert);
    }

    public void escalate(AlertPager alert) {
        EscalationPolicy policy = policyMap.get(alert.getId());
        List<UserAlert> users = policy.getEscalationLevels();
        
        if (users.isEmpty()) return;

        // Notify first user
        UserAlert firstResponder = users.get(0);
        notificationService.sendSMS(firstResponder, "AlertPager: " + alert.getMessage());
    }

    public void acknowledge(String alertId) {
        if (alertMap.containsKey(alertId)) {
            alertMap.get(alertId).acknowledge();
            System.out.println("AlertPager " + alertId + " acknowledged.");
        }
    }
}
