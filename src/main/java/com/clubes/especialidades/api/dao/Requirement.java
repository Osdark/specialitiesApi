package com.clubes.especialidades.api.dao;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "requirements")
public class Requirement {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	@NotNull(message = "orderNumber is required")
	@Min(value = 1, message = "orderNumber must be greater than 0")
	private Integer orderNumber;
	@NotNull(message = "description is required")
	private String description;
	@NotNull(message = "section is required")
	private String section;

	@ManyToOne
	@JoinColumn(name = "speciality_id", referencedColumnName = "id")
	private Speciality speciality;
}
