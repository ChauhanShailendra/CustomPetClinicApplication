package org.petclinic.service.impl;

import jakarta.transaction.Transactional;
import org.petclinic.dtos.PetDetailResponseDTO;
import org.petclinic.dtos.PetInfoRequestDTO;
import org.petclinic.model.Owner;
import org.petclinic.model.Pet;
import org.petclinic.model.PetDetails;
import org.petclinic.model.PetType;
import org.petclinic.repository.OwnerRepository;
import org.petclinic.repository.PetDetailsRepository;
import org.petclinic.repository.PetRepository;
import org.petclinic.repository.PetTypeRepository;
import org.petclinic.service.PetDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PetDetailsServiceImpl implements PetDetailsService {

    private final PetDetailsRepository petDetailsRepository;
    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;
    private final PetTypeRepository petTypeRepository;

    public PetDetailsServiceImpl(PetDetailsRepository petDetailsRepository,
                                 OwnerRepository ownerRepository, PetRepository petRepository,
                                 PetTypeRepository petTypeRepository) {
        this.petDetailsRepository = petDetailsRepository;
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public PetDetailResponseDTO addPetInfo(PetInfoRequestDTO dto) {
        Owner owner = ownerRepository.findById(dto.getOwnerId())
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        PetType petType = petTypeRepository.findById(dto.getPetTypeId())
                .orElseThrow(() -> new RuntimeException("Pet type not found"));

        Pet pet = new Pet();
        pet.setName(dto.getPetName());
        pet.setBirthDate(dto.getBirthDate());
        pet.setType(petType);

        owner.addPet(pet);
        petRepository.save(pet);

        PetDetails petDetails = new PetDetails();
        petDetails.setPet(pet);
        petDetails.setTemperament(dto.getTemperament());
        petDetails.setLengthCm(dto.getLengthCm());
        petDetails.setWeightKg(dto.getWeightKg());

        PetDetails petDetailsInDB = petDetailsRepository.save(petDetails);
        return mapPetDetailsToResponseDto(petDetailsInDB);
    }

    private PetDetailResponseDTO mapPetDetailsToResponseDto(PetDetails petDetailsInDB) {
        PetDetailResponseDTO petDetailResponseDTO = new PetDetailResponseDTO();
        petDetailResponseDTO.setPetId(petDetailsInDB.getPet().getId());
        petDetailResponseDTO.setPetName(petDetailsInDB.getPet().getName());
        petDetailResponseDTO.setBirthDate(String.valueOf(petDetailsInDB.getPet().getBirthDate()));
        petDetailResponseDTO.setPetTypeName(petDetailsInDB.getPet().getType().getName());
        petDetailResponseDTO.setTemperament(petDetailsInDB.getTemperament());
        petDetailResponseDTO.setLengthCm(petDetailsInDB.getLengthCm());
        petDetailResponseDTO.setWeightKg(petDetailsInDB.getWeightKg());
        return petDetailResponseDTO;
    }

    @Override
    public PetDetails updatePetInfo(PetInfoRequestDTO dto) {
        Owner owner = ownerRepository.findById(dto.getOwnerId())
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        Pet pet = owner.getPet(dto.getPetId());
        if (pet == null) {
            throw new RuntimeException("Pet not found for this owner");
        }

        PetType petType = petTypeRepository.findById(dto.getPetTypeId())
                .orElseThrow(() -> new RuntimeException("Pet type not found"));

        pet.setName(dto.getPetName());
        pet.setBirthDate(dto.getBirthDate());
        pet.setType(petType);
        petRepository.save(pet);

        PetDetails petDetails = petDetailsRepository.findByPetId(dto.getPetId())
                .orElseThrow(() -> new RuntimeException("PetDetails not found for pet"));

        petDetails.setTemperament(dto.getTemperament());
        petDetails.setLengthCm(dto.getLengthCm());
        petDetails.setWeightKg(dto.getWeightKg());

        return petDetailsRepository.save(petDetails);
    }


    @Override
    public PetDetails save(PetDetails details) {
        return petDetailsRepository.save(details);
    }

    @Override
    public Optional<PetDetails> findById(Long id) {
        return petDetailsRepository.findById(id);
    }

    @Override
    public Optional<PetDetails> findByPetId(Long petId) {
        return petDetailsRepository.findByPetId(petId);
    }

    @Override
    public List<PetDetails> findAll() {
        return petDetailsRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        petDetailsRepository.deleteById(id);
    }
}
