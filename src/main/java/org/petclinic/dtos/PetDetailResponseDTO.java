package org.petclinic.dtos;

import lombok.Data;
import org.springframework.hateoas.Link;

import java.util.ArrayList;
import java.util.List;

@Data
public class PetDetailResponseDTO {
    private Long petId;
    private String petName;
    private String birthDate;
    private String petTypeName;
    private String temperament;
    private Double lengthCm;
    private Double weightKg;
    private List<Link> links = new ArrayList<Link>();

    public void addLink(Link link) {
        links.add(link);
    }

}
