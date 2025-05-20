package org.petclinic.service.impl;

import jakarta.transaction.Transactional;
import org.petclinic.model.PetType;
import org.petclinic.repository.PetTypeRepository;
import org.petclinic.service.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PetTypeServiceImpl implements PetTypeService {

    private final PetTypeRepository repository;

    public PetTypeServiceImpl(PetTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public PetType save(PetType petType) {
        return repository.save(petType);
    }

    @Override
    public Optional<PetType> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<PetType> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
