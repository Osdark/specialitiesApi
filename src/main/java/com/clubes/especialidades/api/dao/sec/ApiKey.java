package com.clubes.especialidades.api.dao.sec;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "api_keys")
public class ApiKey {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private String apiKey = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 16);
	private LocalDateTime createdAt = LocalDateTime.now();
	private boolean active = true;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public ApiKey(User user) {
		this.user = user;
	}
}
