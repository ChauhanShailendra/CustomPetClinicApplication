package org.petclinic.service.impl;

import jakarta.transaction.Transactional;
import org.petclinic.dtos.VisitRequestDTO;
import org.petclinic.followUps.NotificationService;
import org.petclinic.followUps.PricingService;
import org.petclinic.model.Owner;
import org.petclinic.model.Pet;
import org.petclinic.model.Visit;
import org.petclinic.repository.OwnerRepository;
import org.petclinic.repository.PetRepository;
import org.petclinic.repository.VisitRepository;
import org.petclinic.service.VisitService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VisitServiceImpl implements VisitService {

    private final VisitRepository repository;
    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;
    private final NotificationService notificationService;
    private final PricingService pricingService;

    public VisitServiceImpl(VisitRepository repository, OwnerRepository ownerRepository,
                            PetRepository petRepository, @Value("${visit.notification.medium}") String notificationServiceName,
                            @Value("${visit.pricing}") String pricingServiceName, ApplicationContext applicationContext) {
        this.repository = repository;
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
        this.notificationService = applicationContext.getBean(notificationServiceName, NotificationService.class);
        this.pricingService = applicationContext.getBean(pricingServiceName, PricingService.class);
    }

    @Override
    public Visit addVisit(VisitRequestDTO dto) {
        Owner owner = ownerRepository.findById(dto.getOwnerId())
                .orElseThrow(() -> new RuntimeException("Owner not found with ID: " + dto.getOwnerId()));

        Pet pet = petRepository.findById(dto.getPetId())
                .orElseThrow(() -> new RuntimeException("Pet not found with ID: " + dto.getPetId()));

        if (!owner.getPets().contains(pet)) {
            throw new RuntimeException("Pet does not belong to the given owner");
        }

        Visit visit = new Visit();
        visit.setDate(dto.getDate());
        visit.setDescription(dto.getDescription());
        visit.setPrice(pricingService.calculatePrice(visit, owner));
        pet.addVisit(visit);

        Pet savedPet = petRepository.save(pet);

        Visit savedVisit = savedPet.getVisits().stream()
                .filter(v -> v.getDescription().equals(visit.getDescription())
                        && v.getDate().equals(visit.getDate()))
                .findFirst()
                .orElse(visit);

        notificationService.notifyOwner(owner, "Hi, " + owner.getFirstName() + " " + owner.getLastName() +
                "Visit is scheduled for pet " + pet.getName() + " on " + visit.getDate());
        return savedVisit;
    }

    @Override
    public List<Visit> getVisitsForPet(Long ownerId, Long petId) {
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found with ID: " + ownerId));

        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new RuntimeException("Pet not found with ID: " + petId));

        if (!owner.getPets().contains(pet)) {
            throw new RuntimeException("Pet does not belong to the given owner");
        }

        return pet.getVisits().stream().toList();
    }

    @Override
    public Optional<Visit> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Visit> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
