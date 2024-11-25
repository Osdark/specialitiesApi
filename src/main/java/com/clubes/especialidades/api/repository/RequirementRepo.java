package com.clubes.especialidades.api.repository;

import com.clubes.especialidades.api.dao.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RequirementRepo extends JpaRepository<Requirement, UUID> {
	Requirement findByOrderNumber(int orderNumber);
}
