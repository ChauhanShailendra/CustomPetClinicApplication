package org.petclinic.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pet_details")
@Getter
@Setter
public class PetDetails extends BaseEntity {

	@OneToOne
	@JoinColumn(name = "pet_id", nullable = false, unique = true)
	private Pet pet;

	@Column(name = "temperament")
	private String temperament;

	@Column(name = "length_cm")
	private Double lengthCm;

	@Column(name = "weight_kg")
	private Double weightKg;

	@Override
	public String toString() {
		return "PetDetails{" +
			", temperament='" + temperament + '\'' +
			", lengthCm=" + lengthCm +
			", weightKg=" + weightKg +
			'}';
	}
}
