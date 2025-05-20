package org.petclinic.followUps;

import org.petclinic.model.Owner;
import org.springframework.stereotype.Component;

@Component("smsNotifier")
public class SmsNotificationService implements NotificationService {
    public void notifyOwner(Owner owner, String message) {
        System.out.println("SMS sent to " + owner.getFirstName() + " " + owner.getLastName() + " : " + message);
    }
}
