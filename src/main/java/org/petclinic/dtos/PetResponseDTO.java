package org.petclinic.dtos;

import lombok.Data;

@Data
public class PetResponseDTO {
    private Long id;
    private String name;
    private String birthDate;
    private Long petTypeId;
    private String petTypeName;
}
