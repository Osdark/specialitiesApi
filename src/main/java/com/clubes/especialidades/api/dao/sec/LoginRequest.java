package com.clubes.especialidades.api.dao.sec;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LoginRequest {
	private String username;
	private String password;
}