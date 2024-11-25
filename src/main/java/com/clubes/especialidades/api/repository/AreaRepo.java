package com.clubes.especialidades.api.repository;

import com.clubes.especialidades.api.dao.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AreaRepo extends JpaRepository<Area, UUID> {
	Area findByName(String name);
}
