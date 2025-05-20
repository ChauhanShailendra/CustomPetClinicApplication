package org.petclinic.service;

import org.petclinic.dtos.OwnerRequestDTO;
import org.petclinic.dtos.OwnerResponseDTO;
import org.petclinic.model.Owner;

import java.util.List;
import java.util.Optional;

public interface OwnerService {
    Owner addOwner(OwnerRequestDTO dto);
    Owner updateOwner(Long ownerId, OwnerRequestDTO dto);
    OwnerResponseDTO getOwnerDetails(Long ownerId);
    Optional<Owner> findById(Long id);
    List<Owner> findAll();
    void deleteById(Long id);
    List<Owner> getOwnersByLastNameStartingWith(String lastName);
}
