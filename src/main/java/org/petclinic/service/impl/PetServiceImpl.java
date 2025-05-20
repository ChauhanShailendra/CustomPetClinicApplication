package org.petclinic.service.impl;

import jakarta.transaction.Transactional;
import org.petclinic.model.Pet;
import org.petclinic.repository.PetRepository;
import org.petclinic.service.PetService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PetServiceImpl implements PetService {

    private final PetRepository repository;

    public PetServiceImpl(PetRepository repository) {
        this.repository = repository;
    }

    @Override
    public Pet save(Pet pet) {
        return repository.save(pet);
    }

    @Override
    public Optional<Pet> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Pet> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
