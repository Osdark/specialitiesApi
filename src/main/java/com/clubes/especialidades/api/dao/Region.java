package com.clubes.especialidades.api.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "regions")
@JsonIgnoreProperties("specialities")
public class Region {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@NotNull(message = "Name cannot be null")
	private String name;
	@NotNull(message = "Abbreviation cannot be null")
	private String abbreviation;

	@ManyToMany(mappedBy = "regions")
	private Set<Speciality> specialities;

	public Region(String name, String abbreviation) {
		this.name = name;
		this.abbreviation = abbreviation;
	}
}
