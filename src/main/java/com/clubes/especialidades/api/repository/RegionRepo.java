package com.clubes.especialidades.api.repository;

import com.clubes.especialidades.api.dao.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RegionRepo extends JpaRepository<Region, UUID> {
	Region findByName(String name);
}
