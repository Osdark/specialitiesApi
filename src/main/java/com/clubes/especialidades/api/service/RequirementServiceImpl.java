package com.clubes.especialidades.api.service;

import com.clubes.especialidades.api.dao.Requirement;
import com.clubes.especialidades.api.repository.RequirementRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RequirementServiceImpl implements RequirementService {
	@Autowired
	private RequirementRepo repo;

	public Requirement createRequirement(Requirement requirement) {
		return repo.save(requirement);
	}

	@Override
	public boolean updateRequirement(UUID id, Requirement requirement) {
		repo.findById(id).ifPresentOrElse(
				(existing) -> {
					existing.setOrderNumber(requirement.getOrderNumber());
					existing.setDescription(requirement.getDescription());
					existing.setSection(requirement.getSection());
					repo.save(existing);
				},
				() -> {
					throw new IllegalArgumentException("Requirement with id " + id + " not found");
				}
		);
		return true;
	}

	public boolean deleteRequirementById(UUID id) {
		repo.deleteById(id);
		return true;
	}

	public List<Requirement> getRequirements() {
		return repo.findAll();
	}

	public Requirement getRequirementById(UUID id) {
		return repo.findById(id).orElseThrow(EntityNotFoundException::new);
	}

	public Requirement getRequirementByOrderNumber(int orderNumber) {
		return repo.findByOrderNumber(orderNumber);
	}
}
