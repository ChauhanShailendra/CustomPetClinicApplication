package org.petclinic.followUps;

import org.petclinic.model.Owner;
import org.petclinic.model.Visit;

import java.math.BigDecimal;

public interface PricingService {
    BigDecimal calculatePrice(Visit visit, Owner owner);
}
