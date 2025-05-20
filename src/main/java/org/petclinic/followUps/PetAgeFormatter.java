package org.petclinic.followUps;

import java.time.LocalDate;

public interface PetAgeFormatter {
    String formatAge(LocalDate birthDate);
}
