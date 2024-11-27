package com.clubes.especialidades.api.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
@Table(name = "areas")
@JsonIgnoreProperties("specialities")
public class Area {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@Column(unique = true)
	@NotNull(message = "Name cannot be null")
	@NotBlank(message = "Name cannot be empty")
	private String name;
	@NotNull(message = "Color cannot be null")
	@NotEmpty(message = "Color cannot be empty")
	private String color;

	@OneToMany(mappedBy = "area", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Speciality> specialities;

	public Area(String name, String color) {
		this.name = name;
		this.color = color;
	}
}
