package org.petclinic.dtos;

import lombok.Data;

import java.util.List;

@Data
public class OwnerResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String telephone;
    private List<PetResponseDTO> pets;
}

