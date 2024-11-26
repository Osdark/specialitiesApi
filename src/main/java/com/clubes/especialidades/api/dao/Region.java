package com.clubes.especialidades.api.dao;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "regions")
public class Region {
	@Id
	private UUID id;
	@NotNull(message = "Name cannot be null")
	private String name;
	@NotNull(message = "Abbreviation cannot be null")
	private String abbreviation;

	@ManyToMany(mappedBy = "regions")
	private Set<Speciality> specialities;

	public Region(UUID id, String name, String abbreviation) {
		this.id = id;
		this.name = name;
		this.abbreviation = abbreviation;
	}
}
