package org.petclinic.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class VisitRequestDTO {
    @NotNull(message = "Owner ID is required")
    private Long ownerId;

    @NotNull(message = "Pet ID is required")
    private Long petId;

    @NotNull(message = "Visit date is required")
    @FutureOrPresent(message = "Visit date cannot be in the past")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotBlank(message = "Description is required")
    private String description;
}
