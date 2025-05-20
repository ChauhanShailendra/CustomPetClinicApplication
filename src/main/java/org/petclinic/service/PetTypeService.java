package org.petclinic.service;

import org.petclinic.model.PetType;

import java.util.List;
import java.util.Optional;

public interface PetTypeService {
    PetType save(PetType petType);
    Optional<PetType> findById(Long id);
    List<PetType> findAll();
    void deleteById(Long id);
}
