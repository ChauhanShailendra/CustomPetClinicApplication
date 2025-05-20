package org.petclinic.controller;

import org.petclinic.model.PetType;
import org.petclinic.service.PetTypeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pet-types")
public class PetTypeController {

    private final PetTypeService petTypeService;

    public PetTypeController(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @PostMapping("/add")
    public ResponseEntity<PetType> create(@RequestBody PetType type) {
        return new ResponseEntity<>(petTypeService.save(type), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetType> getById(@PathVariable Long id) {
        return petTypeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public List<PetType> getAll() {
        return petTypeService.findAll();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PetType> update(@PathVariable Long id, @RequestBody PetType type) {
        return petTypeService.findById(id)
                .map(existing -> {
                    type.setId(id);
                    return new ResponseEntity<>(petTypeService.save(type), HttpStatus.OK);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        petTypeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
