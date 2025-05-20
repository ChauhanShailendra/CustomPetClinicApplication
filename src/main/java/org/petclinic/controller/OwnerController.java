package org.petclinic.controller;

import jakarta.validation.Valid;
import org.petclinic.dtos.OwnerRequestDTO;
import org.petclinic.dtos.OwnerResponseDTO;
import org.petclinic.model.Owner;
import org.petclinic.service.OwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @PostMapping("/add")
    public ResponseEntity<Owner> addOwner(@Valid @RequestBody OwnerRequestDTO dto) {
        Owner owner = ownerService.addOwner(dto);
        return new ResponseEntity<>(owner, HttpStatus.CREATED);
    }

    @PutMapping("/update/{ownerId}")
    public ResponseEntity<Owner> updateOwner(@PathVariable Long ownerId, @Valid @RequestBody OwnerRequestDTO dto) {
        Owner updatedOwner = ownerService.updateOwner(ownerId, dto);
        return new ResponseEntity<>(updatedOwner, HttpStatus.OK);
    }

    @GetMapping("/{ownerId}/details")
    public ResponseEntity<OwnerResponseDTO> getOwnerDetails(@PathVariable Long ownerId) {
        OwnerResponseDTO response = ownerService.getOwnerDetails(ownerId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<Owner> getAll() {
        return ownerService.findAll();
    }

    @GetMapping("/findByLastName")
    public ResponseEntity<List<Owner>> getOwnersByLastNamePrefix(
            @RequestParam("lastName") String lastName) {
        List<Owner> owners = ownerService.getOwnersByLastNameStartingWith(lastName);
        return new ResponseEntity<>(owners, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ownerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
