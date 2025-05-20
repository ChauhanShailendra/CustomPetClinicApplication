package org.petclinic.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PetInfoRequestDTO {
    private Long ownerId;
    private Long petId;

    @NotBlank(message = "Pet name is required")
    private String petName;

    @NotNull(message = "Birth date is required")
    @PastOrPresent(message = "Birth date cannot be in the future")
    private LocalDate birthDate;

    @NotNull(message = "Pet Type ID is required")
    private Long petTypeId;

    @NotBlank(message = "Temperament is required")
    private String temperament;

    @NotNull(message = "Length (cm) is required")
    @Positive(message = "Length must be positive")
    private Double lengthCm;

    @NotNull(message = "Weight (kg) is required")
    @Positive(message = "Weight must be positive")
    private Double weightKg;
}
