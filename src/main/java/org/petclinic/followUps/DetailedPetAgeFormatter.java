package org.petclinic.followUps;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component("detailedFormatter")
public class DetailedPetAgeFormatter implements PetAgeFormatter {
    public String formatAge(LocalDate birthDate) {
        Period age = Period.between(birthDate, LocalDate.now());
        return age.getYears() + " years, " + age.getMonths() + " months";
    }
}