package org.petclinic.controller;

import jakarta.validation.Valid;
import org.petclinic.dtos.VisitRequestDTO;
import org.petclinic.model.Visit;
import org.petclinic.service.VisitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visits")
public class VisitController {

    private final VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @PostMapping("/add")
    public ResponseEntity<Visit> addVisit(@Valid @RequestBody VisitRequestDTO dto) {
        Visit savedVisit = visitService.addVisit(dto);
        return new ResponseEntity<>(savedVisit, HttpStatus.CREATED);
    }

    @GetMapping("/getByOwnerAndPet")
    public ResponseEntity<List<Visit>> getVisitsByOwnerAndPet(@RequestParam Long ownerId, @RequestParam Long petId) {
        List<Visit> visits = visitService.getVisitsForPet(ownerId, petId);
        return new ResponseEntity<>(visits, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        visitService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

