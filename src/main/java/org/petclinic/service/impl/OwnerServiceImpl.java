package org.petclinic.service.impl;

import jakarta.transaction.Transactional;
import org.petclinic.dtos.OwnerRequestDTO;
import org.petclinic.dtos.OwnerResponseDTO;
import org.petclinic.dtos.PetResponseDTO;
import org.petclinic.followUps.PetAgeFormatter;
import org.petclinic.model.Owner;
import org.petclinic.model.PetType;
import org.petclinic.repository.OwnerRepository;
import org.petclinic.service.OwnerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    private final PetAgeFormatter ageFormatter;

    public OwnerServiceImpl(OwnerRepository repository, @Value("${pet.age.formatter}") String formatterBeanName,
                            ApplicationContext applicationContext) {
        this.ownerRepository = repository;
        this.ageFormatter = applicationContext.getBean(formatterBeanName, PetAgeFormatter.class);
    }

    @Override
    public Owner addOwner(OwnerRequestDTO dto) {
        Owner owner = new Owner();
        mapDtoToOwner(dto, owner);
        return ownerRepository.save(owner);
    }

    @Override
    public Owner updateOwner(Long ownerId, OwnerRequestDTO dto) {
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found with ID: " + ownerId));
        mapDtoToOwner(dto, owner);
        return ownerRepository.save(owner);
    }

    private void mapDtoToOwner(OwnerRequestDTO dto, Owner owner) {
        owner.setFirstName(dto.getFirstName());
        owner.setLastName(dto.getLastName());
        owner.setAddress(dto.getAddress());
        owner.setCity(dto.getCity());
        owner.setTelephone(dto.getTelephone());
    }

    @Override
    public List<Owner> getOwnersByLastNameStartingWith(String lastName) {
        return ownerRepository.findByLastNameStartingWith(lastName);
    }

    @Override
    public OwnerResponseDTO getOwnerDetails(Long ownerId) {
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        OwnerResponseDTO ownerDto = new OwnerResponseDTO();
        ownerDto.setId(owner.getId());
        ownerDto.setFirstName(owner.getFirstName());
        ownerDto.setLastName(owner.getLastName());
        ownerDto.setAddress(owner.getAddress());
        ownerDto.setCity(owner.getCity());
        ownerDto.setTelephone(owner.getTelephone());

        List<PetResponseDTO> petDtos = owner.getPets().stream().map(pet -> {
            PetResponseDTO petDto = new PetResponseDTO();
            petDto.setId(pet.getId());
            petDto.setName(pet.getName());
            petDto.setBirthDate(ageFormatter.formatAge(pet.getBirthDate()));
            PetType type = pet.getType();
            if (type != null) {
                petDto.setPetTypeId(type.getId());
                petDto.setPetTypeName(type.getName());
            }
            return petDto;
        }).toList();

        ownerDto.setPets(petDtos);
        return ownerDto;
    }

    @Override
    public Optional<Owner> findById(Long id) {
        return ownerRepository.findById(id);
    }

    @Override
    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        ownerRepository.deleteById(id);
    }
}
