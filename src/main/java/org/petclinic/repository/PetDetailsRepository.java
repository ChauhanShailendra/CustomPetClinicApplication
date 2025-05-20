package org.petclinic.repository;

import org.petclinic.model.PetDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PetDetailsRepository extends JpaRepository<PetDetails, Long> {
    Optional<PetDetails> findByPetId(Long petId);
}
