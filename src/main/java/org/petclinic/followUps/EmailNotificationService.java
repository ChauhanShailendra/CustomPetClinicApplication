package org.petclinic.followUps;

import org.petclinic.model.Owner;
import org.springframework.stereotype.Component;

@Component("emailNotifier")
public class EmailNotificationService implements NotificationService {
    public void notifyOwner(Owner owner, String message) {
        System.out.println("Email sent to " + owner.getFirstName() + " " + owner.getLastName() + " : " + message);
    }
}
