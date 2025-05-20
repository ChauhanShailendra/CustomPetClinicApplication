package org.petclinic.service;

import org.petclinic.dtos.PetDetailResponseDTO;
import org.petclinic.dtos.PetInfoRequestDTO;
import org.petclinic.model.PetDetails;

import java.util.List;
import java.util.Optional;

public interface PetDetailsService {
    PetDetails save(PetDetails details);
    Optional<PetDetails> findById(Long id);
    Optional<PetDetails> findByPetId(Long petId);
    List<PetDetails> findAll();
    void deleteById(Long id);
    PetDetailResponseDTO addPetInfo(PetInfoRequestDTO dto);
    PetDetails updatePetInfo(PetInfoRequestDTO dto);
}
