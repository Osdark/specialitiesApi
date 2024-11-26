package com.clubes.especialidades.api.dao.sec;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
	@Id
	private Long id;
	private String username;
	private String password;
	private boolean enabled;
	private String role;
}
