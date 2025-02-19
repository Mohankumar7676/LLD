import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.AlertPager;
import models.EscalationPolicy;
import models.UserAlert;
import service.NotificationService;
import service.PagerService;

public class Main {
    public static void main(String[] args) {

        NotificationService notificationService = new NotificationService();

        UserAlert user1 = new UserAlert("1", "Alice", "alice@example.com", "+1234567890");
        UserAlert user2 = new UserAlert("2", "Bob", "bob@example.com", "+0987654321");
        UserAlert user3 = new UserAlert("3", "Charlie", "charlie@example.com", "+1122334455");

        List<UserAlert> escalationLevels = Arrays.asList(user1, user2, user3);
        EscalationPolicy policy = new EscalationPolicy("policy1", "ServiceX", escalationLevels, 2); // 2 min escalation time

        // Store Escalation Policy
        Map<String, EscalationPolicy> policyMap = new HashMap<>();
        policyMap.put("alert1", policy);

        // Initialize Pager Service
        PagerService pagerService = new PagerService(notificationService, policyMap);

        // Simulate an Incoming AlertPager
        AlertPager alert = new AlertPager("alert1", "ServiceX", "HIGH", "CPU usage exceeded 90%");
        pagerService.receiveAlert(alert);
        pagerService.acknowledge("alert1");

    }
}
