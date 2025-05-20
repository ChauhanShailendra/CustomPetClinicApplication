package org.petclinic.followUps;

import org.petclinic.model.Owner;
import org.petclinic.model.Visit;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("flatPricing")
public class FlatPricingService implements PricingService {

    @Override
    public BigDecimal calculatePrice(Visit visit, Owner owner) {
        return BigDecimal.valueOf(200);
    }
}
