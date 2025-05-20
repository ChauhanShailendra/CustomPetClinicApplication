package org.petclinic.followUps;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component("yearOnlyFormatter")
public class YearOnlyPetAgeFormatter implements PetAgeFormatter {
    public String formatAge(LocalDate birthDate) {
        int years = Period.between(birthDate, LocalDate.now()).getYears();
        return years + " years";
    }
}