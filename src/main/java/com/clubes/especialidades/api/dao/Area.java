package com.clubes.especialidades.api.dao;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "areas")
public class Area {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	@Column(unique = true)
	@NotNull(message = "Name cannot be null")
	private String name;
	@NotNull(message = "Info cannot be null")
	private String info;
}
