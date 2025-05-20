package org.petclinic.controller;

import jakarta.validation.Valid;
import org.petclinic.dtos.PetDetailResponseDTO;
import org.petclinic.dtos.PetInfoRequestDTO;
import org.petclinic.model.PetDetails;
import org.petclinic.service.PetDetailsService;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pet-details")
public class PetDetailsController {

    private final PetDetailsService petDetailsService;

    public PetDetailsController(PetDetailsService petDetailsService) {
        this.petDetailsService = petDetailsService;
    }

    @PostMapping("/addPetInfo")
    public ResponseEntity<PetDetailResponseDTO> addPetInfo(@Valid @RequestBody PetInfoRequestDTO dto) {
        PetDetailResponseDTO petDetailResponseDTO = petDetailsService.addPetInfo(dto);
        Link link = WebMvcLinkBuilder.linkTo(PetDetailsController.class)
                .slash("by-pet")
                .slash(petDetailResponseDTO.getPetId())
                .withRel("fetch details")
                .withType("GET");
        petDetailResponseDTO.addLink(link);
        return new ResponseEntity<>(petDetailResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update/PetInfo")
    public ResponseEntity<PetDetails> updatePetInfo(@Valid @RequestBody PetInfoRequestDTO dto) {
        PetDetails updatedDetails = petDetailsService.updatePetInfo(dto);
        return new ResponseEntity<>(updatedDetails, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PetDetails> create(@RequestBody PetDetails details) {
        return new ResponseEntity<>(petDetailsService.save(details), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetDetails> getById(@PathVariable Long id) {
        return petDetailsService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-pet/{petId}")
    public ResponseEntity<PetDetails> getByPetId(@PathVariable Long petId) {
        return petDetailsService.findByPetId(petId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public List<PetDetails> getAll() {
        return petDetailsService.findAll();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PetDetails> update(@PathVariable Long id, @RequestBody PetDetails details) {
        return petDetailsService.findById(id)
                .map(existing -> {
                    details.setId(id);
                    return new ResponseEntity<>(petDetailsService.save(details), HttpStatus.OK);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        petDetailsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
