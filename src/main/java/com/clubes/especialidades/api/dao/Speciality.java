package com.clubes.especialidades.api.dao;

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
public class Speciality {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	@NotNull(message = "Name cannot be null")
	@Column(unique = true)
	private String name;
	private String image;
	private String pdf;

	@ManyToOne
	@JoinColumn(name = "area_id", referencedColumnName = "id", nullable = false)
	private Area area;

	@ManyToMany
	@JoinTable(
			name = "speciality_region",
			joinColumns = @JoinColumn(name = "speciality_id"),
			inverseJoinColumns = @JoinColumn(name = "region_id")
	)
	private Set<Region> regions;
}
