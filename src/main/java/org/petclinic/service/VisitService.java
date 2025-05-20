package org.petclinic.service;

import jakarta.validation.Valid;
import org.petclinic.dtos.VisitRequestDTO;
import org.petclinic.model.Visit;

import java.util.List;
import java.util.Optional;

public interface VisitService {
    Visit addVisit(@Valid VisitRequestDTO dto);
    List<Visit> getVisitsForPet(Long ownerId, Long petId);
    Optional<Visit> findById(Long id);
    List<Visit> findAll();
    void deleteById(Long id);
}
