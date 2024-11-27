package com.clubes.especialidades.api.repository;

import com.clubes.especialidades.api.dao.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpecialityRepo extends JpaRepository<Speciality, UUID> {
	Optional<Speciality> findByNameIgnoreCase(String name);

	List<Speciality> findByAreaNameIgnoreCase(String area);

	List<Speciality> findByRegionsNameIgnoreCase(String region);

	boolean existsByName(String name);
}
