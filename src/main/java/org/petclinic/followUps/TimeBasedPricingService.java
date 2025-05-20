package org.petclinic.followUps;

import org.petclinic.model.Owner;
import org.petclinic.model.Visit;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.DayOfWeek;

@Component("timeBasedPricing")
public class TimeBasedPricingService implements PricingService {

    @Override
    public BigDecimal calculatePrice(Visit visit, Owner owner) {
        DayOfWeek day = visit.getDate().getDayOfWeek();
        if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
            return BigDecimal.valueOf(800);
        }
        return BigDecimal.valueOf(500);
    }
}
