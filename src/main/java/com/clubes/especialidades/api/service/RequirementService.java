package com.clubes.especialidades.api.service;

import com.clubes.especialidades.api.dao.Requirement;

import java.util.List;
import java.util.UUID;

public interface RequirementService {
	Requirement createRequirement(Requirement requirement);

	boolean updateRequirement(UUID id, Requirement requirement);

	boolean deleteRequirementById(UUID id);

	List<Requirement> getRequirements();

	Requirement getRequirementById(UUID id);

	Requirement getRequirementByOrderNumber(int orderNumber);
}
