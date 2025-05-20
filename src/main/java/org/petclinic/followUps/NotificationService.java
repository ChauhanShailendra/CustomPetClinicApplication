package org.petclinic.followUps;

import org.petclinic.model.Owner;

public interface NotificationService {
    void notifyOwner(Owner owner, String message);
}
