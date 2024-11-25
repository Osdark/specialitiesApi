package com.clubes.especialidades.api.controller;

import com.clubes.especialidades.api.dao.Requirement;
import com.clubes.especialidades.api.service.RequirementService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@Tag(name = "Requirement controller", description = "API to manage requirements")
public class RequirementControllerImpl implements RequirementController {
	@Autowired
	private RequirementService service;

	@Override
	public ResponseEntity<Requirement> createRequirement(Requirement requirement, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new IllegalArgumentException("Validation failed for request body: " + bindingResult.getAllErrors());
		}
		return ResponseEntity.ok(service.createRequirement(requirement));
	}

	@Override
	public ResponseEntity<Boolean> updateRequirement(UUID id, Requirement requirement, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new IllegalArgumentException("Validation failed for request body: " + bindingResult.getAllErrors());
		}
		return ResponseEntity.ok(service.updateRequirement(id, requirement));
	}

	@Override
	public ResponseEntity<Boolean> deleteRequirementById(UUID id) {
		return ResponseEntity.ok(service.deleteRequirementById(id));
	}

	@Override
	public ResponseEntity<List<Requirement>> getRequirements() {
		return ResponseEntity.ok(service.getRequirements());
	}

	@Override
	public ResponseEntity<Requirement> getRequirementById(UUID id) {
		return ResponseEntity.ok(service.getRequirementById(id));
	}

	@Override
	public ResponseEntity<Requirement> getRequirementByOrderNumber(int orderNumber) {
		return ResponseEntity.ok(service.getRequirementByOrderNumber(orderNumber));
	}
}
