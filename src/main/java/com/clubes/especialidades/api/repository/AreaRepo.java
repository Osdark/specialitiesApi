package com.clubes.especialidades.api.repository;

import com.clubes.especialidades.api.dao.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AreaRepo extends JpaRepository<Area, UUID> {
	Optional<Area> findByNameIgnoreCase(String name);
}
