package com.clubes.especialidades.api.repository;

import com.clubes.especialidades.api.dao.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RegionRepo extends JpaRepository<Region, UUID> {
	Optional<Region> findByNameIgnoreCase(String name);

	Optional<Region> findByAbbreviationIgnoreCase(String abbreviation);
}
