package org.petclinic.followUps;

import org.petclinic.model.Owner;
import org.petclinic.model.Visit;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("locationBasedPricing")
public class LocationBasedPricingService implements PricingService {
    @Override
    public BigDecimal calculatePrice(Visit visit, Owner owner) {
        String city = owner.getCity().toLowerCase();
        if (city.equalsIgnoreCase("Mumbai")) {
            return BigDecimal.valueOf(800);
        }
        return BigDecimal.valueOf(500);
    }
}
