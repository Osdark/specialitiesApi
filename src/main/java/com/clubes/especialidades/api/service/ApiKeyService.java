package com.clubes.especialidades.api.service;

import com.clubes.especialidades.api.dao.sec.User;

import java.util.Optional;

public interface ApiKeyService {
	Optional<User> validateApiKey(String apiKey);

	String generateApiKey(User user);
}
