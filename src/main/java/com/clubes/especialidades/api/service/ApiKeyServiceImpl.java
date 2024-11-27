package com.clubes.especialidades.api.service;

import com.clubes.especialidades.api.dao.sec.ApiKey;
import com.clubes.especialidades.api.dao.sec.User;
import com.clubes.especialidades.api.repository.ApiKeyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApiKeyServiceImpl implements ApiKeyService {
	@Autowired
	private ApiKeyRepo repo;

	@Override
	public Optional<User> validateApiKey(String apiKey) {
		ApiKey key = repo.findByApiKey(apiKey);
		if (key == null || !key.isActive()) {
			return Optional.empty();
		}
		return Optional.of(key.getUser());
	}

	@Override
	public String generateApiKey(User user) {
		return repo.findByUserId(user.getId()).stream()
				.filter(ApiKey::isActive)
				.findFirst()
				.map(ApiKey::getApiKey)
				.orElseGet(() -> {
					ApiKey newKey = new ApiKey();
					newKey.setUser(user);
					repo.save(newKey);
					return newKey.getApiKey();
				});
	}
}
