package org.petclinic.service;

import org.petclinic.model.Pet;

import java.util.List;
import java.util.Optional;

public interface PetService {
    Pet save(Pet pet);
    Optional<Pet> findById(Long id);
    List<Pet> findAll();
    void deleteById(Long id);
}
