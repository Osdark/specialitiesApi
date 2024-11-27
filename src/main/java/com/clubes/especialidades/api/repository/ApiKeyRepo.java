package com.clubes.especialidades.api.repository;

import com.clubes.especialidades.api.dao.sec.ApiKey;
import com.clubes.especialidades.api.dao.sec.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ApiKeyRepo extends JpaRepository<ApiKey, UUID> {
	ApiKey findByApiKey(String apiKey);

	List<ApiKey> findByUserId(Long userId);
}
